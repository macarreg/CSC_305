import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

class Repository extends PropertyChangeSupport {
    private static final int MARGIN = 20;
    private static Repository instance;
    private final List<Point> cities = new ArrayList<>();
    private List<Point> corners;
    private Point center;

    private Repository() {
        super(new Object());
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void addCity(Point city) {
        cities.add(city);
    }
    public List<Point> getCorners() {
        return corners;
    }

    public Point getCenter() {
        return center;
    }

    public List<Point> getCities() {
        return cities;
    }

    public void setCorners(List<Point> corners) {
        this.corners = corners;
    }
    public void setCenter(Point center) {
        this.center = center;
    }

    public void clearData() {
        cities.clear();
        corners = null;
        center = null;
        firePropertyChange("city", null, cities);
        firePropertyChange("stats", null, cities);
    }

    public Point scale(Point value, Dimension dimensions) {
        List<Point> points = getCities();
        if (points.isEmpty()) {
            return new Point(0, 0);
        }

        // Calculate min and max for X and Y
        int minX = points.stream().mapToInt(p -> (int) p.getX()).min().orElse(0);
        int maxX = points.stream().mapToInt(p -> (int) p.getX()).max().orElse(1);

        int minY = points.stream().mapToInt(p -> (int) p.getY()).min().orElse(0);
        int maxY = points.stream().mapToInt(p -> (int) p.getY()).max().orElse(1);

        // Scale X and Y
        int scaledX = MARGIN + (int) ((value.x - minX) * (dimensions.width - 2.0 * MARGIN) / (maxX - minX));
        int scaledY = MARGIN + (int) ((value.y - minY) * (dimensions.height - 2.0 * MARGIN) / (maxY - minY));

        return new Point(scaledX, scaledY);
    }

}
