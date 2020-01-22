package com.projects;

import java.time.LocalDate;

/**
 * @author Toma-Florin Ungureanu
 */

public abstract class Project
{
    protected String name;
    protected LocalDate deadline;

    /**
     * Parameter constructor for the project class
     *
     * @param name     the name of the project
     * @param deadline the due date of the project
     */
    public Project(String name, LocalDate deadline)
    {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * @return the name of the project
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the project
     *
     * @param name of the project
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Abstract function to return the due date of the project
     *
     * @return the due date of the project
     */
    public abstract LocalDate getDeadline();

    /**
     * Set the due date of the project
     *
     * @param deadline due date of the project
     */
    public void setDeadline(LocalDate deadline)
    {
        this.deadline = deadline;
    }

    @Override
    /**
     * Function so that when we call any type of print
     * on the project, the compiler will know how to print it
     */
    public String toString()
    {
        return "Project{" +
                "name='" + name + '\'' +
                ", deadline=" + deadline +
                '}';
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Project))
            return false;
        Project project = (Project) object;
        return name.equals(project.name) &&
                deadline.equals(project.deadline);
    }
}
