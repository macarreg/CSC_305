package org.example;
public class Board {
    private final char[] board;

    public Board() {
        this.board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }
    public char[] getBoard() {return board;}
    public void updateBoard(int move, char symbol) {board[move] = symbol;}
    public boolean isValidMove(int move) {
        return move >= 0 && move < 9 && board[move] != 'X' && board[move] != 'O';
    }

    public boolean checkWin(char symbol) {
        int[][] winCombos = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}  // Diagonals
        };
        for (int[] combo : winCombos) {
            if (board[combo[0]] == symbol && board[combo[1]] == symbol && board[combo[2]] == symbol) {
                return true;
            }
        }
        return false;
    }
    public boolean isBoardFull() {
        for (char c : board) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        return true;
    }
}
