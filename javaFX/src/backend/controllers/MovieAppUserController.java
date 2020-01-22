package backend.controllers;

import backend.database.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieAppUserController
{
    private CallableStatement callableStatement;
    public MovieAppUserController() {}

    public String logIn(String userEmail, String password) throws SQLException
    {
        //callable statement
        callableStatement = DatabaseConnection.getConnection()
                .prepareCall("{call LOGIN(?,?,?)}");
        callableStatement.setString(1, userEmail);
        callableStatement.setString(2, password);
        callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
        callableStatement.executeUpdate();
        return callableStatement.getString(3);
    }

    public String register(String lastName, String firstName, String email, String password)
            throws SQLException
    {
        callableStatement = DatabaseConnection.getConnection()
                .prepareCall("{call INREGISTRARE(?, ?, ?, ?, ?)}");
        callableStatement.setString(1, lastName);
        callableStatement.setString(2, firstName);
        callableStatement.setString(3, email);
        callableStatement.setString(4, password);
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.executeUpdate();
        DatabaseConnection.commit();
        return callableStatement.getString(5);
    }

    public String generateRandomPass() throws SQLException
    {
        callableStatement = DatabaseConnection.getConnection()
                .prepareCall("{? = call GENERARE_PAROLA()}");
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.executeUpdate();
        DatabaseConnection.commit();
        return callableStatement.getString(1);
    }

}
