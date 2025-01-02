import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuController implements ActionListener {
    private final JFrame mainFrame;
    private final JFrame statFrame;

    public MenuController(JFrame mainFrame, JFrame statFrame) {
        this.mainFrame = mainFrame;
        this.statFrame = statFrame;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JButton loadItem = new JButton("Load");
        loadItem.setActionCommand("load");
        loadItem.addActionListener(this);

        JButton runItem = new JButton("Run");
        runItem.setActionCommand("run");
        runItem.addActionListener(this);

        JButton statsItem = new JButton("Statistics");
        statsItem.setActionCommand("stat");
        statsItem.addActionListener(this);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("exit");
        exitItem.addActionListener(this);

        menuBar.add(loadItem);
        menuBar.add(runItem);
        menuBar.add(statsItem);
        menuBar.add(exitItem);
        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Repository repository = Repository.getInstance();
        Strategy strategy = new StrategyFile();
        switch (e.getActionCommand()) {
            case "load":
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    strategy.load(file);
                }
                break;
            case "run":
                repository.firePropertyChange("city", null, e);
                break;
            case "stat":
                strategy.findStatistics();
                statFrame.setVisible(true);
                repository.firePropertyChange("stats", null, e);
                break;
            case "exit":
                repository.clearData();
                statFrame.setVisible(false);
                break;
            default:
                throw new UnsupportedOperationException("Unknown command: " + e.getActionCommand());
        }
    }
}
