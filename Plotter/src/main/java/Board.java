import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

/**
 * Singleton class that represents an observable Board (storage for data).
 *
 * @author javiergs
 * @version 1.0
 */
public class Board extends PropertyChangeSupport {

    private LinkedList<Integer> values;
    private static Board instance = null;

    private Board() {
        super(new Object());
        values = new LinkedList<>();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public LinkedList<Integer> getValues() {
        return values;
    }

    public void addValue(int value) {
        values.add(value);
        if (values.size() > 17)
            values.removeFirst();
        firePropertyChange("values", null, values.getLast());
    }

}
