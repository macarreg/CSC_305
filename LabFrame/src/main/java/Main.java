import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Main mainFrame = new Main();
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("Map");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public Main() {
        JFrame statFrame = new JFrame();
        statFrame.setSize(400,300);
        statFrame.setTitle("Statistics");
        statFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        statFrame.setResizable(false);
        statFrame.setLocationRelativeTo(null);
        statFrame.setVisible(false);

        PanelCities panelCities = new PanelCities();
        PanelStats panelStats = new PanelStats();
        Repository repository = Repository.getInstance();
        MenuController menuController = new MenuController(this, statFrame);
        repository.addPropertyChangeListener(panelCities);
        repository.addPropertyChangeListener(panelStats);

        setLayout(new BorderLayout());
        add(panelCities, BorderLayout.CENTER);
        setJMenuBar(menuController.createMenuBar());

        statFrame.setLayout(new BorderLayout());
        statFrame.add(panelStats, BorderLayout.CENTER);
    }
}
