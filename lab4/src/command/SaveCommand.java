package command;

import catalog.Catalog;

/**
 * @author Toma-Florin Ungureanu
 */
public class SaveCommand extends Command
{
    public SaveCommand(Catalog catalog, String name)
    {
        catalog.save(name);
    }
}
