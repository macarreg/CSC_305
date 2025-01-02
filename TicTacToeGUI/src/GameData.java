import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GameData extends PropertyChangeSupport {
    private static GameData instance;
    private final List<Box> boxes;
    private boolean xTurn = true;  // Track whose turn it is (X goes first)
    private boolean gameOver = false;
    private boolean tie = false;

    private GameData() {
        super(new Object());
        boxes = new ArrayList<>();
        initializeBoard();
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    private void initializeBoard() {
        boxes.clear();
        int size = 100;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boxes.add(new Box(col * size, row * size, size));
            }
        }
    }

    public void checkForWin() {
        String[][] board = new String[3][3];
        int index = 0;

        // Populate 2D board from the box list
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = boxes.get(index++).getMark();
            }
        }

        // Check rows, columns, and diagonals for a win
        if (hasThreeInARow(board)) {
            gameOver = true;
            repaint();
        } else if (boxes.stream().allMatch(Box::isFilled)) {
            tie = true;  // It's a draw if all boxes are filled
            gameOver = true;
        }
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

    private boolean checkLine(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    public void repaint() {
        // Notify the GUI to refresh the board
        firePropertyChange("repaint", null, null);
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
}

