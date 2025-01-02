
import java.awt.*;
import java.io.*;

/**
 * The Circle class represents a circle shape.
 */
public class Circle extends Shape implements Serializable {

    public Circle(Color color, int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g.fillOval(x, y, w, w);
        if (Officer.getInstance().getBaseShapeComponent() != null && Officer.getInstance().getBaseShapeComponent().equals(this)) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawOval(x, y, w, w);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
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
        int centerX = this.x + this.w / 2;
        int centerY = this.y + this.h / 2;
        int radius = this.w / 2;

        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2);
    }


    @Override
    public String toString() {
        String c = "red";
        if (color != Color.RED){
            c = "blue";
        }
        return "<circle r=\"" + w + "\" cx=\"" + x + "\" cy=\"" + y +
                "\" fill= \"" + c + "\"/>\n";
    }

}