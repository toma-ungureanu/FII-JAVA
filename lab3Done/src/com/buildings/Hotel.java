package com.buildings;

import com.interfaces.*;
import com.graphrelated.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class Hotel extends Node implements IClassifiable
{
    private int rank;

    /**
     * Default constructor for the hotel class
     *
     * @param name the name of the hotel
     */
    public Hotel(String name)
    {
        this.name = name;
    }

    /**
     * Function to get the rank of the hotel
     *
     * @return the rank of the hotel
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Method to set the rank of the hotel
     *
     * @param rank of the hotel
     */
    public void setRank(int rank)
    {
        this.rank = rank;
    }

    /**
     * Method to set the name of the hotel
     *
     * @param name the name of the hotel
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Hotel " + name + "\nHas rank: " + rank + "\n";
    }

    @Override
    public String getName()
    {
        return this.name;
    }
}
