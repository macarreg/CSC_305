import java.awt.*;

public class DecBoxSingleton extends DecoratorBox {
    public DecBoxSingleton(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in bottom-left
        decoratedBox.draw(g);
        int centerX = x + width / 4;
        int centerY = y + height;
        g.setColor(Color.ORANGE);
        g.fillOval(centerX - 10, centerY - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("#1", centerX - 5, centerY + 5);
    }
}
