import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class StrategyDrawLineCurved implements StrategyDraw {

    public void drawConnect(Node node, Node b, Graphics g) {
        if (node == null || b == null || node == b) {
            return;
        }
        Graphics2D g2d = (Graphics2D) g;
        int startX= node.center().x;
        int startY =       node.center().y;
        int endX =         b.center().x;
        int endY =        b.center().y;
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        int displacement = 50; // You can adjust this value
        int deltaX = endX - startX;
        int deltaY = endY - startY;
        double controlX = midX - deltaY * displacement / Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        double controlY = midY + deltaX * displacement / Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        QuadCurve2D q = new QuadCurve2D.Float(startX, startY, (int)controlX, (int)controlY, endX, endY);
        g2d.draw(q);
    }

}