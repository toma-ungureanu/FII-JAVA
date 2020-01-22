package backend.controllers;

import backend.database.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Toma-Florin Ungureanu
 */
public class AppMovieController
{
    private CallableStatement callableStatement;
    public AppMovieController() {}

    public String filterBy(String title,
                           String genre,
                           Date dataFormatBegin,
                           Date dataFormatEnd,
                           Integer duration)
            throws SQLException
    {
        callableStatement = DatabaseConnection.getConnection()
                .prepareCall("{? = call LISTA_FILME_FILTRARE(?, ?, ?, ?, ?)}");
        callableStatement.registerOutParameter(1, Types.CLOB);
        callableStatement.setString(2, title);
        callableStatement.setString(3, genre);
        callableStatement.setDate(4, dataFormatBegin);
        callableStatement.setDate(5, dataFormatEnd);

        if(duration != null)
        {
            callableStatement.setInt(6, duration);
        }
        else
        {
            callableStatement.setNull(6, Types.INTEGER);
        }
        callableStatement.executeUpdate();
        DatabaseConnection.commit();

        return callableStatement.getString(1);
    }

    public String getUserMovieList(String userId)
    {
        try
        {
            callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{? = call LISTA_FILME_UTILIZATOR(?)}");
            callableStatement.registerOutParameter(1, Types.CLOB);
            callableStatement.setString(2, userId);
            callableStatement.executeUpdate();
            DatabaseConnection.commit();

            return callableStatement.getString(1);
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

    public String getRecommendedMovieList(int userid)
    {
        try
        {
            callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{? = call RECOMANDARE(?)}");
            callableStatement.registerOutParameter(1, Types.CLOB);
            callableStatement.setString(2, String.valueOf(userid));
            callableStatement.executeUpdate();
            DatabaseConnection.commit();
            return callableStatement.getString(1);
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

    public String getTop30()
    {
        try
        {
            callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{? = call LISTA_FILME_FILTRARE_TOP()}");
            callableStatement.registerOutParameter(1, Types.CLOB);
            callableStatement.executeUpdate();
            DatabaseConnection.commit();
            return callableStatement.getString(1);
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

}