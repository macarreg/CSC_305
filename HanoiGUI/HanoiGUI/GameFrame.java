import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GameFrame extends JFrame implements Observer {
    private final ChatPanel chatPanel;
    private final MapPanel mapPanel;

    public GameFrame(GameController controller) {
        chatPanel = new ChatPanel();
        mapPanel = new MapPanel(controller);

        setLayout(new BorderLayout());
        add(chatPanel, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void update() {
        mapPanel.repaint();
        chatPanel.updateText(GameData.getInstance().getMoveHistory());
    }
}
