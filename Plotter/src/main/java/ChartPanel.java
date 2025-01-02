import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A Panel that draw a decorated marker per value in the board values list
 * It is an observer of the board values list
 *
 * @author javiergs
 * @version 1.0
 */
public class ChartPanel extends JPanel implements PropertyChangeListener {

    Marker marker;

    public ChartPanel(Marker marker) {
        this.marker = marker;
    }

    public void paintComponent(Graphics g) {
        if (marker != null)
            for (int i = 0; i < Board.getInstance().getValues().size(); i++) {
                marker.draw(g, i);
            }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

}