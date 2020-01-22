package com.student;

import com.projects.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Toma-Florin Ungureanu
 */
public class Student
{
    private String name;
    private int yearOfStudy;
    private Project[] preferences;

    /**
     * Parameter constructor for the student class
     *
     * @param name        name of the student
     * @param yearOfStudy study year of the student
     */
    public Student(String name, int yearOfStudy)
    {
        this.name = name;
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * @return the name of the student
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the student
     *
     * @param name name of the student
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the year of study
     */
    public int getYearOfStudy()
    {
        return yearOfStudy;
    }

    /**
     * Set the year of study for the student
     *
     * @param yearOfStudy study year of the student
     */
    public void setYearOfStudy(int yearOfStudy)
    {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * Set the preferences of the student for the projects
     *
     * @param preferences 0,1... projects in descending order of preference
     */
    public void setPreferences(Project... preferences)
    {
        Project[] projectsWithDuplicates = new Project[preferences.length];
        Set<Project> set = new HashSet<Project>();
        for (int i = 0; i < projectsWithDuplicates.length; i++)
        {
            set.add(projectsWithDuplicates[i]);
        }
        this.preferences = preferences;
    }

    /**
     * @return the prefereces for the student
     */
    public Project[] getPreferences()
    {
        return preferences;
    }

    @Override
    /**
     * Function to ease the checking of conditions
     * for the problem
     */
    public boolean equals(Object object)
    {
        if (!(object instanceof Student))
            return false;
        Student student = (Student) object;
        return yearOfStudy == student.yearOfStudy &&
                name.equals(student.name) &&
                Arrays.equals(preferences, student.preferences);
    }

    @Override
    /**
     * Function so that when we call any type of print
     * on the student, the compiler will know how to print it
     */
    public String toString()
    {
        return "Name of the student: " + name + "\n" +
                "Year of Study: " + yearOfStudy + "\n" +
                "Preferences: \n" + Arrays.toString(preferences).replace("[", "")
                .replace("]", "")
                .replace(", ", "\n")
                .trim() + "\n";
    }


}
