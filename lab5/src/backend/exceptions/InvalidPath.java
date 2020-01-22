package backend.exceptions;

/**
 * @author Toma-Florin Ungureanu
 */
public class InvalidPath extends RuntimeException
{
    public InvalidPath(String errorName)
    {
        super(errorName);
    }
}
