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
public class Movie
{
    private String name;
    private String id;

    public Movie()
    {

    }

    public Movie(String name)
    {
        this.name = name;
    }
}
