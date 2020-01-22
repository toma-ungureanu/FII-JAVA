package oom;

import database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class Director extends Person
{
    public Director()
    {
        super();
    }

    public Director(String name, int directorId)
    {
        super(name, directorId);
    }

    private List<String> moviesDirectedBy = new ArrayList<>();


}
