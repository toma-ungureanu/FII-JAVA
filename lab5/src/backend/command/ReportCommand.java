package backend.command;

import backend.catalog.Catalog;
import backend.graph.Graph;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Toma-Florin Ungureanu
 */
public class ReportCommand extends Command
{
    public ReportCommand(Catalog catalog, String fileName)
    {
        StringBuilder html = new StringBuilder();
        html.append("<!doctype html>\n");
        html.append("<html lang='en'>\n");

        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>Report</title>\n");
        html.append("</head>\n\n");

        html.append("<body>\n");
        html.append("<h1>Movie List</h1>\n");
        // Make a list in HTML
        html.append("<ul>\n");

        // Loop the list of reports passed as argument.
        if(!catalog.getGraphs().isEmpty())
        {
            for (Graph graph : catalog.getGraphs())
            {
                html.append("<li>" + graph.toString() + "</li>\n");
            }
        }
        else
        {
            StringBuilder graphInfo = new StringBuilder();
            Object toCheck = catalog.getCatalogList();
            if(toCheck == null)
            {
                html.append("<li>" + "No catalog to display" + "</li>\n");
            }
            else
            {
                DefaultTableModel model = catalog.getCatalogList().getModel();
                for (int row = 0; row < model.getRowCount(); row++)
                {
                    for (int column = 0; column < model.getColumnCount(); column++)
                    {
                        graphInfo.append(model.getValueAt(row, column)).append(" ");
                    }
                    html.append("<li>" + graphInfo.toString() + "</li>\n");
                    graphInfo.delete(0, graphInfo.length());
                }
            }

        }
        html.append("</ul>\n");
        html.append("</body>\n\n");

        html.append("</html>");

        try
        {
            String directoryPath = catalog.getPath() + "\\catalog";
            File directory = new File(directoryPath);
            directory.mkdir();
            File catalogFile = new File(directoryPath + "\\" + fileName);
            catalogFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(catalogFile.getPath());
            PrintWriter writer = new PrintWriter(fileOut);
            writer.write(html.toString());
            writer.close();
            fileOut.close();
            System.out.println("Report was saved in: " + catalogFile.getPath());
        } catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getCause());
            System.exit(1);
        }
    }
}
