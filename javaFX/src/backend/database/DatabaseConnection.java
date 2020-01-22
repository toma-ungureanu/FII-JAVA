package backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Toma-Florin Ungureanu
 */
public class DatabaseConnection
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT1";
    private static Connection connection = null;

    private DatabaseConnection() { }

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
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
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
