import javax.swing.JPanel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BoardPanel extends JPanel implements PropertyChangeListener {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the boxes on the board
        for (Box box : GameData.getInstance().getBoxes()) {
            box.draw(g);
        }

        // Display result message if game is over
        String message = "";
        if (GameData.getInstance().isTie()) {
            g.setColor(Color.YELLOW);
            message = "It's a Tie!";
        } else if (GameData.getInstance().isGameOver()) {
            g.setColor(GameData.getInstance().isXTurn() ? Color.GREEN : Color.RED);
            message = GameData.getInstance().isXTurn() ? "X Wins!" : "O Wins!";
        }

        if (!message.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            int x = (getWidth() - g.getFontMetrics().stringWidth(message)) / 2;
            int y = getHeight() / 2;
            g.drawString(message, x, y);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();  // Redraw when properties change
    }
}
