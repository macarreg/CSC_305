import java.awt.Color;
import java.awt.Graphics;

public class BoxEyes extends BoxDecorator {
    public BoxEyes(Box decoratedBox) {
        super(decoratedBox);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.WHITE);
        g.fillOval(x + 7, y + 7, 16, 16); // Left eye
        g.fillOval(x + 27, y + 7, 16, 16); // Right eye
        g.setColor(Color.BLUE);
        g.fillOval(x + 14, y + 12, 6, 6);   // Left pupil
        g.fillOval(x + 30, y + 12, 6, 6);   // Right pupil
    }
}

