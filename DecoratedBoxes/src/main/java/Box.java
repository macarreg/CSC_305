import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Box {
    protected int x, y, width, height;

    public Box(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics g);

    public boolean contains(int px, int py) {
        return new Rectangle(x, y, width, height).contains(px, py);
    }
}

