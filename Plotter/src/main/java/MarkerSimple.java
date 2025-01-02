import java.awt.*;
import java.util.LinkedList;

/**
 * Class that represents a simple marker.
 * This class is part of the decorator pattern.
 *
 * @author javiergs
 * @version 1.0
 */
public class MarkerSimple extends Marker {

    public void draw(Graphics g, int value) {
        g.setColor(Color.RED);
        LinkedList<Integer> values = Board.getInstance().getValues();
        for (int i = 0; i < values.size() - 2; i++) {
            g.drawLine(25 * i, values.get(i), 25 * (i + 1), values.get(i + 1));
        }
    }

}