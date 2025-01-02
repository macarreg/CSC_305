import java.util.List;

public class GameState {
    List<Tower> towers;
    GameState previous;
    int from, to, g;

    public GameState(List<Tower> towers, GameState previous, int from, int to, int g) {
        this.towers = towers;
        this.previous = previous;
        this.from = from;
        this.to = to;
        this.g = g;
    }

    public Game.Move getFirstMove() {
        GameState state = this;
        while (state.previous != null && state.previous.previous != null) {
            state = state.previous;
        }
        return new Game.Move(state.from, state.to);
    }

    private int heuristic() {
        int misplaced = 0;
        for (int i = 0; i < 2; i++) {
            misplaced += towers.get(i).size();
        }
        return misplaced;
    }
    public int getCost() {
        return g + heuristic();
    }
}
