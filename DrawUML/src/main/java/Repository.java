import java.beans.PropertyChangeSupport;
import java.util.*;

public class Repository extends PropertyChangeSupport {
    private static Repository instance;
    private final List<Box> boxes = new ArrayList<>();
    private final List<Connection> connections = new ArrayList<>();
    private final Map<Box, List<DecoratorBox>> boxDecorations = new HashMap<>();
    private final Stack<Box> undoStack = new Stack<>();
    private String connectionType = "None";
    private Boolean needToConnect = false;
    public List<Box> getBoxes() {return new ArrayList<>(boxes);}
    public String getConnectionType() {return connectionType;}
    public List<Connection> getConnections() {return new ArrayList<>(connections);}
    public Map<Box, List<DecoratorBox>> getDecorations() {return boxDecorations;}
    public Stack<Box> getUndoStack() {return undoStack;}
    public Boolean getNeedToConnect() {return needToConnect;}
    public void setNeedToConnect(Boolean needToConnect) {this.needToConnect = needToConnect;}

    private Repository() {
        super(new Object());
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public void addBox(Box box) {
        boxes.add(box);
        boxDecorations.put(box, new ArrayList<>());
        firePropertyChange("addBox", null, boxes);
    }

    public void addDecoration(Box box, DecoratorBox decoration) {
        if (boxDecorations.containsKey(box)) {
            boxDecorations.get(box).add(decoration);
            firePropertyChange("decorateBox", null, box);
        }
    }

    public void removeBox(Box box) {
        List<DecoratorBox> decorations = boxDecorations.get(box);
        if (decorations != null) {
            for (DecoratorBox decoration : decorations) {
                if (decoration instanceof Connection connection) {
                    // Remove the connection from the other box
                    Box otherBox = connection.getStart() == box ? connection.getEnd() : connection.getStart();
                    boxDecorations.get(otherBox).remove(connection);
                }
            }
        }
        boxes.remove(box);
        boxDecorations.remove(box);
        undoStack.push(box);
        firePropertyChange("removeBox", null, boxes);
    }

    public void addConnection(Box start, Box end, Connection connection) {
        addDecoration(start, connection);
        addDecoration(end, connection);
        firePropertyChange("addConnection", null, connection);
    }
    public void removeConnection(Connection connection) {
        connections.remove(connection);
        firePropertyChange("removeConnection", null, connections);
    }

    public void setConnectionType(String type) {
        this.connectionType = type;
        firePropertyChange("connectionType", null, type);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Box restoredBox = undoStack.pop();
            boxes.add(restoredBox);
            firePropertyChange("undo", null, boxes);
        }
    }
}
