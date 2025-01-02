import java.awt.*;

public class ConnectionAssociation extends Connection {
    public ConnectionAssociation(Box start, Box end) {
        super(start, end);
    }

    @Override
    public void draw(Graphics2D g2) {
        // connects from left-bottom to right-bottom of box
        int startX = start.x;
        int startY = start.y + ((start.height * 3) / 4);
        int endX = end.x + end.height;
        int endY = end.y + ((end.height * 3) / 4);

        g2.setColor(Color.BLACK);
        g2.drawLine(startX, startY, endX, endY);

        // Draw > near the end
        g2.drawLine(endX, endY, endX + 10, endY + 10);
        g2.drawLine(endX, endY, endX + 10, endY - 10);
    }
}
