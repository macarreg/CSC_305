import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class UMLPanel extends JPanel implements PropertyChangeListener {

    public UMLPanel() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Repository repository = Repository.getInstance();

        for (Box box : repository.getBoxes()) {
            box.draw(g2);
            List<DecoratorBox> decorations = repository.getDecorations().get(box);
            if (decorations != null) {
                for (DecoratorBox decoration : decorations) {
                    decoration.draw(g2);
                }
            }
        }

        int trashX = getWidth() - 50;
        int trashY = getHeight() - 50;
        g2.drawRect(trashX, trashY, 40, 40);
        g2.drawString("Trash", trashX + 2, trashY + 20);
    }

    public boolean isOverTrashCan(int x, int y) {
        int trashX = getWidth() - 50;
        int trashY = getHeight() - 50;
        return x >= trashX && x <= trashX + 40 && y >= trashY && y <= trashY + 40;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
