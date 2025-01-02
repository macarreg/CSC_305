import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatPanel extends JPanel {
    private final JTextArea textArea;

    public ChatPanel() {
        setLayout(new BorderLayout());
        textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void updateText(List<String> moveHistory) {
        // Display the most recent moves and messages in the chat
        textArea.setText(String.join("\n", moveHistory));
    }

    public void clearText() {
        textArea.setText("");  // Clears the chat panel if needed
    }

}
