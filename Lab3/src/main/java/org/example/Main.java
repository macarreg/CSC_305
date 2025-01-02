package org.example;

import javax.swing.*;

/**
 * Examples of Maven projects.
 * Also introduce you to JTabbedPane, a very useful component just for fun
 *
 * @author javiergs
 * @version 1.0
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(800, 600);
        main.setTitle("Maven Examples");
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setResizable(false);
    }

    public Main() {
        // just for fun let me introduce you to JTabbedPane
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab("JSoup Example", new JsoupPanel());
        add(tabPane);
    }

}