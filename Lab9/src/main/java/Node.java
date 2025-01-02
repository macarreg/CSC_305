import java.awt.*;

/**
 * Node represents a node in the graph
 *
 * @author javiergs
 * @version 1.0
 */
public class Node {

    private final Rectangle bounds;
    private String label;
    private final static int DEFAULT_SIZE = 10;
    private static int size = DEFAULT_SIZE;
    private final StrategyDraw strategyDraw = new StrategyDrawLineCurved();

    public Node(String label, int x, int y, int w, int h) {
        bounds = new Rectangle(x, y, w, h);
        this.label = label;
    }

    public Node (String label, int x, int y, int nodeSize) {
        this(label, x, y, nodeSize, nodeSize);
    }

    public int getX() {
        return bounds.x;
    }

    public int getY() {
        return bounds.y;
    }

    public void draw(Graphics g) {
        //Color c = g.getColor();
        int x = bounds.x, y = bounds.y, h = bounds.height, w = bounds.width;
        g.drawOval(x, y, w, h);
        //g.setColor(Color.WHITE);
        g.fillOval(x + 1, y + 1, w - 1, h - 1);
        //g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.PLAIN, 10));
        g.drawString(label, x + w, y);
        //g.setColor(c);
    }

    public void move(int x, int y) {
        bounds.x = x;
        bounds.y = y;
    }

    public void drawConnect(Node b, Graphics2D g) {
        strategyDraw.drawConnect(this, b, g);
    }

    public boolean contains(int x, int y) {
        return bounds.contains(x, y);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Point center() {
        return new Point(
                bounds.x + bounds.width / 2,
                bounds.y + bounds.height / 2
        );
    }

}