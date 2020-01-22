package dao;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieController
{
    public void create(String name) throws SQLException
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into MOVIES (NAME) values (?)"))
        {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from MOVIES where name like '" + name + "'"))
        {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select NAME from MOVIES where ID=" + id))
        {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public List<String> findAll() throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from MOVIES"))
        {
            List<String> allMovies = new ArrayList<>();
            while (rs.next())
            {
                allMovies.add(rs.getString(2));
            }
            return allMovies;
        }
    }

    public boolean getMovieActors(String movieName) throws SQLException
    {
        List<String> movieActors = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select MOVIE_ID from MOVIES where NAME=" + movieName))
        {
            Integer movieId = rs.next() ? rs.getInt(1) : null;
            if(movieId == null)
            {
                return false;
            }

            ResultSet rs1 = stmt.executeQuery("select * from MOVIE_ACTORS where MOVIE_ID=" + movieId);
            while(rs1.next())
            {
                Integer movieActor = rs1.getInt(2);
                movieActors.add(movieActor.toString());
            }

            String actorsString = movieActors.toString();
            System.out.println(actorsString.substring(1, actorsString.length() - 1));
            return true;
        }
    }


}
