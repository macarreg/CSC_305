import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class SVGData {
    private static SVGData instance;
    private List<SVGShape> shapes;
    private PropertyChangeSupport support;

    private SVGData() {
        shapes = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public static SVGData getInstance() {
        if (instance == null) {
            instance = new SVGData();
        }
        return instance;
    }

    public void addShape(SVGShape shape) {
        shapes.add(shape);
        support.firePropertyChange("shapes", null, shapes);
    }

    public List<SVGShape> getShapes() {
        return shapes;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public String getSVGContent() {
        StringBuilder svgContent = new StringBuilder("<svg width=\"800\" height=\"600\">");
        for (SVGShape shape : shapes) {
            svgContent.append(shape.toSVG());
        }
        svgContent.append("</svg>");
        return svgContent.toString();
    }
}
