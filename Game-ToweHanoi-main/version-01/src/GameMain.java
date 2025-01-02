import javax.swing.*;


/**
 * The main class for the Towers of Hanoi game.
 * It extends creates a new TowerPanel object to display the game.
 * Define number of disks and window size.
 *
 * @author Professor
 */
public class GameMain extends JFrame {

  public GameMain() {

    TowerPanel panel = new TowerPanel();
    add(panel);

    GameController controller = new GameController();
    panel.addMouseListener(controller);
    panel.addMouseMotionListener(controller);
    panel.addComponentListener(controller);

    GameData.getInstance().setnDisks(4);
    GameData.getInstance().setSize(this.getWidth(), this.getHeight());
    GameData.getInstance().addPropertyChangeListener(panel);
  }

  public static void main(String[] args) {

    GameMain main = new GameMain();
    main.setTitle("Towers of Hanoi");
    main.setSize(600, 400);
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setVisible(true);
  }
}
