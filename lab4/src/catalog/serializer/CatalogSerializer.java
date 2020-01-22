package catalog.serializer;

import catalog.Catalog;

import java.io.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class CatalogSerializer
{
    private Catalog catalog;

    public CatalogSerializer(Catalog catalog)
    {
        this.catalog = catalog;
    }

    /**
     *
     * @param fileName
     */
    public void serialize(String fileName)
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
            writer.writeObject(catalog);
            writer.close();
            fileOut.close();
            System.out.println("Data was saved in: " + catalogFile.getPath());
        }
        catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getCause());
            System.exit(1);
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    public Catalog deserialize(String fileName)
    {
        try
        {
            String directoryPath = catalog.getPath() + "\\catalog";
            File directory = new File(directoryPath);
            File catalogFile = new File(directoryPath + "\\" + fileName);
            FileInputStream fileIn = new FileInputStream(catalogFile.getPath());
            ObjectInputStream reader = new ObjectInputStream(fileIn);
            this.catalog = (Catalog) reader.readObject();
            System.out.println("Data was read from: " + catalogFile.getPath());
        }
        catch (IOException | ClassNotFoundException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getCause());
            System.exit(1);
        }

        return this.catalog;
    }
}
