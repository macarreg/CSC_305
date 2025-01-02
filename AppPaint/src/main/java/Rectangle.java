
import java.awt.*;
import java.io.*;

/**
 * The Rectangle class represents a rectangle shape.
 */
public class Rectangle extends Shape implements Serializable {

    public Rectangle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g.fillRect(x, y, w, h);
        if (Officer.getInstance().getBaseShapeComponent() != null && Officer.getInstance().getBaseShapeComponent().equals(this)) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(x, y, w, h);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.writeInt(color.getRGB());
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(w);
        out.writeInt(h);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        color = new Color(in.readInt());
        x = in.readInt();
        y = in.readInt();
        w = in.readInt();
        h = in.readInt();
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h;
    }

    @Override
    public String toString() {
        String c = "red";
        if (color != Color.RED){
            c = "blue";
        }
        return "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + w +
                "\" height=\"" + h + "\" fill= \"" + c + "\"/>\n";
    }

}