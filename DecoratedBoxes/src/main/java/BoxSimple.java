import java.awt.Color;
import java.awt.Graphics;

public class BoxSimple extends Box {
    public BoxSimple(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }
}

