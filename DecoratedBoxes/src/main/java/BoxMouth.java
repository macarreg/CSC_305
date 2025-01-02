import java.awt.Color;
import java.awt.Graphics;

public class BoxMouth extends BoxDecorator {
    public BoxMouth(Box decoratedBox) {
        super(decoratedBox);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x + 19, y + 30, 12, 7);
    }
}

