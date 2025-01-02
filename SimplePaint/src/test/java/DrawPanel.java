import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPanel extends JPanel {
    private SVGData svgData;
    private SVGShape selectedShape;

    public DrawPanel(SVGData svgData) {
        this.svgData = svgData;
        setBackground(Color.WHITE);

        svgData.addPropertyChangeListener(evt -> repaint());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedShape = findShapeAt(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    selectedShape.setPosition(e.getX(), e.getY());
                    svgData.addShape(selectedShape);  // Update to trigger listeners
                    repaint();
                }
            }
        });
    }

    private SVGShape findShapeAt(int x, int y) {
        for (SVGShape shape : svgData.getShapes()) {
            // Basic hit detection logic for each shape type (rect/ellipse)
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (SVGShape shape : svgData.getShapes()) {
            if (shape instanceof SVGRectangle) {
                SVGRectangle rect = (SVGRectangle) shape;
                g2d.setColor(Color.BLUE);
                g2d.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            } else if (shape instanceof SVGEllipse) {
                SVGEllipse ellipse = (SVGEllipse) shape;
                g2d.setColor(Color.RED);
                g2d.fillOval(ellipse.getCx() - ellipse.getRx(), ellipse.getCy() - ellipse.getRy(), ellipse.getRx() * 2, ellipse.getRy() * 2);
            }
        }
    }
}
