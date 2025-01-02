import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SVGTextPanel extends JTextArea implements PropertyChangeListener {

    public SVGTextPanel(SVGData svgData) {
        svgData.addPropertyChangeListener(this);
        setEditable(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("svgContent".equals(evt.getPropertyName())) {
            setText((String) evt.getNewValue());
        }
    }
}
