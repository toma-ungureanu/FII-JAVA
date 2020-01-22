package repositories;

import database.Database;
import oom.Movie;
import org.springframework.data.repository.CrudRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Toma-Florin Ungureanu
 */
public interface MovieRepository extends CrudRepository<Movie, Long>
{
    static public String getMovieActors(String movieName) throws SQLException
    {
        List<String> movieActors = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select MOVIE_ID from MOVIES where NAME=" + movieName))
        {
            Integer movieId = rs.next() ? rs.getInt(1) : null;
            if(movieId == null)
            {
                return null;
            }

            ResultSet rs1 = stmt.executeQuery("select * from MOVIE_ACTORS where MOVIE_ID=" + movieId);
            while(rs1.next())
            {
                int movieActorId = rs1.getInt(2);
                movieActors.add(String.valueOf(movieActorId));
            }

            String actorsString = movieActors.toString();
            System.out.println(actorsString.substring(1, actorsString.length() - 1));
            return actorsString;
        }
    }

    static Optional<Movie> findById(int id) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select NAME from MOVIES where ID=" + id))
        {
            Movie movie = new Movie();
            movie.setId(rs.next() ? rs.getLong(1) : null);
            movie.setMovieName(rs.next() ? rs.getString(2) : null);
            return Optional.of(movie);
        }
    }
}
