import java.awt.*;

public class DecBoxObservable extends DecoratorBox {
    public DecBoxObservable(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in top-right
        decoratedBox.draw(g);
        int centerX = x + ((width * 3) / 4);
        g.setColor(Color.CYAN);
        g.fillOval(centerX - 10, y - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("O", centerX - 5, y + 5);
    }
}
