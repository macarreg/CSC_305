import java.util.ArrayList;
import java.util.List;

public class Board {
    private static Board instance;
    private final List<Panel> observers = new ArrayList<>();
    private final List<Box> boxes = new ArrayList<>();
    private Board(){}

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void addObserver(Panel observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Panel observer : observers) {
            observer.update();
        }
    }

    public void addBox(Box box) {
        boxes.add(box);
        notifyObservers();
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void handleClick(int x, int y) {
        boolean boxUpdated = false;

        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            if (box.contains(x, y)) {
                box = cycleDecoration(box);
                boxes.set(i, box);
                boxUpdated = true;
                break;
            }
        }

        if (!boxUpdated) {
            addBox(new BoxSimple(x, y, 50, 50));
        }
        notifyObservers();
    }

    private Box cycleDecoration(Box box) {
        if (box instanceof BoxSimple) {
            return new BoxEyes(box);
        } else if (box instanceof BoxEyes) {
            return new BoxMouth(box);
        } else if (box instanceof BoxMouth) {
            return new BoxHat(box);
        } else if (box instanceof BoxHat) {
            return new BoxSimple(box.x, box.y, box.width, box.height);
        }
        return box;
    }
}
