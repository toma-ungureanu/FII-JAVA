package drawings;

import controlpanel.ControlPanel;
import graph.Graph;
import toolbar.Toolbar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class DrawingFrame extends JFrame
{
    private Canvas canvas;
    private Graph graph;
    private ControlPanel control;
    private Toolbar toolbar;

    public DrawingFrame()
    {
        super("Visual Graph Manager");
        init();
    }

    public Toolbar getToolbar()
    {
        return this.toolbar;
    }

    public ControlPanel getControlPanel()
    {
        return this.control;
    }

    public Canvas getCanvas()
    {
        return this.canvas;
    }

    public Graph getGraph() { return  this.graph; }

    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 800);

        control = new ControlPanel(this);
        canvas = new Canvas(this, graph);
        canvas.setSize(1200,800);
        toolbar = new Toolbar(this);
        control.setBackground(Color.BLUE);
        canvas.setBackground(Color.WHITE);
        toolbar.setBackground(Color.ORANGE);

        this.getContentPane().add(toolbar,BorderLayout.NORTH);
        this.getContentPane().add(control, BorderLayout.SOUTH);
        this.getContentPane().add(canvas,BorderLayout.CENTER);
    }
}
