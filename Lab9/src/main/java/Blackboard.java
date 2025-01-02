import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Blackboard class is a singleton that stores the nodes of the graph.
 * It also provides a method to save the data to a file and to read the data from a file.
 *
 * @author javiergs
 * @version 1.0
 */
public class Blackboard extends PropertyChangeSupport {

    private static Blackboard instance;
    private final ArrayList<Node> nodes = new ArrayList<>();

    private Strategy strategy = new StrategyAsQueue();

    protected Blackboard() {
        super(new Object());
    }

    public static Blackboard getInstance() {
        if (instance == null)
            instance = new Blackboard();
        return instance;
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public Node get(int index) {
        return nodes.get(index);
    }

    public int size() {
        return nodes.size();
    }

    public int[] travelingOrder() {
        return strategy.algorithm(nodes);
    }

    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }
}
