package repositories;

import database.Database;
import oom.Person;
import org.springframework.data.repository.CrudRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * @author Toma-Florin Ungureanu
 */
public interface PersonRepository extends CrudRepository<Person, Long>
{
    static Integer findByName(String name) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from PERSONS where name like '" + name + "'"))
        {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    static Optional<Person> findById(int id) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select NAME from PERSON where ID=" + id))
        {
            Person person = new Person();
            person.setName(rs.next() ? rs.getString(2) : null);
            return Optional.of(person);
        }
    }
}
