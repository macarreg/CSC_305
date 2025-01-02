import java.awt.*;

public class ConnectionAggregation extends Connection {

    public ConnectionAggregation(Box start, Box end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics2D g2) {
        // connects from right-bottom to left-bottom of box
        int startX = start.x+ start.width;
        int startY = start.y + ((start.height * 3) / 4);
        int endX = end.x;
        int endY = end.y + ((end.height * 3) / 4);

        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, endX, endY);

        // Draw unfilled diamond near the start
        int[] xPoints = { startX, startX + 10, startX + 20, startX + 10 };
        int[] yPoints = { startY, startY - 5, startY, startY + 5 };
        g2.drawPolygon(xPoints, yPoints, 4);
    }
}

