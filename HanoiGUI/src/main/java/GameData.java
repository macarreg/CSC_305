import java.util.*;

public class GameData extends Observable {
    private static GameData instance;
    private final List<Tower> towers;
    private final int numDisks;
    private final List<String> moveHistory;

    private GameData(int numDisks) {
        this.numDisks = numDisks;
        this.towers = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            towers.add(new Tower());
        }
        towers.get(0).initialize(numDisks);
        moveHistory = new ArrayList<>();
    }

    public static synchronized GameData getInstance() {
        if (instance == null) {
            instance = new GameData(3);
        }
        return instance;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public boolean isGameWon() {
        Tower thirdTower = towers.get(2);
        return thirdTower.getDisks().size() == numDisks;
    }

    public List<String> getMoveHistory() {
        return moveHistory;
    }

    public boolean isValidMove(int from, int to) {
        if (from == to) return false;
        Tower source = towers.get(from);
        Tower target = towers.get(to);

        if (source.isEmpty()) return false;
        return target.isEmpty() || source.peek() < target.peek();
    }

    public boolean makeMove(int from, int to) {
        if (isValidMove(from, to)) {
            towers.get(from).moveDiskTo(towers.get(to));
            moveHistory.add("Move disk from " + (from + 1) + " to " + (to + 1));  // Log the move
            notifyObservers();
            return true;
        }
        return false;
    }

    public void checkWinCondition() {
        if (isGameWon()) {
            String winMessage = "Congratulations! You won the game!";
            moveHistory.add(winMessage);
            notifyObservers();
        }
    }


    public String getHint() {
        if (isGameWon()) {
            return "You have already won the game!";
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && isValidMove(i, j)) {
                    if (j == 2) {
                        return "Hint: Move a disk from Peg " + (i + 1) + " to Peg " + (j + 1) + " to progress toward solving!";
                    } else {
                        return "Hint: You can move a disk from Peg " + (i + 1) + " to Peg " + (j + 1) + ".";
                    }
                }
            }
        }
        return "No valid moves available.";
    }
}
