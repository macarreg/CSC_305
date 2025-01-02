import java.awt.*;

public class ConnectionComposition extends Connection {
    public ConnectionComposition(Box start, Box end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics2D g2) {
        // connects from right-top to left-top of box
        int startX = start.x + start.width;
        int startY = start.y + start.height / 4;
        int endX = end.x;
        int endY = end.y + end.height / 4;

        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, endX, endY);

        // Draw filled diamond near the start
        int[] xPoints = { startX, startX + 10, startX + 20, startX + 10 };
        int[] yPoints = { startY, startY - 5, startY, startY + 5 };
        g2.drawPolygon(xPoints, yPoints, 4);
        g2.fillPolygon(xPoints, yPoints, 4);
    }
}
