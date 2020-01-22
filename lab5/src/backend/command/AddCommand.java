package backend.command;

import backend.catalog.Catalog;
import backend.graph.Graph;

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
