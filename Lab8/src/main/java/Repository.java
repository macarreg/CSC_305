import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
public class Repository extends PropertyChangeSupport {
    private static Repository instance;
    private final List<Point> points = new ArrayList<>();

    public Repository (){
        super(new Object());
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
        firePropertyChange("points", null, point);
        if (points.size() > 10) {
            removePoint();
        }
    }
    public void removePoint() {
        firePropertyChange("points", null, points.remove(0));
    }
}
