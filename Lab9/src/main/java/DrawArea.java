import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * DrawPanel is a JPanel where the user can draw nodes and their connections
 *
 * @author javiergs
 * @version 1.0
 */
public class DrawArea extends JPanel implements PropertyChangeListener {

    public DrawArea() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw grid as background
        g.setColor(new Color(250, 250, 250, 255));
        for (int i = 0; i < getWidth(); i += 20) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = 0; i < getHeight(); i += 20) {
            g.drawLine(0, i, getWidth(), i);
        }
        // draw nodes and connections
        Graphics2D g2 = (Graphics2D) g;
        // this is a problem
        int[] order = Blackboard.getInstance().travelingOrder();
        g2.setColor(new Color(74, 136, 98, 255));
        for (int i = 0; i < Blackboard.getInstance().size() - 1; i++) {
            Blackboard.getInstance().get(order[i]).drawConnect(Blackboard.getInstance().get(order[i + 1]), g2);
        }
        // Draw all nodes
        for (int i = 0; i < Blackboard.getInstance().size(); i++) {
            Blackboard.getInstance().get(i).draw(g2);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}