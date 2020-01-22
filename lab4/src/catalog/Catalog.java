package catalog;

import exceptions.InvalidPath;
import graph.Graph;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import catalog.serializer.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class Catalog extends CatalogIO implements Serializable
{
    private List<Graph> graphs;
    private String path;

    /**
     *
     * @param path
     */
    public Catalog(String path)
    {
        try
        {
            checkValidPath(path);
            this.path = path;
            graphs = new ArrayList<>();
        }
        catch (InvalidPath excp)
        {
            System.out.println(excp.getMessage());
            System.exit(1);
        }
    }


    public String getPath()
    {
        return this.path;
    }

    /**
     *
     * @param graph
     */
    public void add(Graph graph)
    {
        graphs.add(graph);
    }

    /**
     *
     * @param name
     */
    public void open(String name)
    {
        boolean found = false;
        try
        {
            for (Graph graph : graphs)
            {
                if (graph.getName().equals(name))
                {
                    Desktop viewer = Desktop.getDesktop();
                    File file = new File(graph.getPathToImage());
                    viewer.open(file);
                    found = true;
                }
            }
            if(!found)
            {
                throw new InvalidPath("Couldn't find the image");
            }
        }
        catch (IOException | InvalidPath excp)
        {
            System.out.println(excp.getMessage() + ": " + excp.getCause());
            System.exit(1);
        }

    }

    /**
     *
     * @param fileName
     */
    public void save(String fileName)
    {
       CatalogSerializer serializer = new CatalogSerializer(this);
       serializer.serialize(fileName);
    }

    /**
     *
     */
    public void list()
    {
        System.out.println(graphs.toString().replace("[", "")
                .replace("]", "")
                .replace(", ", "\n")
                .trim() + "\n");
    }

    /**
     *
     * @param fileName
     */
    public void load(String fileName)
    {
        CatalogSerializer deserializer = new CatalogSerializer(this);
        Catalog loadedCatalog = deserializer.deserialize(fileName);
        this.path = loadedCatalog.path;
        this.graphs = loadedCatalog.graphs;
    }

    /**
     *
     * @return
     */
    public List<Graph> getGraphs()
    {
        return this.graphs;
    }


}
