package gui.graph;

import gui.catalog.CatalogFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author Toma-Florin Ungureanu
 */
public class GraphForm extends JPanel
{
    private static Vector<String> graphType = new Vector<>(Arrays.asList("directed", "undirected"));
    private final CatalogFrame frame;
    private JButton addButton = new JButton("Add a graph");
    private JLabel titleLabel = new JLabel("Name of the graph");
    private JTextField nameField = new JTextField();
    private JComboBox<String> typeLabel = new JComboBox<>(graphType);
    private JSpinner verticesField = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
    private JButton defPathbtn = new JButton("Graph Rep");
    private JButton imgPathBtn = new JButton("Graph image");
    private String[] graphInformation = new String[5];

    /**
     * @param frame
     */
    public GraphForm(CatalogFrame frame)
    {
        this.frame = frame;
        init();
    }

    String genericPath()
    {
        File genericPath = new File( (System.getProperty("user.home") +
                System.getProperty("file.separator") +
                "IdeaProjects" +
                System.getProperty("file.separator") +
                "lab4") +
                System.getProperty("file.separator") +
                "graphs");

        return genericPath.getAbsolutePath();
    }

    /**
     *
     */
    private void init()
    {
        nameField.setPreferredSize(new Dimension(150, 24));
        add(addButton);
        add(titleLabel);
        add(nameField);
        add(typeLabel);
        add(verticesField);
        defPathbtn.addActionListener((ActionEvent e) ->
        {

            JFileChooser jfc = new JFileChooser(new File(
                    genericPath() +
                    System.getProperty("file.separator") +
                    "tgf"));
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                graphInformation[3] = jfc.getSelectedFile().getAbsolutePath();
            }
        });
        add(defPathbtn);
        imgPathBtn.addActionListener((ActionEvent e) ->
        {

            JFileChooser jfc = new JFileChooser(new File(genericPath() +
                    System.getProperty("file.separator") +
                    "images"));
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
                graphInformation[4] = jfc.getSelectedFile().getAbsolutePath();
            }
        });
        add(imgPathBtn);
        addButton.addActionListener((ActionEvent e) ->
        {
            graphInformation[0] = nameField.getText();
            graphInformation[1] = typeLabel.getSelectedItem().toString();
            graphInformation[2] = verticesField.getValue().toString();
            addGraph();
        });
    }

    /**
     *
     */
    private void addGraph()
    {
        frame.list.addGraph(graphInformation);
    }
}
