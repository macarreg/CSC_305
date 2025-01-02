import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BoardPanel extends JPanel implements PropertyChangeListener {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Box box : GameData.getInstance().getBoxes()) {
            box.draw(g);
        }
        String message;
        g.setFont(new Font("Arial", Font.BOLD, 40));

        if (GameData.getInstance().isTie()) {
            g.setColor(Color.BLUE);
            message = "Cat's Game!";
            g.drawString(message, getWidth() / 2 - g.getFontMetrics().stringWidth(message) / 2, getHeight() / 2);
        } else if (GameData.getInstance().isGameOver() && GameData.getInstance().isXTurn()) {
            g.setColor(Color.GREEN);
            message = "X Wins!";
            g.drawString(message, getWidth() / 2 - g.getFontMetrics().stringWidth(message) / 2, getHeight() / 2);
        } else if (GameData.getInstance().isGameOver()) {
            g.setColor(Color.RED);
            message = "O Wins!";
            g.drawString(message, getWidth() / 2 - g.getFontMetrics().stringWidth(message) / 2, getHeight() / 2);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
