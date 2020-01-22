package database;

import java.sql.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class Database
{
    private static final String URL = "jdbc:derby://localhost:1527/Movies";
    private static final String USER = "dba";
    private static final String PASSWORD = "sql";
    private static Connection connection = null;

    private Database() { }

    public static Connection getConnection()
    {
        if (connection == null)
        {
            createConnection();
        }
        return connection;
    }

    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        }
        catch (SQLException | ClassNotFoundException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }

    public static void closeConnection()
    {
        try
        {
            connection.close();
        }
         catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }

    public static void commit()
    {
        try
        {
            connection.commit();
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }

    public static void rollback()
    {
        try
        {
            connection.rollback();
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }
}
