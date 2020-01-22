package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawingFrame extends JFrame {
    public Canvas canvas = new Canvas(this);
    public Toolbar toolbar = new Toolbar(this);
    public ControlPanel controlPanel = new ControlPanel(this);

    public DrawingFrame() {
        super("Visual Graph Manager");
        init();
    }
    private void init() {
        setSize(500, 500);
        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //pack();
    }



    public static void main(String[] args){

        new DrawingFrame().setVisible(true);
    }
}
