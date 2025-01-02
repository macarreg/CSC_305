import java.awt.*;

public class ConnectionInheritance extends Connection {
    public ConnectionInheritance(Box start, Box end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics2D g2) {
        // connects from top-right to bottom-right of box
        int startX = start.x + ((start.width * 3) / 4);
        int startY = start.y;
        int endX = end.x + ((end.width * 3) / 4);
        int endY = end.y + end.height;

        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, endX, endY);

        // Draw triangle near the end
        int[] xPoints = { endX, endX + 10, endX - 10};
        int[] yPoints = { endY, endY + 10, endY + 10};
        g2.drawPolygon(xPoints, yPoints, 3);
    }
}
