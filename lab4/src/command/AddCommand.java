package command;

import catalog.Catalog;
import graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Toma-Florin Ungureanu
 */
public class AddCommand extends Command
{
    public AddCommand(Catalog catalog, Graph graph)
    {
        catalog.add(graph);
    }

}
