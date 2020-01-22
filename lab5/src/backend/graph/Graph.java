package backend.graph;

import backend.catalog.CatalogIO;
import backend.exceptions.ExtensionNotSupported;
import backend.exceptions.InvalidPath;

import java.io.Serializable;

/**
 * @author Toma-Florin Ungureanu
 */
public class Graph extends CatalogIO implements Serializable
{
    String name;
    String pathToRep;
    String pathToImage;

    public Graph(String name, String  pathToRep, String pathToImage)
    {
        try
        {
            checkValidPath(pathToRep);
            checkValidPath(pathToImage);
            checkFileType(pathToImage);
            this.name = name;
            this.pathToRep = pathToRep;
            this.pathToImage = pathToImage;
        }
        catch (InvalidPath excp)
        {
            System.out.println("Could not verify the path to the graph rep: ");
            System.out.println(excp.getMessage());
            System.exit(1);
        }
        catch (ExtensionNotSupported excp)
        {
            System.out.println("Could not verify the path to the graph image: ");
            System.out.println(excp.getMessage());
        }
    }

    public String  getName()
    {
        return name;
    }

    public String getPathToRep()
    {
        return pathToRep;
    }

    public String getPathToImage()
    {
        return pathToImage;
    }

    @Override
    public String toString()
    {
        return "Name of the graph: " + name + '\n' +
                "Representation of the graph: " + pathToRep + '\n' +
                "Graph's image: " + pathToImage + '\n';
    }
}
