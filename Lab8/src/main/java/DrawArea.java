import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class DrawArea extends JPanel implements PropertyChangeListener {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Point> points = Repository.getInstance().getPoints();
        for (int i = 0; i < Repository.getInstance().getPoints().size(); i++) {
            Point p = points.get(i);
            int intensity = 255 - (i * (255 / 10));
            g.setColor(new Color(intensity, intensity, intensity));
            g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
        connectTheDots(g, points);
    }
    private void connectTheDots(Graphics g, List<Point> points) {
        if (points.size() < 2) {
            return;
        }

        Point start = points.get(0);
        boolean[] visited = new boolean[points.size()];
        visited[0] = true;

        for (int i = 1; i < points.size(); i++) {
            Point nearestPoint = null;
            int nearestIndex = -1;
            double minDistance = Double.MAX_VALUE;

            for (int j = 1; j < points.size(); j++) {
                if (!visited[j]) {
                    double distance = start.distance(points.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestPoint = points.get(j);
                        nearestIndex = j;
                    }
                }
            }

            g.setColor(Color.BLACK);
            g.drawLine(start.x, start.y, nearestPoint.x, nearestPoint.y);

            visited[nearestIndex] = true;
            start = nearestPoint;
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        repaint();
    }
}
