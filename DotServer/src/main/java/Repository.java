import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

/**
 * A Singleton as data repository
 *
 * @author javiergs
 * @version 1.0
 */
public class Repository extends PropertyChangeSupport {

    private static Repository instance;
    private final Stack<Point> points = new Stack<>();

    private Repository() {
        super(new Object());
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }


    public Stack<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.push(point);
        firePropertyChange("points", null, point);
    }
}
