import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class DrawAreaListener implements MouseListener, MouseMotionListener {

    private int preX, preY;
    private int selected = -1;
    private MouseEvent lastClick;

    private int boxSelected(MouseEvent e) {
        int boxSelected = -1;
        for (int i = 0; i < Repository.getInstance().getBoxes().size(); i++) {
            if (Repository.getInstance().getBoxes().get(i).contains(e.getX(), e.getY())) {
                boxSelected = i;
            }
        }
        return boxSelected;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int boxIndex = boxSelected(e);
        Repository repository = Repository.getInstance();

        if (SwingUtilities.isRightMouseButton(e) && boxIndex != -1) {
            showContextMenu(e, boxIndex);
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            if (repository.getConnectionType().equals("None")) {
                if (boxIndex == -1) {
                    // Create new box
                    String name = "Untitled" + repository.getBoxes().size();
                    name = getDialog(e, name);
                    if (name != null) {
                        BasicBox newBox = new BasicBox(name, e.getX(), e.getY(), 100, 50);
                        repository.addBox(newBox);
                    }
                } else {
                    // Rename existing box
                    Box box = repository.getBoxes().get(boxIndex);
                    String newName = getDialog(e, box.getLabel());
                    if (newName != null && !newName.equals(box.getLabel())) {
                        box.setLabel(newName);
                        repository.firePropertyChange("renameBox", null, box);
                    }
                }
            } else if (repository.getNeedToConnect()) {
                // Handle connections
                Box start = repository.getBoxes().get(boxSelected(lastClick));
                Box end = repository.getBoxes().get(boxIndex);
                Connection connection = createConnection(repository.getConnectionType(), start, end);
                repository.addConnection(start, end, connection);
                repository.setNeedToConnect(false);
            } else {
                repository.setNeedToConnect(true);
            }
        }
        lastClick = e;
    }


    private Connection createConnection(String type, Box start, Box end) {
        switch (type) {
            case "Association":
                return new ConnectionAssociation(start, end);
            case "Aggregation":
                return new ConnectionAggregation(start, end);
            case "Composition":
                return new ConnectionComposition(start, end);
            case "Inheritance":
                return new ConnectionInheritance(start, end);
            case "Implementation":
                return new ConnectionImplementation(start, end);
            default:
                throw new IllegalArgumentException("Invalid connection type: " + type);
        }
    }

    private void showContextMenu(MouseEvent e, int boxIndex) {
        JPopupMenu menu = new JPopupMenu("Patterns");
        String[] patterns = { "Observer", "Observable", "Singleton", "Decoration", "Concrete", "Chain", "Strategy" };

        for (String type : patterns) {
            JRadioButton pattern = new JRadioButton(type);
            pattern.addActionListener(event -> applyDecorator(boxIndex, type));
            menu.add(pattern);
        }
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    private void applyDecorator(int boxIndex, String decoratorType) {
        Repository repository = Repository.getInstance();
        Box selectedBox = repository.getBoxes().get(boxIndex);
        DecoratorBox decoratedBox = null;

        switch (decoratorType) {
            case "Observer":
                decoratedBox = new DecBoxObserver(selectedBox); // Extend for specific Singleton decorations
                break;
            case "Observable":
                decoratedBox = new DecBoxObservable(selectedBox); // Extend for Observer-related decorations
                break;
            case "Singleton":
                decoratedBox = new DecBoxSingleton(selectedBox); // Extend for general decorations
                break;
            case "Decoration":
                decoratedBox = new DecBoxDecoration(selectedBox); // Extend for Observer-related decorations
                break;
            case "Concrete":
                decoratedBox = new DecBoxConcrete(selectedBox); // Extend for general decorations
                break;
            case "Chain":
                decoratedBox = new DecBoxChain(selectedBox); // Extend for Observer-related decorations
                break;
            case "Strategy":
                decoratedBox = new DecBoxStrategy(selectedBox); // Extend for general decorations
                break;
        }

        if (null != decoratedBox) {
            repository.addDecoration(selectedBox, decoratedBox);
        }
    }

    private String getDialog(MouseEvent e, String name) {
        String label = (String) JOptionPane.showInputDialog(
                e.getComponent(),
                "Type the name of the new box",
                "Box Name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                name);
        return label;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selected = boxSelected(e);
        if (selected == -1) return;
        Box box = Repository.getInstance().getBoxes().get(selected);
        preX = box.x - e.getX();
        preY = box.y - e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected == -1) return;
        Box box = Repository.getInstance().getBoxes().get(selected);
        if (box != null) {
            box.move(e.getX() + preX, e.getY() + preY);

            List<DecoratorBox> decorations = Repository.getInstance().getDecorations().get(box);
            if (decorations != null) {
                for (DecoratorBox decoration : decorations) {
                    decoration.move(e.getX(), e.getY());
                }
            }
            Repository.getInstance().firePropertyChange("moveBox", null, null);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected != -1) {
            UMLPanel panel = (UMLPanel) e.getComponent();
            Box box = Repository.getInstance().getBoxes().get(selected);

            if (panel.isOverTrashCan(e.getX(), e.getY())) {
                Repository.getInstance().removeBox(box);
            }
        }
        selected = -1;
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
