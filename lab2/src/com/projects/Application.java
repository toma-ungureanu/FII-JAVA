package com.projects;

import java.time.LocalDate;

/**
 * @author Toma-Florin Ungureanu
 */

public class Application extends Project
{
    /**
     * types of Computer Languages for the application class
     */
    public enum Language
    {
        JAVA, CPLUSPLUS, DOTNET, PHP, PYTHON;
    }

    private Language language;

    public LocalDate getDeadline()
    {
        return deadline;
    }

    /**
     * Parameter constructor for application
     *
     * @param name     the name of the application
     * @param deadline the due date of the application
     * @param language the language in which th application wil be written
     */
    public Application(String name, LocalDate deadline, Language language)
    {
        super(name, deadline);
        this.language = language;
    }

    @Override
    /**
     * Function so that when we call any type of print
     * on the application, the compiler will know how to print it
     */
    public String toString()
    {
        return "Title of the application: " + name + "\n" +
                "Language in which the application is written: " + language + "\n" +
                "Deadline of the application: " + deadline + "\n";
    }
}
