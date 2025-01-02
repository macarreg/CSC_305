package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TicTacToe {



    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(TicTacToe.class);
        logger.info("starting tge main");
        View view = new View();
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        Player player = new HumanPlayer('X', view, scanner);

        Player computer = new ComputerPlayer('O', view);



        while (true) {
            view.printBoard(board.getBoard());

            view.printMessage("Player 1's turn");
            player.makeMove(board);

            if (board.checkWin(player.getSymbol())) {
                view.printBoard(board.getBoard());
                view.printMessage("You Win!");
                break;
            }
            if (board.isBoardFull()) {
                view.printBoard(board.getBoard());
                view.printMessage("Cat's Game!");
                break;
            }

            // No need to check for tie bc player goes first and last
            view.printMessage("CPU's turn");
            computer.makeMove(board);

            if (board.checkWin(computer.getSymbol())) {
                view.printBoard(board.getBoard());
                view.printMessage("You Lose");
                break;
            }
        }
        logger.debug("out of while");
        scanner.close();
    }
}
