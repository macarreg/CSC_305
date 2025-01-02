import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        View view = new View();
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        Player player = new HumanPlayer('X', view, scanner);
        Player computer = new ComputerPlayer('O', view);

        while (true) {
            view.printBoard(board.getBoard());

            view.printMessage("Your turn");
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
        scanner.close();
    }
}
