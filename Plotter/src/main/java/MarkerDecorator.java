import java.awt.*;

/**
 * Abstract class that represents a marker decorator object.
 * This class is part of the decorator pattern.
 *
 * @author javiergs
 * @version 1.0
 */
public abstract class MarkerDecorator extends Marker {

    protected Marker marker;

    public void add(Marker d) {
        marker = d;
    }

    @Override
    public void draw(Graphics g, int value) {
        if (marker != null)
            marker.draw(g, value);
    }

}
