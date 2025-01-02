import java.awt.*;

/**
 * Abstract class that represents a marker object.
 * This class is part of the decorator pattern.
 *
 * @author javiergs
 * @version 1.0
 */
public abstract class Marker {

    public abstract void draw(Graphics g, int value);

}