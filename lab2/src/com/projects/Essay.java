package com.projects;

import java.time.LocalDate;

/**
 * @author Toma-Florin Ungureanu
 */

public class Essay extends Project
{
    /**
     * Topics for the essay
     */
    public enum Topic
    {
        ALGORITHMS, DATA_STRUCTURES, WEB, DATABASES;
    }

    public LocalDate getDeadline()
    {
        return deadline;
    }

    private Topic topic;

    /**
     * Parameter constructor for the essay class
     *
     * @param name     name of the essay
     * @param deadline due date of the essay
     * @param topic    topic of the essay
     */
    public Essay(String name, LocalDate deadline, Topic topic)
    {
        super(name, deadline);
        this.topic = topic;
    }

    @Override
    /**
     * Function so that when we call any type of print
     * on the essay, the compiler will know how to print it
     */
    public String toString()
    {
        return "Title of the essay: " + name + "\n" +
                "Topic of the essay: " + topic + "\n" +
                "Deadline of the essay: " + deadline + "\n";
    }
}
