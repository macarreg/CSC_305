import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SVG Paint App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            SVGData svgData = SVGData.getInstance();
            DrawPanel drawPanel = new DrawPanel(svgData);
            SVGTextPanel svgTextPanel = new SVGTextPanel(svgData);

            // Set up menu bar for shape creation
            JMenuBar menuBar = new JMenuBar();
            JMenu shapeMenu = new JMenu("Shape");
            JMenuItem rectangleItem = new JMenuItem("Rectangle");
            JMenuItem ellipseItem = new JMenuItem("Ellipse");

            rectangleItem.addActionListener(e -> svgData.addShape(new SVGRectangle(50, 50, 100, 50)));
            ellipseItem.addActionListener(e -> svgData.addShape(new SVGEllipse(100, 100, 80, 40)));

            shapeMenu.add(rectangleItem);
            shapeMenu.add(ellipseItem);
            menuBar.add(shapeMenu);

            frame.setJMenuBar(menuBar);

            frame.setLayout(new GridLayout(1, 2));
            frame.add(svgTextPanel);
            frame.add(drawPanel);
            frame.setVisible(true);
        });
    }
}
