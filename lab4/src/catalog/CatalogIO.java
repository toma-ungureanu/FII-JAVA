package catalog;

import exceptions.InvalidPath;
import exceptions.ExtensionNotSupported;
import java.io.File;

/**
 * @author Toma-Florin Ungureanu
 */
public abstract class CatalogIO
{
    /**
     *
     * @param name
     * @return
     * @throws InvalidPath
     */
    public boolean checkValidPath(String name) throws InvalidPath
    {
        File file = new File(name);
        if(!file.exists())
        {
            throw new InvalidPath(name);
        }
        return true;
    }

    /**
     *
     * @param file
     * @return
     */
    private String getFileExtension(String file)
    {

        if(file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0)
        {
            return file.substring(file.lastIndexOf(".") + 1);
        }
        else return "";
    }

    /**
     *
     * @param file
     * @return
     * @throws ExtensionNotSupported
     */
    public boolean checkFileType(String file) throws  ExtensionNotSupported
    {
        String extension = getFileExtension(file);
        if (!(extension.equals("pdf") ||
                extension.equals("tgf") ||
                extension.equals("png") ||
                extension.equals("svg") ||
                extension.equals("jpg")))
        {
            throw new ExtensionNotSupported(file);
        }
        return true;
    }
}
