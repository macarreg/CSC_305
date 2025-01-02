import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
        Panel panel = new Panel();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(600, 400);
        main.setLocationRelativeTo(null);
        main.add(panel);
        main.setVisible(true);
    }
}
