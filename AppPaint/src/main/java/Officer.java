
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.Stack;

/**
 * The Officer class represents the officer that manages the shapes.
 */
public class Officer extends PropertyChangeSupport {
    private static Officer instance;

    private static final Stack<Component> shapes = new Stack<>();
    private static final Stack<Component> redoShapes = new Stack<>();
    private static JPanel drawPanel;
    private static Color color;
    private static String shape;
    private static Shape box;
    private static boolean savePerformed = false;
    private static Shape baseSelectedShape;
    private static Component selectedComponent;

    private Officer() {
        super(new Object());
    }

    public static Officer getInstance() {
        if (instance == null) {
            instance = new Officer();
        }
        return instance;
    }

    public JPanel getDrawPanel() {
        return drawPanel;
    }

    public void setDrawPanel(JPanel drawPanel) {
        Officer.drawPanel = drawPanel;
    }

    public Color getColor() {
        return color == null ? Color.RED : color;
    }

    public void setColor(Color color) {
        Officer.color = color;
        if (baseSelectedShape != null) {
            baseSelectedShape.color = color;
        }
        doSomething();
    }

    public String getShape() {
        return shape == null ? "Rectangle" : shape;
    }

    public void setShape(String shape) {
        Officer.shape = shape;
    }

    public void doSomething() {
        savePerformed = false;
        firePropertyChange("shapeChanges", null, shapes);
        drawPanel.repaint();
    }

    public Stack<Component> getStack() {
        return shapes;
    }

    public void pushToStack(Component shape) {
        if (!((shape.getW() == 0) && (shape.getH() == 0))) {
            Officer.shapes.add(shape);
            Officer.redoShapes.clear();
        }
    }

    public Shape getBox() {
        return box;
    }

    public void setBox(Shape box) {
        Officer.box = box;
    }

    public void newFile() {
        if (!shapes.isEmpty()) {
            if (!savePerformed) {
                int wantSave = JOptionPane.showConfirmDialog(drawPanel, "You have not saved this file. Would you like to save?", null, JOptionPane.YES_NO_OPTION);
                if (wantSave == JOptionPane.YES_OPTION) {
                    saveFile();
                } else if (wantSave == JOptionPane.NO_OPTION) {
                    shapes.clear();
                    redoShapes.clear();
                    doSomething();
                }
            } else {
                shapes.clear();
                redoShapes.clear();
                doSomething();
            }
        }
    }

    public void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files", "ser");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(drawPanel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".ser";
            File fileName = new File(filePath);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(shapes);
                savePerformed = true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(drawPanel, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files", "ser");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(drawPanel);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileName = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                shapes.clear();
                redoShapes.clear();
                Stack<Shape> loadedShapes = (Stack<Shape>) ois.readObject();
                shapes.addAll(loadedShapes);
                doSomething();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(drawPanel, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            System.out.println("Load operation canceled by user.");
        } else if (userSelection == JFileChooser.ERROR_OPTION) {
            System.err.println("Error in file chooser dialog.");
        }
    }

    public Shape getBaseShapeComponent() {
        return baseSelectedShape;
    }

    public void setBaseShapeComponent(Shape selectedShape) {
        Officer.baseSelectedShape = selectedShape;
    }

    public void setSelectedComponent(Component selectedComponent) {
        Officer.selectedComponent = selectedComponent;
    }


}