import java.awt.*;

public class DecBoxDecoration extends DecoratorBox {
    public DecBoxDecoration(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in bottom-right
        decoratedBox.draw(g);
        int centerX = x + ((width * 3) / 4);
        int centerY = y + height;
        g.setColor(Color.CYAN);
        g.fillOval(centerX - 10, centerY - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("D", centerX - 5, centerY + 5);
    }
}
