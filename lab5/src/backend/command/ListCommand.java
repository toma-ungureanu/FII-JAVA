package backend.command;
import backend.catalog.Catalog;

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
