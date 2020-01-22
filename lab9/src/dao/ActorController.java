package dao;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class ActorController
{
    private List<String> moviesPlayedByActor = new ArrayList<>();

    public boolean addActor(String actorName) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from PERSON where name like '" + actorName + "'"))
        {
            Integer personId = rs.next() ? rs.getInt(1) : null;
            if (personId == null)
            {
                return false;
            }
            String actorQueriedName = rs.next() ? rs.getString(2) : null;
            PreparedStatement pstmt = con.prepareStatement("insert into ACTOR (name,ACTOR_ID) values (?,?)");
            pstmt.setString(1, actorQueriedName);
            pstmt.setInt(2, personId);
            pstmt.executeUpdate();
            return true;
        }
    }

    public boolean addMovieActor(String actorName, String movieName) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select ACTOR_ID from ACTOR where name like '" + actorName + "'"))
        {
            Integer actorId = rs.next() ? rs.getInt(3) : null;
            if (actorId == null)
            {
                return false;
            }

            ResultSet rs1 = stmt.executeQuery("select MOVIE_ID from MOVIES where name like '" + movieName + "'");
            Integer movieId = rs.next() ? rs.getInt(1) : null;
            if (movieId == null)
            {
                return false;
            }

            PreparedStatement pstmt = con.prepareStatement("insert into MOVIE_ACTORS (MOVIE_ID,ACTOR_ID) values (?,?)");
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();
        }
        return true;
    }

    public boolean getMoviesPlayedByActor(String actorName) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select ID from ACTOR where NAME=" + actorName))
        {
            Integer actorId = rs.next() ? rs.getInt(3) : null;
            if (actorId == null)
            {
                return false;
            }

            ResultSet rs1 = stmt.executeQuery("select * from MOVIE_ACTORS where ACTOR_ID=" + actorId);
            while (rs1.next())
            {
                Integer movie = rs1.getInt(1);
                moviesPlayedByActor.add(movie.toString());
            }

            String actorsString = moviesPlayedByActor.toString();
            System.out.println(actorsString.substring(1, actorsString.length() - 1));
            return true;
        }
    }

}
