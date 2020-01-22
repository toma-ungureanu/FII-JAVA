package backend.command;

import backend.catalog.Catalog;
import gui.catalog.CatalogList;

/**
 * @author Toma-Florin Ungureanu
 */
public class SaveCommand extends Command
{
    public SaveCommand(Catalog catalog, CatalogList catalogList, String name)
    {
        catalog.save(catalogList, name);
    }
}
