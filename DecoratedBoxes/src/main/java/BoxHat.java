import java.awt.Color;
import java.awt.Graphics;

public class BoxHat extends BoxDecorator {
    public BoxHat(Box decoratedBox) {
        super(decoratedBox);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        g.fillRect(x - 5, y - 5, width + 10, 5);   // Brim of the hat
        g.fillRect(x + 5, y - 15, width - 10, 15);  // Top part of the hat
    }
}

