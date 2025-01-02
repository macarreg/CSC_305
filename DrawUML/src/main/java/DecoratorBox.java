import java.awt.*;

abstract class DecoratorBox extends Box {
    protected Box decoratedBox;

    public DecoratorBox(Box decoratedBox) {
        super(decoratedBox.x, decoratedBox.y, decoratedBox.width, decoratedBox.height);
        this.decoratedBox = decoratedBox;
    }

    @Override
    public void draw(Graphics2D g) {
        decoratedBox.draw(g);
    }

    @Override
    public boolean contains(int px, int py) {
        return decoratedBox.contains(px, py);
    }
    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

