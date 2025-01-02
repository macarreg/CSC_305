package org.example;
public class View {

    public void printBoard(char[] board) {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("---|---|---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("---|---|---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " ");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printComputerMove(int move) {
        System.out.println("Computer chose position " + (move + 1));
    }
}
