import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("UML Diagram Editor");
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        UMLPanel umlPanel = new UMLPanel();
        DrawAreaListener drawAreaListener = new DrawAreaListener();
        umlPanel.addMouseListener(drawAreaListener);
        umlPanel.addMouseMotionListener(drawAreaListener);

        CodePanel codePanel = new CodePanel();
        tabbedPane.addTab("UML", umlPanel);
        tabbedPane.add("Code", codePanel);
        Repository.getInstance().addPropertyChangeListener(umlPanel);

        frame.add(tabbedPane);
        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JButton undo = new JButton("Undo");
        JButton run = new JButton("Run");
        JButton about = new JButton("About");
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        MenuController menuController = new MenuController();
        newItem.addActionListener(e -> menuController.newProject());
        openItem.addActionListener(e -> menuController.openProject());
        saveItem.addActionListener(e -> menuController.saveProject());
        exitItem.addActionListener(e -> menuController.exitApplication());
        undo.addActionListener(e -> menuController.undoAction());
        run.addActionListener(e -> Repository.getInstance().firePropertyChange("selectedFile", null, "this class"));
        about.addActionListener(e -> menuController.aboutDialog());

        JMenu connectorMenu = new JMenu("Box Connection");
        String[] connectionTypes = {"Association", "Aggregation", "Composition", "Inheritance", "Implementation", "None" };
        for (String type : connectionTypes) {
            JMenuItem item = new JMenuItem(type);
            item.addActionListener(e -> Repository.getInstance().setConnectionType(type));
            connectorMenu.add(item);
        }

        menuBar.add(fileMenu);
        menuBar.add(connectorMenu);
        menuBar.add(undo);
        menuBar.add(run);
        menuBar.add(about);

        return menuBar;
    }
}
