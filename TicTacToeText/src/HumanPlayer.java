import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(char symbol, View view, Scanner scanner) {
        super(symbol, view);
        this.scanner = scanner;
    }

    @Override
    public void makeMove(Board board) {
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

