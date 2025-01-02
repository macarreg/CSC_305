import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameData gameData = GameData.getInstance();
            GameController controller = new GameController(gameData);
            GameFrame gameFrame = new GameFrame(controller);
            gameData.addObserver(gameFrame);
        });
    }
}
