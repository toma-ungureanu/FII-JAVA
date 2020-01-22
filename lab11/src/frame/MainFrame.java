package frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class MainFrame extends JFrame
{
    ControlPanel controlPanel = new ControlPanel(this);
    DesignPanel designPanel = new DesignPanel(this);

    public MainFrame()
    {
        super("Swing Designer");
        init();
    }

    private void init()
    {
        this.setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(controlPanel,  BorderLayout.NORTH);
        this.add(designPanel,  BorderLayout.CENTER);
        pack();
    }

    public static void main(String[] args)
    {
        new MainFrame().setVisible(true);
    }
}
