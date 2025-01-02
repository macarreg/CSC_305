import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GameData extends PropertyChangeSupport {
    private static GameData instance;
    private final List<Box> boxes;
    private int windowWidth, windowHeight;
    private boolean xTurn = true;
    private boolean tie = false;
    private boolean gameOver = false;

    private GameData() {
        super(new Object());
        boxes = new ArrayList<>();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public void recalculate() {
        if (windowWidth == 0 || windowHeight == 0) {
            return;
        }
        boxes.clear();
        int boxSize = Math.min(windowWidth, windowHeight) / 3;  // Each box size
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boxes.add(new Box(col * boxSize, row * boxSize, boxSize));
            }
        }
        repaint();  // Notify listeners to draw the initial board
    }

    private boolean checkLine(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    private boolean hasThreeInARow(String[][] board) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||
                    checkLine(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        // Check diagonals
        return checkLine(board[0][0], board[1][1], board[2][2]) ||
                checkLine(board[0][2], board[1][1], board[2][0]);
    }

    public void checkForWin() {
        String[][] board = new String[3][3];
        int index = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = boxes.get(index++).getSymbol();
            }
        }

        if (hasThreeInARow(board)) {
            gameOver = true;
            repaint();  // Notify observers to show the result
        } else if (boxes.stream().allMatch(Box::isFilled)) {
            tie = true;
            gameOver = true;
            repaint();
        }
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isTie() {
        return tie;
    }

    public void toggleTurn() {
        xTurn = !xTurn;
    }

    public void repaint() {
        firePropertyChange("repaint", null, null);  // Notify observers to repaint
    }

    public void setSize(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.recalculate();
    }
}
