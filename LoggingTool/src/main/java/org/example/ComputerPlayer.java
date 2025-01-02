package org.example;
import java.util.Random;

public class ComputerPlayer extends Player {
    private final Random random;

    public ComputerPlayer(char symbol, View view) {
        super(symbol, view);
        this.random = new Random();
    }

    @Override
    public void makeMove(Board board) {
        int move;
        while (true) {
            move = random.nextInt(9);
            if (board.isValidMove(move)) {
                board.updateBoard(move, symbol);
                view.printComputerMove(move);
                break;
            }
        }
    }
}
