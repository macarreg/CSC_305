import java.util.*;

public class Game implements Observable {
    private final List<Tower> towers = new ArrayList<>(3);
    private final List<String> moveHistory = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();
    private final int numDisks;

    public Game(int numDisks) {
        this.numDisks = numDisks;
        for (int i = 0; i < 3; i++) {
            towers.add(new Tower());
        }
        towers.get(0).initialize(numDisks);
    }

    public record Move(int from, int to) {
    }

    public boolean makeMove(int from, int to) {
        if (towers.get(from).moveDiskTo(towers.get(to))) {
            trackMove(from, to);
            notifyObservers();
            return true;
        }
        return false;
    }

    public String getHint() {
        Move nextMove = getOptimalMoveAStar();
        if (nextMove != null) {
            moveHistory.add("hint");
            return "Hint: Move from Peg " + (nextMove.from + 1) +
                    " to Peg " + (nextMove.to + 1);
        }
        return "No valid moves available.";
    }

    private Move getOptimalMoveAStar() {
        PriorityQueue<GameState> queue = new PriorityQueue<>(Comparator.comparingInt(GameState::getCost));
        Set<String> visited = new HashSet<>();

        GameState initialState = new GameState(copyTowers(towers), null, -1, -1, 0);
        queue.add(initialState);
        visited.add(initialState.toString());

        while (!queue.isEmpty()) {
            GameState state = queue.poll();

            if (state.towers.get(2).size() == numDisks) {
                return state.getFirstMove();
            }

            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from != to && state.towers.get(from).canMoveTo(state.towers.get(to))) {
                        List<Tower> newTowers = copyTowers(state.towers);
                        newTowers.get(to).push(newTowers.get(from).pop());

                        GameState newState = new GameState(newTowers, state, from, to, state.g + 1);
                        if (visited.add(newState.toString())) {
                            queue.add(newState);
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean isSolved() {
        return towers.get(2).size() == numDisks;
    }

    public List<String> getMoveHistory() {
        return moveHistory;
    }

    private void trackMove(int from, int to) {
        moveHistory.add("Moved from Peg " + (from + 1) + " to Peg " + (to + 1));
    }

    private List<Tower> copyTowers(List<Tower> original) {
        List<Tower> copy = new ArrayList<>();
        for (Tower tower : original) {
            copy.add(new Tower(tower));
        }
        return copy;
    }

    public Tower getTowers(int index) {
        return towers.get(index);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
