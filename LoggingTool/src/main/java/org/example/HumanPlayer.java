package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;
    private Logger logger = LoggerFactory.getLogger(HumanPlayer.class);
    public HumanPlayer(char symbol, View view, Scanner scanner) {
        super(symbol, view);
        this.scanner = scanner;
    }

    @Override
    public void makeMove(Board board) {
        logger.info("here in move");
        int move;
        while (true) {
            view.printMessage("Enter your move (1-9): ");
            while(!scanner.hasNextInt()) {
                view.printMessage("You must choose a number (1-9) that is not yet taken, try again.");
                scanner.next();
            }
            move = scanner.nextInt() - 1;  // -1 to match array index
            if (board.isValidMove(move)) {
                board.updateBoard(move, symbol);
                break;
            } else {
                view.printMessage("You must choose a number (1-9) that is not yet taken, try again.");
            }
        }
    }
}
