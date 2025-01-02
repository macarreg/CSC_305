import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodePanel extends JPanel implements PropertyChangeListener {
    private final JTextArea codeArea;
    private final Map<String, StringBuilder> codeMap;

    public CodePanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(codeArea);
        add(scrollPane, BorderLayout.CENTER);

        codeMap = new HashMap<>();
        Repository.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("selectedFile")) {
            String selectedFile = (String) evt.getNewValue();
            updateCodeForFile(selectedFile);
        } else if (evt.getPropertyName().equals("addBox") || evt.getPropertyName().equals("decorateBox") || evt.getPropertyName().equals("removeBox")) {
            updateAllCode();
        }
    }

    private void updateCodeForFile(String fileName) {
        StringBuilder code = new StringBuilder();
        if (codeMap.containsKey(fileName)) {
            code.append(codeMap.get(fileName));
        } else {
            code.append("// Code for ").append(fileName).append(" not available");
        }
        codeArea.setText(code.toString());
    }

    private void updateAllCode() {
        for (Box box : Repository.getInstance().getBoxes()) {
            String fileName = box.getLabel() + ".java";
            StringBuilder code = new StringBuilder();
            code.append("public class ").append(box.getLabel()).append(" implements PropertyChangeListener {\n");

            code.append("    public ").append(box.getLabel()).append("() {\n");
            code.append("        // constructor code\n");
            code.append("    }\n\n");
            code.append("    @Override\n");
            code.append("    public void propertyChange(PropertyChangeEvent evt) {\n");
            code.append("        // propertyChange method code\n");
            code.append("    }\n\n");

            List<DecoratorBox> decorations = Repository.getInstance().getDecorations().get(box);
            if (decorations != null) {
                for (DecoratorBox decoration : decorations) {
                    code.append("    ").append(decoration.getClass().getSimpleName()).append(" {\n");
                    code.append("        // ").append(decoration.getClass().getSimpleName()).append(" code\n");
                    code.append("    }\n\n");
                }
            }

            code.append("}");
            codeMap.put(fileName, code);
        }
    }
}