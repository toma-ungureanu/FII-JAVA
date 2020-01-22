package com.buildings;

import com.interfaces.*;
import com.graphrelated.*;

import java.util.Objects;

/**
 * @author Toma-Florin Ungureanu
 */
public class Restaurant extends Node implements IClassifiable
{
    private int rank;

    /**
     * The default constructor of the restaurant class
     *
     * @param name the name of the restaurant
     */
    public Restaurant(String name)
    {
        this.name = name;
    }

    /**
     * Function to get the rank of the restaurant
     *
     * @return the rank
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Method to st the rank of the restaurant
     *
     * @param rank the rank of the restaurant
     */
    public void setRank(int rank)
    {
        this.rank = rank;
    }

    /**
     * Function to get the name of the restaurant
     *
     * @return the name of the restaurant
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to set the name of the restaurant
     *
     * @param name name of the restaurant
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Restaurant " + name + "\nHas rank: " + rank + "\n";
    }
}
