import javax.swing.*;

/**
 * Main class creates a window with a menu bar and a DrawPanel where user can draw cities and their connections
 * and save or load the data.
 *
 * @author javiergs
 * @version 1.0
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setTitle("Example of Drag and Drop in Java Swing");
        main.setSize(800, 600);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setVisible(true);
    }

    public Main() {
        DrawArea drawPanel = new DrawArea();
        DrawAreaListener drawAreaListener = new DrawAreaListener();
        drawPanel.addMouseListener(drawAreaListener);
        drawPanel.addMouseMotionListener(drawAreaListener);
        add(drawPanel);
        Blackboard.getInstance().addPropertyChangeListener(drawPanel);
    }

}
