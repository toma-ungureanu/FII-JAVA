package gui.catalog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;

/**
 * @author Toma-Florin Ungureanu
 */
public class CatalogList extends JTable implements Serializable
{
    private DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"Name","Type","Vertices","defPath","imgPath"});

    /**
     *
     */
    public CatalogList()
    {
        this.setModel(model);
    }

    /**
     * @param item
     */
    public void addGraph(String[] item)
    {
        model.addRow(item);
    }

    public DefaultTableModel getModel()
    {
        return model;
    }
}