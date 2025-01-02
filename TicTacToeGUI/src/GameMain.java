import javax.swing.*;

public class GameMain extends JFrame {
    public GameMain() {
        BoardPanel panel = new BoardPanel();
        add(panel);

        GameController controller = new GameController();
        panel.addMouseListener(controller);

        GameData.getInstance().addPropertyChangeListener(panel);

        setTitle("Tic-Tac-Toe");
        setSize(300, 320);  // Adjusted to fit 3x3 grid
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameMain();
    }
}
