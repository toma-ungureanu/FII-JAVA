package command;

import catalog.Catalog;

/**
 * @author Toma-Florin Ungureanu
 */
public class ListCommand extends Command
{
    public ListCommand(Catalog catalog)
    {
        catalog.list();
    }
}
