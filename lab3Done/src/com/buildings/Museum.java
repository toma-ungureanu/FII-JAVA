package com.buildings;

import com.interfaces.*;
import com.graphrelated.*;

import java.time.LocalTime;
import java.util.Objects;

/**
 * @author Toma-Florin Ungureanu
 */

public class Museum extends Node implements IPayable, IVisitable
{
    private int entryFee;
    private String openingHour = " ";
    private String closingHour = " ";

    /**
     * Default constructor for the museum class
     *
     * @param name the name of the museum
     */
    public Museum(String name)
    {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Function to get the get the name of the museum
     *
     * @return the name of the museum
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Method to set the entry fee
     *
     * @param fee entry fee for the museum
     */
    public void setFee(int fee)
    {
        this.entryFee = fee;
    }

    /**
     * Function to get the entry fee of the museum
     *
     * @return the entry fee
     */
    public int getFee()
    {
        return this.entryFee;
    }

    /**
     * Method to set the name of the museum
     *
     * @param name of the museum
     */
    public void setName(String name)
    {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Function to get the entry fee of the museum
     *
     * @return the entry fee
     */
    public double getEntryFee()
    {
        return this.entryFee;
    }

    /**
     * Method to set the entry fee for the museum
     *
     * @param entryFee the entry fee
     */
    public void setEntryFee(int entryFee)
    {
        this.entryFee = entryFee;
    }

    /**
     * Function to get the opening hour for the museum
     *
     * @return opening hour of the museum
     */
    public LocalTime getOpeningHour()
    {
        return LocalTime.parse(this.openingHour);
    }

    /**
     * Function to get the closing hour for the museum
     *
     * @return the closure hour for the museum
     */
    public LocalTime getClosingHour()
    {
        return LocalTime.parse(this.closingHour);
    }

    /**
     * Method to set the opening hour for the museum
     *
     * @param openingHour opening hour
     */
    public void setOpenHour(String openingHour)
    {
        if (checkFormat(openingHour))
        {
            this.openingHour = openingHour;
        }
    }

    /**
     * Method to set the closure hour for the museum
     *
     * @param closingHour closure hour
     */
    public void setCloseHour(String closingHour)
    {
        if (checkFormat(closingHour))
        {
            this.closingHour = closingHour;
        }
    }

    /**
     * Default method to set the opening hour for the museum
     */
    public void setOpenHour()
    {
        this.openingHour = IVisitable.super.setOpeningHour();
    }

    /**
     * Default method to set the closure hour for the museum
     */
    public void setCloseHour()
    {
        this.closingHour = IVisitable.super.setClosureHour();
    }

    @Override
    public String toString()
    {
        return "Museum " + name + "\n" +
                "Has an entry Fee of: " + entryFee +
                "\nOpening Hour: " + openingHour +
                "\nClosing Hour: " + closingHour + "\n";
    }
}
