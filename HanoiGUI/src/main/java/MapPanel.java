import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MapPanel extends JPanel implements Observer{
    private final GameController controller;

    public MapPanel(GameController controller) {
        this.controller = controller;
        setPreferredSize(new Dimension(500, 400));
        setLayout(new BorderLayout());

        JButton hintButton = new JButton("Show Hint");
        hintButton.setActionCommand("hint");
        hintButton.addActionListener(controller);
        add(hintButton, BorderLayout.SOUTH);

        addMouseListener(new DiskMouseAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 3; i++) {
            drawTower(g, i);
        }
    }

    private void drawTower(Graphics g, int towerIndex) {
        int x = towerIndex * 160 + 80;
        int y = getHeight() - 50;
        g.setColor(Color.BLACK);
        g.fillRect(x - 5, y - 200, 10, 200);

        Tower tower = GameData.getInstance().getTowers().get(towerIndex);
        int yPosition = y - 20;
        for (Integer diskSize : tower.getDisks()) {
            g.setColor(Color.BLUE);
            int diskWidth = diskSize * 20;
            g.fillRect(x - diskWidth / 2, yPosition, diskWidth, 20);
            yPosition -= 25;
        }
    }

    @Override
    public void update() {
        repaint();

        if (GameData.getInstance().isGameWon()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You have won the game!");
        }
    }

    private class DiskMouseAdapter extends MouseAdapter {
        private int fromTower = -1;

        @Override
        public void mousePressed(MouseEvent e) {
            int towerIndex = (e.getX() - 80) / 160;
            if (towerIndex >= 0 && towerIndex < 3) {
                fromTower = towerIndex;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (fromTower == -1) return;

            int toTower = (e.getX() - 80) / 160;
            if (toTower >= 0 && toTower < 3 && controller.moveDisk(fromTower, toTower)) {
                System.out.println("Move successful!");
            } else {
                System.out.println("Invalid move.");
            }
        }
    }
}
