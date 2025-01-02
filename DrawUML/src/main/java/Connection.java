import java.awt.*;

public abstract class Connection extends DecoratorBox{
    protected Box start;
    protected Box end;

    public Connection(Box start, Box end) {
        super(start);
        this.start = start;
        this.end = end;
    }

    public Box getStart() {
        return start;
    }

    public Box getEnd() {
        return end;
    }

    @Override
    public void move(int deltaX, int deltaY) {}
    @Override
    public abstract void draw(Graphics2D g2);
}

