import javax.swing.*;

public class GameMain extends JFrame {
    public GameMain() {
        BoardPanel panel = new BoardPanel();
        GameController controller = new GameController(panel);

        add(panel);
        panel.addMouseListener(controller);

        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Ensure the board is initialized once the window size is known
        GameData.getInstance().setSize(getWidth(), getHeight());
        GameData.getInstance().recalculate();
        GameData.getInstance().addPropertyChangeListener(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameMain::new);
    }
}
