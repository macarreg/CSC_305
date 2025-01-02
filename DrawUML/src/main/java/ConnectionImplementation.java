import java.awt.*;

public class ConnectionImplementation extends Connection {
    public ConnectionImplementation(Box start, Box end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics2D g2) {
        // connects from top-left to bottom-left of box
        int startX = start.x + start.width / 4;
        int startY = start.y;
        int endX = end.x + end.width / 4;
        int endY = end.y + end.height;

        g2.setColor(Color.BLUE);
        g2.drawLine(startX, startY, endX, endY);

        // Draw triangle near the end
        int[] xPoints = { endX, endX + 10, endX - 10};
        int[] yPoints = { endY, endY + 10, endY + 10};
        g2.drawPolygon(xPoints, yPoints, 3);
    }
}
