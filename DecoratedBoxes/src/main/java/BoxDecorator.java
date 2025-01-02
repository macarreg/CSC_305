import java.awt.Graphics;

public abstract class BoxDecorator extends Box {
    protected Box decoratedBox;

    public BoxDecorator(Box decoratedBox) {
        super(decoratedBox.x, decoratedBox.y, decoratedBox.width, decoratedBox.height);
        this.decoratedBox = decoratedBox;
    }

    @Override
    public void draw(Graphics g) {
        decoratedBox.draw(g);
    }
}

