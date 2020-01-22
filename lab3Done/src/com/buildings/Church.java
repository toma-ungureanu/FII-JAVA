package com.buildings;

import com.interfaces.*;
import com.graphrelated.*;

import java.time.LocalTime;

/**
 * @author Toma-Florin Ungureanu
 */
public class Church extends Node implements IVisitable
{
    private String openingHour;
    private String closingHour;

    /**
     * The default constructor for the class
     *
     * @param name of the Church
     */
    public Church(String name)
    {
        this.name = name;
    }

    /**
     * Function to get the name of the church
     *
     * @return the name of the church
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to set the name of the church
     *
     * @param name of the church
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Function to get the opening hour for the church
     *
     * @return the opening hour
     */
    public LocalTime getOpeningHour()
    {
        return LocalTime.parse(this.openingHour);
    }

    /**
     * Function to get the closing hour for the church
     *
     * @return the closing hour
     */
    public LocalTime getClosingHour()
    {
        return LocalTime.parse(this.closingHour);
    }

    /**
     * Method to set the opening hour for the church
     *
     * @param openingHour for the church
     */
    public void setOpenHour(String openingHour)
    {
        if (checkFormat(openingHour))
        {
            this.openingHour = openingHour;
        }
    }

    /**
     * Method to set the closing hour for the church
     *
     * @param closingHour for the church
     */
    public void setCloseHour(String closingHour)
    {
        if (checkFormat(closingHour))
        {
            this.closingHour = closingHour;
        }
    }

    /**
     * Default method to set the opening hour for the church, 09:30
     */
    public void setOpenHour()
    {
        this.openingHour = IVisitable.super.setOpeningHour();
    }

    /**
     * Default method to set the closing hour, for the church, 21:00
     */
    public void setCloseHour()
    {
        this.closingHour = IVisitable.super.setClosureHour();
    }

    @Override
    public String toString()
    {
        return "Church " + name +
                "\nOpening Hour: " + openingHour +
                "\nClosing Hour: " + closingHour + "\n";
    }
}
