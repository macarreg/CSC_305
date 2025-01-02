import java.awt.*;

public class DecBoxStrategy extends DecoratorBox {
    public DecBoxStrategy(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in left-bottom
        decoratedBox.draw(g);
        int centerY = y + ((height * 3) / 4);
        g.setColor(Color.GREEN);
        g.fillOval(x - 10, centerY - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("S", x - 5, centerY + 5);
    }
}
