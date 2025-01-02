import java.awt.*;

public abstract class Box {
    private String label;
    protected int x, y, width, height;

    public Box(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D g);
    public abstract boolean contains(int px, int py);
    public abstract void move(int newX, int newY);

    public String getLabel() {return label;}
    public void setLabel(String label) {this.label = label;}
}

