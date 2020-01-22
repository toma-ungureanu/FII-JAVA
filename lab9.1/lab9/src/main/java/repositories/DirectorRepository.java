package repositories;

import java.util.List;
import database.Database;
import oom.Director;
import org.springframework.data.repository.CrudRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * @author Toma-Florin Ungureanu
 */
public interface DirectorRepository extends CrudRepository<Director, Long>
{
    static public boolean getMoviesDirectedBy(String directorName) throws SQLException
    {
        List<String> moviesDirectedBy = new ArrayList<String>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select DIRECTOR_ID from DIRECTOR where NAME=" + directorName))
        {
            Integer directorId = rs.next() ? rs.getInt(1) : null;
            if(directorId == null)
            {
                return false;
            }

            ResultSet rs1 = stmt.executeQuery("select * from MOVIE_DIRECTORS where MOVIE_ID=" + directorId);
            while(rs1.next())
            {
                int movieActorId = rs1.getInt(1);
                moviesDirectedBy.add(String.valueOf(movieActorId));
            }

            String actorsString = moviesDirectedBy.toString();
            System.out.println(actorsString.substring(1, actorsString.length() - 1));
            return true;
        }
    }
}
