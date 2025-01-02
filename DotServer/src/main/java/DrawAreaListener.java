import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class DrawAreaListener implements MouseListener {
    private final Publisher publisher;

    public DrawAreaListener(Publisher publisher){this.publisher = publisher;}

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        Repository.getInstance().addPoint(point);
        publisher.publishPoint(point);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
