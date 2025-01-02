import java.awt.*;
import java.util.LinkedList;

/**
 * Class that represents a bar marker decorator.
 * This class is part of the decorator pattern.
 *
 * @author javiergs
 * @version 1.0
 */
public class MarkerDecoratorBar extends MarkerDecorator {

    public void draw(Graphics g, int value) {
        super.draw(g, value);
        g.setColor(Color.GRAY);
        LinkedList<Integer> values = Board.getInstance().getValues();
        for (int i = 0; i < values.size() - 1; i++) {
            g.fillRect((25 * i) - 5, ((values.get(i)) - 5), 10, 600 - ((values.get(i)) - 5));
        }
    }

}