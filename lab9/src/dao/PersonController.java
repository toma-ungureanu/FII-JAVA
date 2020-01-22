package dao;

import database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class PersonController
{
    public void create(String name) throws SQLException
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into PERSONS (name) values (?)"))
        {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from PERSONS where name like '" + name + "'"))
        {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select NAME from PERSON where ID=" + id))
        {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public List<String> findAll() throws SQLException
    {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from PERSON"))
        {
            List<String> allPersons = new ArrayList<>();
            while (rs.next())
            {
                allPersons.add(rs.getString(2));
            }
            return allPersons;
        }
    }

}