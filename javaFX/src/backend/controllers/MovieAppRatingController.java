package backend.controllers;

import backend.database.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieAppRatingController
{
    private CallableStatement callableStatement;
    public MovieAppRatingController() {}

    public String modifyRating(int userId, int movieId, int rating)
    {
        try
        {
            callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{call ACTUALIZARE_NOTA(?, ?, ?, ?)}");
            callableStatement.setString(1, String.valueOf(userId));
            callableStatement.setString(2, String.valueOf(movieId));
            callableStatement.setInt(3, rating);
            callableStatement.registerOutParameter(4, Types.VARCHAR);

            callableStatement.executeUpdate();
            DatabaseConnection.commit();

            return callableStatement.getString(4);

        } catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

    public String addRating(int userId, int movieId, int rating)
    {
        try
        {
            callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{call ADAUGARE_NOTA(?, ?, ?, ?)}");
            callableStatement.setString(1, String.valueOf(userId));
            callableStatement.setString(2, String.valueOf(movieId));
            callableStatement.setInt(3, rating);
            callableStatement.registerOutParameter(4, Types.VARCHAR);

            callableStatement.executeUpdate();
            DatabaseConnection.commit();

            return callableStatement.getString(4);
        } catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

    public String deleteRating(int userId, int movieId)
    {
        try
        {
            callableStatement = DatabaseConnection.getConnection()
                    .prepareCall("{call STERGERE_NOTA(?, ?, ?)}");
            callableStatement.setString(1, String.valueOf(userId));
            callableStatement.setString(2, String.valueOf(movieId));
            callableStatement.registerOutParameter(3, Types.VARCHAR);

            callableStatement.executeUpdate();
            DatabaseConnection.commit();

            return callableStatement.getString(3);

        } catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return null;
        }
    }

    public int getMovieId(String title) throws SQLException
    {
        callableStatement = DatabaseConnection.getConnection()
                .prepareCall("{call ID_FILM(?, ?)}");

        callableStatement.setString(1, title);
        callableStatement.registerOutParameter(2, Types.VARCHAR);

        callableStatement.executeUpdate();
        DatabaseConnection.commit();

        return Integer.parseInt(callableStatement.getString(2));
    }
}
