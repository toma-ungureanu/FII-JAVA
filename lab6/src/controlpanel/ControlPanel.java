package controlpanel;

import drawings.DrawingFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * @author Toma-Florin Ungureanu
 */
public class ControlPanel extends JPanel
{
    private final DrawingFrame frame;
    private JButton loadBtn = new JButton("Load");
    private JButton saveBtn = new JButton("Save");
    private JButton resetBtn = new JButton("Reset");

    public ControlPanel(DrawingFrame frame)
    {
        this.frame = frame;
        init();
    }


    private void init()
    {
        loadBtn.addActionListener((ActionEvent e) ->
        {
           // loadImpl();
        });
        saveBtn.addActionListener((ActionEvent e) ->
        {
           // saveImpl();
        });
        resetBtn.addActionListener((ActionEvent e) ->
        {
           // resetImpl();
        });

        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
    }
}
