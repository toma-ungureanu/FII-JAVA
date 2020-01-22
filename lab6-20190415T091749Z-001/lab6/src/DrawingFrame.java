import javax.swing.*;
import java.awt.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class DrawingFrame extends JFrame
{
    private Canvas canvas;
    private ControlPanel control;
    private Toolbar toolbar;

    public DrawingFrame()
    {
        super("Visual Graph Manager");
        init();
    }

    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        control = new ControlPanel(this);
        canvas = new Canvas(this);
        toolbar = new Toolbar(this);
        control.setBackground(Color.BLUE);
        canvas.setBackground(Color.WHITE);
        toolbar.setBackground(Color.ORANGE);
        this.getContentPane().add(toolbar,BorderLayout.NORTH);
        this.getContentPane().add(control, BorderLayout.SOUTH);
        this.getContentPane().add(canvas,BorderLayout.CENTER);
    }
}
