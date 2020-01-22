package command;

import catalog.Catalog;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Toma-Florin Ungureanu
 */
public class LoadCommand extends Command
{
    public LoadCommand() { }

    public Catalog loadCatalog()
    {
        String path;
        Catalog catalog = null;
        try
        {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Please enter the load file path:\n");
             path = br.readLine().trim();
            if(path.isEmpty())
            {
                path = "C:\\Users\\thomi\\IdeaProjects\\lab4\\graphs";
            }
            catalog = new Catalog(path);
            catalog.load("catalog.dat");

        } catch (IOException e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            System.exit(1);
        }

        return catalog;
    }
}
