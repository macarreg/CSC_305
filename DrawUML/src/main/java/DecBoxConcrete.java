import java.awt.*;

public class DecBoxConcrete extends DecoratorBox {
    public DecBoxConcrete(Box selectedBox) {
        super(selectedBox);
    }

    @Override
    public void draw(Graphics2D g) {
        // draws circle in right-bottom
        decoratedBox.draw(g);
        int centerX = x + width;
        int centerY = y + ((height * 3) / 4);
        g.setColor(Color.YELLOW);
        g.fillOval(centerX - 10, centerY - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString("CO", centerX - 5, centerY + 5);
    }
}
