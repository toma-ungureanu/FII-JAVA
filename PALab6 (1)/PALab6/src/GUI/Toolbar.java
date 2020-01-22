package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel
{
    private DrawingFrame frame;
    private JLabel sizeLabel = new JLabel("Node Size ");
    private JSpinner sizeField = new JSpinner(new SpinnerNumberModel(50, 0, 1000, 1));

    private JButton colorBtn = new JButton("Color");


    public Toolbar(DrawingFrame frame)
    {
        this.frame = frame;
        setBorder(BorderFactory.createTitledBorder("Toolbar"));

        setLayout(new FlowLayout());

        add(sizeLabel);
        add(sizeField);
        add(colorBtn);


        colorBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.red);
                frame.canvas.setChosenColor(newColor);
            }
        });
    }

    public int getNodeSize()
    {
        return (Integer) sizeField.getModel().getValue();
    }
}
