import java.awt.*;

public class BasicBox extends Box {
    private String label;

    public BasicBox(String label, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.label = label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawString(label, x + 10, y + 20);
    }

    @Override
    public boolean contains(int px, int py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}

