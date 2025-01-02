import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class Main {
    public static void main(String[] args) {
         Logger logger = LoggerFactory.getLogger(Main.class);
        try {
            int numDisks = View.askForNumberOfDisks();
            Game game = new Game(numDisks);
            View view = new View(game);

            while (!game.isSolved()) {
                view.displayGameState();
                String input = view.askForMoveOrHint();

                if (input.equalsIgnoreCase("hint")) {
                    String hint = game.getHint();
                    view.displayMessage(hint);
                } else {
                    try {
                        int[] move = view.parseMoveInput(input);
                        if (game.makeMove(move[0], move[1])) {
                            view.displaySuccessfulMove();
                        } else {
                            view.displayInvalidMove();
                        }
                    } catch (IllegalArgumentException e) {
                        view.displayMessage(e.getMessage());
                    }
                }
            }
            view.displayGameSolved();
            view.displayAllMoves(game.getMoveHistory());
        } catch (Exception e) {
            logger.error("An unexpected error occurred: " + e.getMessage());
        }
    }
}
