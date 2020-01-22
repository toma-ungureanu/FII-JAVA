import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
public class TableExample {
    public static void main(String[] args) {
//Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TableExample().BuildGUI();
            }
        });
    }
    public void BuildGUI()
    {
        JFrame guiFrame = new JFrame();
//make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Creating a Table Example");
        guiFrame.setSize(700,860);
//This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
//Create a two dimensional array to hold the data for the JTable.
        Object[][] data = {{1,1,1},{2,2,2},{3,3,3},{4,4,4}};
//A string array containing the column names for the JTable.
        String[] columnNames = {"Column 1","Column 2","Column 3"};
//Create the JTable using the data array and column name array.
        JTable exampleJTable = new JTable(data, columnNames);
//Create a JScrollPane to contain for the JTable
        JScrollPane sp = new JScrollPane(exampleJTable);
//The JTable will provides methods which access the DefaultTabelModel.
//created when the JTable object was created
        System.out.println(exampleJTable.getValueAt(2, 2));
//The DefaultTableModel can be acessed through the getModel method.
        TableModel tabModel = exampleJTable.getModel();
//Provides the same output as the exampleJTable.getValueAt method call
//above.
        System.out.println(tabModel.getValueAt(2, 2).toString());
//Note: We can't cast the TableMode returned from the getModel method
//to a DefaultTableModel object because it is implemented as an anonymous
//inner class in the JTable. So let's create a JTable with a DefaultTableModel
//we can use:
//Create a DeafultTableModel object for another JTable
        DefaultTableModel defTableModel = new DefaultTableModel(data,columnNames);
        JTable anotherJTable = new JTable(defTableModel);
//Create a JScrollPane to contain for the JTable
        JScrollPane anotherSP = new JScrollPane(anotherJTable);
//an array holding data for a new column
        Object[] newData = {1,2,3,4};
//Add a column
        defTableModel.addColumn("Column 4", newData);
//an array holding data for a new row
        Object[] newRowData = {5,5,5,5};
//Add a row
        defTableModel.addRow(newRowData);
//an array holding data for a new row
        Object[] insertRowData = {2.5,2.5,2.5,2.5};
//Insert a row
        defTableModel.insertRow(2,insertRowData);
//Change a cell value
        defTableModel.setValueAt(8888, 3, 2);
//Add the JScrollPanes to the JFrame.
        guiFrame.add(sp, BorderLayout.NORTH);
        guiFrame.add(anotherSP, BorderLayout.SOUTH);
        guiFrame.setVisible(true);
    }
}