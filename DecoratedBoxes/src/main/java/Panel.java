import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {
    private final Board board;

    public Panel() {
        this.board = Board.getInstance();
        board.addObserver(this);
        setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                board.handleClick(e.getX(), e.getY());
            }
        });
    }

    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Box box : board.getBoxes()) {
            box.draw(g);
        }
    }
}

