package toolbar;

import drawings.DrawingFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Toma-Florin Ungureanu
 */
public class Toolbar extends JPanel
{
    private final DrawingFrame frame;
    private JButton addButton = new JButton("Add a vertex");
    private JLabel xCoordLabel = new JLabel("X coordinate:");
    private JLabel yCoordLabel = new JLabel("Y coordinate:");
    private JTextField xCoord = new JTextField();
    private JTextField yCoord = new JTextField();

    public Toolbar(DrawingFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        xCoord.setPreferredSize(new Dimension(100, 26));
        yCoord.setPreferredSize(new Dimension(100, 26));

        addButton.addActionListener((ActionEvent e) ->
        {
            String xCoord = this.xCoord.getText();
            int xCoordInt = Integer.parseInt(xCoord);
            String yCoord = this.yCoord.getText();
            int yCoordInt = Integer.parseInt(yCoord);
            if (yCoordInt > 25 && yCoordInt < 662)
            {
                frame.getCanvas().drawNode(xCoordInt, yCoordInt);
            }
        });

        add(addButton);
        add(xCoordLabel);
        add(xCoord);
        add(yCoordLabel);
        add(yCoord);

    }
}
