import java.util.List;
import java.util.Scanner;

public class View implements Observer {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final Game game;

    public View(Game game) {
        this.game = game;
        game.addObserver(this);
    }

    public static int askForNumberOfDisks() {
        while (true) {
            System.out.print("Enter the number of disks: ");
            try {
                int numDisks = Integer.parseInt(SCANNER.nextLine().trim());
                if (numDisks >= 1 && numDisks <= 4) {
                    return numDisks;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public String askForMoveOrHint() {
        System.out.print("Enter your move (e.g., '1 2') or 'hint': ");
        return SCANNER.nextLine().trim();
    }

    public int[] parseMoveInput(String input) {
        try {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Please enter exactly two numbers separated by a space.");
            }

            int from = Integer.parseInt(parts[0].trim()) - 1;
            int to = Integer.parseInt(parts[1].trim()) - 1;

            if (!isValidPegNumber(from) || !isValidPegNumber(to)) {
                throw new IllegalArgumentException("Please enter numbers between 1 and 3.");
            }
            return new int[]{from, to};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter two valid integers.");
        }
    }

    private boolean isValidPegNumber(int peg) {
        return peg >= 0 && peg <= 2;
    }

    public void displayGameState() {
        System.out.println("Current State:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Peg " + (i + 1) + ": " + game.getTowers(i));
        }
        System.out.println();
    }

    public void displaySuccessfulMove() {
        System.out.println(game.getMoveHistory().get(game.getMoveHistory().size() - 1));
    }

    public void displayInvalidMove() {
        System.out.println("Invalid move! Make sure you follow the game rules.");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayGameSolved() {
        System.out.println("Congratulations, you solved the puzzle!");
    }

    public void displayAllMoves(List<String> moves) {
        System.out.println("Moves made: " + moves.size());
    }

    @Override
    public void update() {
        displayGameState();
    }
}
