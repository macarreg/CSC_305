
import javax.swing.*;
import java.awt.*;

/**
 * The Frame class represents the main application window.
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        Main app = new Main();
        app.setSize(1200, 600);
        app.setTitle("Paint App");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setVisible(true);
    }

    public Main() {
        JPanel drawPanel = new DrawPanel();
        Officer officer = Officer.getInstance();
        officer.setDrawPanel(drawPanel);
        MouseController ml = new MouseController();
        officer.getDrawPanel().addMouseListener(ml);
        officer.getDrawPanel().addMouseMotionListener(ml);
        TextPanel textPanel = new TextPanel();
        officer.addPropertyChangeListener(textPanel);
        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.WEST);
        createMenuBar();
        setFocusable(true);
        requestFocusInWindow();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        Officer officer = Officer.getInstance();
        newMenuItem.addActionListener(e -> officer.newFile());
        saveMenuItem.addActionListener(e -> officer.saveFile());
        loadMenuItem.addActionListener(e -> officer.loadFile());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(createShapesMenu());
        menuBar.add(createColorsMenu());
        setJMenuBar(menuBar);
    }

    private JMenu createShapesMenu() {
        JMenu shapesMenu = new JMenu("Shapes");
        JRadioButtonMenuItem rectangleTool = new JRadioButtonMenuItem("Rectangle");
        JRadioButtonMenuItem circleTool = new JRadioButtonMenuItem("Circle");
        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(rectangleTool);
        shapeGroup.add(circleTool);
        rectangleTool.setSelected(true);
        Officer officer = Officer.getInstance();
        rectangleTool.addActionListener(e -> officer.setShape("Rectangle"));
        circleTool.addActionListener(e -> officer.setShape("Circle"));
        shapesMenu.add(rectangleTool);
        shapesMenu.add(circleTool);
        return shapesMenu;
    }

    private JMenu createColorsMenu() {
        JMenu colorMenu = new JMenu("Color");
        JRadioButtonMenuItem redColor = new JRadioButtonMenuItem("Red");
        JRadioButtonMenuItem blueColor = new JRadioButtonMenuItem("Blue");
        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(redColor);
        colorGroup.add(blueColor);
        Officer officer = Officer.getInstance();
        redColor.addActionListener(e -> officer.setColor(Color.RED));
        blueColor.addActionListener(e -> officer.setColor(Color.BLUE));
        colorMenu.add(redColor);
        colorMenu.add(blueColor);
        return colorMenu;
    }

}