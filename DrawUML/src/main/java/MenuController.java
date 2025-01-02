import javax.swing.*;
import java.io.*;
import java.util.Stack;

public class MenuController {
    public void newProject() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new project?", "New Project", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            Repository.getInstance().getBoxes().clear();
            Repository.getInstance().firePropertyChange("newProject", null, null);
        }
    }

    public void openProject() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object boxes = ois.readObject();
                if (boxes instanceof Stack<?>) {
                    Repository.getInstance().getBoxes().clear();
                    Repository.getInstance().getBoxes().addAll((Stack<BasicBox>) boxes);
                    Repository.getInstance().firePropertyChange("openProject", null, null);
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error opening file: " + e.getMessage());
            }
        }
    }
    public void saveProject() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(Repository.getInstance().getBoxes());
                JOptionPane.showMessageDialog(null, "Project saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
            }
        }
    }

    public void exitApplication() {
        System.exit(0);
    }

    public void undoAction() {
        if (!Repository.getInstance().getUndoStack().isEmpty()) {
            Repository.getInstance().undo();
        }
    }

    public void aboutDialog() {
        String message = ("This App is used to make UML class diagrams. If you click the screen, a class will appear,\n" +
                " and you will be able to give it a name. You can select a box connector in the Menu to apply\n" +
                " connections between classes. As long as you have the 'None' option selected, you can rename, \n" +
                " decorate, and drag these classes around freely. There are patterns you can apply to classes \n" +
                "if you right click them. The trash in the bottom right allows you to remove unwanted classes, \n" +
                "and the undo button brings classes back once trashed. Symbols for box connections are as follows: \n" +
                "Aggregation = unfilled diamond; Composition = filled diamond; Association = regular arrow; \n" +
                "Inheritance = black arrow with triangle tip, Implementation = blue arrow with triangle tip.");
        JOptionPane.showMessageDialog(null, message);
    }
}
