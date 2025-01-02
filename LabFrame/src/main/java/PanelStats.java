import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


class PanelStats extends JPanel implements PropertyChangeListener {
    public PanelStats() {
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Repository repository = Repository.getInstance();
        Point center = repository.getCenter();
        Point scaledCenter = repository.scale(center, getSize());
        List<Point> corners = repository.getCorners();
        List<Point> scaledCorners = new ArrayList<>();

        if (corners.size() >= 4) {
            for (Point corner : corners) {
                Point c = repository.scale(corner, getSize());
                scaledCorners.add(c);

                g2d.draw(new Line2D.Double(c.getX(), c.getY(), scaledCenter.getX(), scaledCenter.getY()));
                g2d.setColor(Color.BLUE);
                g2d.fillOval((int) (c.getX() - 5), (int) (c.getY() - 5), 10, 10);

            }
            g2d.setColor(Color.RED);
            g2d.fillOval((int) (scaledCenter.getX() - 4), (int) (scaledCenter.getY() - 4), 8, 8);
        }
        repository.setCenter(scaledCenter);
        repository.setCorners(scaledCorners);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("stats".equals(evt.getPropertyName())) {
            repaint();
        }
    }
}
