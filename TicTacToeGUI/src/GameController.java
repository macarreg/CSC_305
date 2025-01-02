import java.awt.event.*;

public class GameController implements MouseListener {
    @Override
    public void mousePressed(MouseEvent e) {
        if (GameData.getInstance().isGameOver()) return;  // Ignore clicks if game is over

        for (Box box : GameData.getInstance().getBoxes()) {
            if (box.contains(e.getX(), e.getY()) && !box.isFilled()) {
                String mark = GameData.getInstance().isXTurn() ? "X" : "O";
                box.setMark(mark);
                GameData.getInstance().checkForWin();
                if (!GameData.getInstance().isGameOver()) {
                    GameData.getInstance().toggleTurn();
                }
                GameData.getInstance().repaint();
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
