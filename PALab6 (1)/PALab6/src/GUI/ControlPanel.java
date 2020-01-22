package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class ControlPanel extends JPanel
{
    private DrawingFrame frame;
    private final JFileChooser fc = new JFileChooser();


    private JButton saveBtn = new JButton("Save");
    private JButton loadBtn = new JButton("Load");
    private JButton resetBtn = new JButton("Reset");
    private JButton prettyBtn = new JButton("Pretty");
    private JCheckBox deleteCheckBox = new JCheckBox("Delete");

    public ControlPanel(DrawingFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(prettyBtn);
        add(deleteCheckBox);

        loadBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fc.showOpenDialog(ControlPanel.this);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();

                    System.out.println("Opening: " + file.getPath() + ".");


                } else
                {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });

        saveBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int returnVal = fc.showSaveDialog(ControlPanel.this);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    //This is where a real application would save the file.
                    System.out.println("Saving: " + file.getName() + ".");

                } else
                {
                    System.out.println("Save command cancelled by user.");
                }
            }
        });

        resetBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.canvas.clearCanvas();
            }
        });

        prettyBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.canvas.pretty();
            }
        });

        deleteCheckBox.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                frame.canvas.setDeleteMode(e.getStateChange() == 1 ? true : false);
            }
        });
    }
}
