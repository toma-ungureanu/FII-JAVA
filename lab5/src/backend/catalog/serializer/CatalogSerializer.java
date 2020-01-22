package backend.catalog.serializer;

import backend.catalog.Catalog;
import gui.catalog.CatalogList;

import java.io.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class CatalogSerializer
{
    private Catalog catalog;
    private CatalogList catalogList;

    public CatalogSerializer(Catalog catalog, CatalogList catalogList)
    {
        this.catalog = catalog;
        this.catalogList = catalogList;
    }

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }

    public CatalogList getCatalogList()
    {
        return catalogList;
    }

    /**
     * @param fileName
     */
    public void serialize(CatalogList catalogList, String fileName)
    {
        try
        {

            String directoryPath = catalog.getPath() + "\\catalog";
            File directory = new File(directoryPath);
            directory.mkdir();
            File catalogFile = new File(directoryPath + "\\" + fileName);
            catalogFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(catalogFile.getPath());
            ObjectOutputStream writer = new ObjectOutputStream(fileOut);
            if (catalogList == null)
            {
                writer.writeObject(catalog);
            } else
            {
                writer.writeObject(catalogList);
            }
            writer.close();
            fileOut.close();
            System.out.println("Data was saved in: " + catalogFile.getPath());
        } catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getCause());
            System.exit(1);
        }
    }

    /**
     * @param fileName
     * @return
     */
    public CatalogSerializer deserialize(String fileName)
    {
        try
        {
            String directoryPath = catalog.getPath() + "\\catalog";
            File catalogFile = new File(directoryPath + "\\" + fileName);
            FileInputStream fileIn = new FileInputStream(catalogFile.getPath());
            ObjectInputStream reader = new ObjectInputStream(fileIn);
            Object object = reader.readObject();
            if (object instanceof Catalog)
            {
                this.catalog = (Catalog) object;
            }
            else
            {
                this.catalogList = (CatalogList) object;
            }
            System.out.println("Data was read from: " + catalogFile.getPath());
        } catch (IOException | ClassNotFoundException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getCause());
            System.exit(1);
        }

        return this;
    }
}
