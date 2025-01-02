
import java.awt.*;

/**
 * The Component interface represents a component.
 */
public interface Component {

    void drawShape(Graphics g);

    int getX();

    int getY();

    int getW();

    int getH();

    void move(int dx, int dy);

}
