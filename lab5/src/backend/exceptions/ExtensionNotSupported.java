package backend.exceptions;

/**
 * @author Toma-Florin Ungureanu
 */
public class ExtensionNotSupported extends RuntimeException
{
    public ExtensionNotSupported(String name)
    {
        super(name);
    }
}
