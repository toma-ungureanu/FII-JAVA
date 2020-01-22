package gui.controlpanel;

import backend.catalog.Catalog;
import backend.command.OpenCommand;
import backend.command.ReportCommand;
import backend.graph.Graph;
import gui.catalog.CatalogFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author Toma-Florin Ungureanu
 */
public class ControlPanel extends JPanel
{
    private final CatalogFrame frame;
    private JButton loadBtn = new JButton("Load");
    private JButton saveBtn = new JButton("Save");
    private JButton openBtn = new JButton("Open");
    private JButton reportBtn = new JButton("Report");

    public ControlPanel(CatalogFrame frame)
    {
        this.frame = frame;
        init();
    }

    public String[] getRowAt(DefaultTableModel table, int row)
    {
        String[] result = new String[5];

        for (int column = 0; column < result.length; column++)
        {
            result[column] = (String)table.getValueAt(row, column);
        }

        return result;
    }

    private JFileChooser jFileChooserLoadNSave()
    {
        JFileChooser jfc = new JFileChooser(new File
                (System.getProperty("user.home") +
                        System.getProperty("file.separator") +
                        "IdeaProjects" +
                        System.getProperty("file.separator") +
                        "lab4") +
                System.getProperty("file.separator") +
                "graphs" +
                System.getProperty("file.separator") +
                "catalog");
        return jfc;
    }

    private void loadImpl()
    {
        JFileChooser jfc = jFileChooserLoadNSave();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            Catalog catalog = new Catalog(jfc.getCurrentDirectory().getParent());
            catalog.load("\\" + jfc.getSelectedFile().getName());
            String[] graphInfo = new String[5];
            Object verifyCatalog = catalog.getCatalogList();
            DefaultTableModel model = null;
            if (verifyCatalog != null)
            {
                model = catalog.getCatalogList().getModel();
            }
            if (catalog.getCatalogList() != null)
            {
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    frame.list.addGraph(getRowAt(model,i));
                }
            } else
            {
                for (Graph graph : catalog.getGraphs())
                {
                    graphInfo[0] = graph.getName();
                    graphInfo[1] = "TBD";
                    graphInfo[2] = "TBD";
                    graphInfo[3] = graph.getPathToImage();
                    graphInfo[4] = graph.getPathToRep();
                    frame.list.addGraph(graphInfo);
                }
            }
        }
    }

    private void saveImpl()
    {
        JFileChooser jfc = jFileChooserLoadNSave();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            Catalog catalog = new Catalog(jfc.getCurrentDirectory().getParent());
            catalog.save(frame.list, "\\" + jfc.getSelectedFile().getName());
        }
    }

    private void reportImpl()
    {
        JFileChooser jfc = jFileChooserLoadNSave();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            Catalog catalog = new Catalog(jfc.getCurrentDirectory().getParent());
            catalog.setCatalogList(frame.list);
            ReportCommand reportCommand = new ReportCommand(catalog, jfc.getSelectedFile().getName());

        }
    }

    private void openImpl()
    {
        JFileChooser jfc = jFileChooserLoadNSave();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            Catalog catalog = new Catalog(jfc.getCurrentDirectory().getParent());
            catalog.setCatalogList(frame.list);
            OpenCommand openCommand = new OpenCommand(catalog, jfc.getSelectedFile().getAbsolutePath());
        }
    }

    private void init()
    {
        loadBtn.addActionListener((ActionEvent e) ->
        {
            loadImpl();
        });
        saveBtn.addActionListener((ActionEvent e) ->
        {
            saveImpl();
        });
        reportBtn.addActionListener((ActionEvent e) ->
        {
            reportImpl();
        });
        openBtn.addActionListener((ActionEvent e) ->
        {
            openImpl();
        });
        add(loadBtn);
        add(saveBtn);
        add(openBtn);
        add(reportBtn);
    }
}
