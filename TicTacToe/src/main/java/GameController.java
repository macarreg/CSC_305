import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameController implements MouseListener, ComponentListener {
    private final BoardPanel panel;

    public GameController(BoardPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (GameData.getInstance().isGameOver()) return;  // Ignore clicks if the game is over

        for (Box box : GameData.getInstance().getBoxes()) {
            if (box.contains(e.getX(), e.getY()) && !box.isFilled()) {
                String symbol = GameData.getInstance().isXTurn() ? "X" : "O";
                box.setSymbol(symbol);
                GameData.getInstance().checkForWin();  // Check if the game has ended
                if (!GameData.getInstance().isGameOver()) {
                    GameData.getInstance().toggleTurn();  // Switch turns
                }
                panel.repaint();  // Force a repaint after each move
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public void componentResized(ComponentEvent e) {
        GameData.getInstance().setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
        GameData.getInstance().recalculate();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
