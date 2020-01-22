package command;

import catalog.Catalog;
import graph.Graph;

import java.io.*;

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
        html.append("<title>Report of Reports</title>\n");
        html.append("</head>\n\n");

        html.append("<body>\n");
        html.append("<h1>List of Reports</h1>\n");
        // Make a list in HTML
        html.append("<ul>\n");

        // Loop the list of reports passed as argument.
        for (Graph graph : catalog.getGraphs())
        {
            html.append("<li>" + graph.toString() + "</li>\n");
        }
        html.append("</ul>\n");
        html.append("</body>\n\n");

        html.append("</html>");

        try
        {
            String directoryPath = catalog.getPath() + "\\catalog";
            File directory = new File(directoryPath);
            directory.mkdir();
            File catalogFile = new File(directoryPath + "\\" + "catalog.html");
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
