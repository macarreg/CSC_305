import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DrawArea extends JPanel implements PropertyChangeListener {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for (Point point : Repository.getInstance().getPoints()) {
            g.fillOval(point.x - 5, point.y - 5, 10, 10);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("points".equals(evt.getPropertyName())) {
            repaint();
        }
    }
}
