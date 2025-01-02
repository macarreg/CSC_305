import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawAreaListener implements MouseListener, MouseMotionListener {

    private int preX, preY;
    private int selected = -1;
    private int nodeSize = 10;

    private int citySelected(MouseEvent e) {
        int citySelected = -1;
        for (int i = 0; i < Blackboard.getInstance().size(); i++) {
            if (Blackboard.getInstance().get(i).contains(e.getX(), e.getY())) {
                citySelected = i;
            }
        }
        return citySelected;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (citySelected(e) == -1) {
            String name = "unnamed" + Blackboard.getInstance().size();
            Node newNode = new Node(name, e.getX(), e.getY(), nodeSize);
            nodeSize += 5;
            String result = (String) JOptionPane.showInputDialog(
                    e.getComponent(),
                    "Type the name of the new city",
                    "City Name",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    name
            );
            if (result != null && !result.isEmpty()) {
                Blackboard.getInstance().add(newNode);
                newNode.setLabel(result);
            }
            Blackboard.getInstance().repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selected = citySelected(e);
        if (selected == -1) return;
        Node node = Blackboard.getInstance().get(selected);
        preX = node.getX() - e.getX();
        preY = node.getY() - e.getY();
        node.move(preX + e.getX(), preY + e.getY());
        Blackboard.getInstance().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected == -1) return;
        Node node = Blackboard.getInstance().get(selected);
        node.move(preX + e.getX(), preY + e.getY());
        Blackboard.getInstance().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected == -1) return;
        Node node = Blackboard.getInstance().get(selected);
        node.move(preX + e.getX(), preY + e.getY());
        Blackboard.getInstance().repaint();
        selected = citySelected(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}