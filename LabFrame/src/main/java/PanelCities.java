import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelCities extends JPanel implements PropertyChangeListener{
    public PanelCities() {
        setBackground(new Color(100, 200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Repository repository = Repository.getInstance();
        for (Point city : repository.getCities()) {
            Point scaledPoint = repository.scale(city, getSize());
            g2d.fillOval((int) (scaledPoint.getX() - 2), (int) (scaledPoint.getY() - 2), 4, 4);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("city".equals(evt.getPropertyName())) {
            repaint();
        }
    }
}