import java.awt.*;

public class DecBoxObserver extends DecoratorBox {
    public DecBoxObserver(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in top-left
        decoratedBox.draw(g);
        int centerX = x + width / 4;
        g.setColor(Color.BLUE);
        g.fillOval(centerX - 10, y - 10, 20, 20);
        g.setColor(Color.WHITE);
        g.drawString("OO", centerX - 5, y + 5);
    }
}
