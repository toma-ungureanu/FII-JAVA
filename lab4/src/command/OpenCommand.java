package command;

import catalog.Catalog;

/**
 * @author Toma-Florin Ungureanu
 */
public class OpenCommand extends Command
{
    public OpenCommand(Catalog catalog, String name)
    {
        catalog.open(name);
    }
}
