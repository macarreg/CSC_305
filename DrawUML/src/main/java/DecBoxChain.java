import java.awt.*;
public class DecBoxChain extends DecoratorBox {
    public DecBoxChain(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in right-top
        decoratedBox.draw(g);
        int centerX = x + width;
        int centerY = y + height / 4;
        g.setColor(Color.RED);
        g.fillOval(centerX - 10, centerY - 10, 20, 20);
        g.setColor(Color.WHITE);
        g.drawString("CM", centerX - 5, centerY + 5);
    }
}
