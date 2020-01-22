package backend.catalog;

import backend.catalog.serializer.CatalogSerializer;
import backend.exceptions.InvalidPath;
import backend.graph.Graph;
import gui.catalog.CatalogList;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class Catalog extends CatalogIO implements Serializable
{
    private List<Graph> graphs;
    private String path;
    private CatalogList catalogList;
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

    public void setCatalogList(CatalogList catalogList)
    {
        this.catalogList = catalogList;
    }

    public CatalogList getCatalogList()
    {
        return catalogList;
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

            Desktop viewer = Desktop.getDesktop();
            File file = new File(name);
            viewer.open(file);
            found = true;

            if(!found)
            {
                throw new InvalidPath("Couldn't find the file");
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
    public void save(CatalogList catalogList, String fileName)
    {
       CatalogSerializer serializer = new CatalogSerializer(this, catalogList);
       serializer.serialize(catalogList, fileName);
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
        CatalogSerializer deserializer = new CatalogSerializer(this, catalogList);
        CatalogSerializer loadedCatalog = deserializer.deserialize(fileName);
        this.path = loadedCatalog.getCatalog().getPath();
        this.graphs = loadedCatalog.getCatalog().getGraphs();
        this.catalogList = loadedCatalog.getCatalogList();
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
