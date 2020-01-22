package com.interfaces;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Toma-Florin Ungureanu
 */
public interface IVisitable extends Comparable
{
    /**
     * Function to compare the opening hours of the visitable objects
     *
     * @param other the object to be compared with
     * @return -1 if other has opening hour before, 0 if the opening
     * hours are the same, 1 if the other object has the opening hour
     * before this
     */
    default int compareTo(Object other)
    {
        if (other == null)
            throw new NullPointerException();
        if (!(other instanceof IVisitable))
            throw new ClassCastException("Uncomparable objects!");
        IVisitable visitable = (IVisitable) other;
        if (this.getOpeningHour().isBefore(visitable.getOpeningHour()))
        {
            return -1;
        } else if (this.getOpeningHour().isAfter(visitable.getOpeningHour()))
        {
            return 1;
        } else if (this.getOpeningHour().equals(visitable.getOpeningHour()))
        {
            return 0;
        }

        return 0;
    }

    /**
     * Static function to get the duration of the visitable object
     *
     * @param visitableObject the object to be analysed
     * @return the duration between the given LocalTime objects
     */
    static Duration getVisitingDuration(IVisitable visitableObject)
    {
        LocalTime opening = visitableObject.getOpeningHour();
        LocalTime closure = visitableObject.getClosingHour();

        System.out.println(Duration.between(opening, closure).getSeconds() / 3600 + " Hours");
        return Duration.between(opening, closure);
    }

    /**
     * Function to get the closing hour
     *
     * @return the closing hour
     */
    public LocalTime getClosingHour();

    /**
     * Function to get the opening hour
     *
     * @return the opening hour
     */
    public LocalTime getOpeningHour();

    /**
     * Default function to set the opening hour
     *
     * @return the default hour, 9:30
     */
    default String setOpeningHour()
    {
        return "09:30";
    }

    /**
     * Default function to set the closure hour
     *
     * @return the default hour, 21:00
     */
    default String setClosureHour()
    {
        return "21:00";
    }

    /**
     * Default function to check if the string if a valid hour
     *
     * @param hour the hour to be checked
     * @return true if the hour is valid, false otherwise
     */
    default boolean checkFormat(String hour)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try
        {
            LocalTime local = LocalTime.parse(hour, dateTimeFormatter);
        } catch (DateTimeParseException e)
        {
            System.out.println("Exception, you entered an invalid time: " + e.getParsedString());
            return false;
        }
        return true;
    }


}
