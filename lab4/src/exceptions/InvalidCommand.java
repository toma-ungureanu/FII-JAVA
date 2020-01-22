package exceptions;

/**
 * @author Toma-Florin Ungureanu
 */
public class InvalidCommand extends RuntimeException
{
    public InvalidCommand(String name)
    {
        super(name);
    }
}
