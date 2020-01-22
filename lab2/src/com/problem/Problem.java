package com.problem;

import com.projects.Project;
import com.student.Student;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * @author Toma-Florin Ungureanu
 */


public class Problem
{
    private Student[] students;
    private Project[] projects;

    /**
     * Function to check for multiple instances
     * of a given student in a given array
     * The function is time efficient as it does not look
     * into already checked parts of the array
     *
     * @param check   the student to be checked
     * @param objects the array of objects in which we check
     * @param pos     the position where we start the search
     * @return true if the object is unique in array, false otherwise
     */
    private boolean checkFrequency(Object check, Object[] objects, int pos)
    {
        int count = 0;
        for (int i = pos; i < objects.length; i++)
        {
            if (objects[i].equals(check))
            {
                count++;
            }
        }
        return count < 2;
    }

    /**
     * Template function to check the uniqueness
     * condition for each object (Project or Student)
     *
     * @param objects array of objects to be verified
     * @return true if each object is unique, false otherwise
     */
    private boolean checkCondition(Object[] objects)
    {
        for (int i = 0; i < objects.length; i++)
        {
            if (!checkFrequency(objects[i], objects, i))
            {
                System.out.println("You cannot add the same " + objects.getClass().getSimpleName() + " type!");
                return false;
            }
        }
        return true;
    }

    /**
     * Function to set projects member
     * It checks for multiple apparitions of the same project
     *
     * @param projects vararg as there can be 0,1,.. projects
     * @return true if the frequency of each project is 1, false otherwise
     */
    public boolean setProjects(Project... projects)
    {
        if (projects.length == 1)
        {
            this.projects = projects;
            return true;
        }

        for (int i = 0; i < projects.length; i++)
        {
            if (projects[i] == null)
            {
                System.out.println("Problem::setProjects: Project " + (i + 1) + " is not defined appropriately");
                return false;
            }
        }

         if(!checkCondition(projects))
         {
             return false;
         }
         this.projects = projects;
         return true;
    }

    /**
     * Function for setting the students parameter
     * It checks for multiple instances of the same student
     *
     * @param students vararg as there can be 0,1.. students
     * @return true if the frequency of each student is 1, false otherwise
     */
    public boolean setStudents(Student... students)
    {
        if (students.length == 1)
        {
            this.students = students;
            return true;
        }

        for (int i = 0; i < students.length; i++)
        {
            if (students[i] == null)
            {
                System.out.println("Problem::setStudent: Student " + (i + 1) + " is not defined appropriately");
                return false;
            }
        }

         if(!checkCondition(students))
         {
             return false;
         }

         this.students = students;
         return true;
    }

    /**
     * Template method to print the Student and Project tpyes
     *
     * @param objects the objects that will be printed
     */
    public void printObject(Object[] objects)
    {
        if (objects != null)
        {
            for (int i = 0; i < objects.length; i++)
            {
                if (objects[i] != null)
                {
                    System.out.println(objects[i]);
                }
            }
        }
    }

    /**
     * Function to get the projects
     * We erase the multiple apparitions of a project
     * with a hash set
     *
     * @return project array with no duplicates
     */
    public Project[] getProjects()
    {
        int count = 0;
        for (int i = 0; i < students.length; i++)
        {
            if (students[i] == null)
            {
                return null;
            } else if (students[i].getPreferences() != null)
            {
                for (int j = 0; j < students[i].getPreferences().length; j++)
                {
                    count++;
                }
            }
        }

        Project[] projectsWithDuplicates = new Project[count];
        count = -1;
        for (int i = 0; i < students.length; i++)
        {
            if (students[i].getPreferences() != null)
            {
                for (int j = 0; j < students[i].getPreferences().length; j++)
                {
                    count++;
                    projectsWithDuplicates[count] = students[i].getPreferences()[j];
                }
            }
        }

        Set<Project> set = new HashSet<Project>();
        for (int i = 0; i < projectsWithDuplicates.length; i++)
        {
            set.add(projectsWithDuplicates[i]);
        }

        projects = set.toArray(Project[]::new);
        return projects;
    }

    /**
     * Function to get the students.
     *
     * @return
     */
    public Student[] getStudents()
    {
        return students;
    }

    @Override
    /**
     * Function so that when we call any type of print
     * on the problem, the compiler will know how to print it
     */
    public String toString()
    {
        for (int i = 0; i < this.students.length; i++)
        {
            if (this.students[i] == null)
            {
                return "Problem::toString: Students are not defined appropriately";
            }
        }

        for (int i = 0; i < projects.length; i++)
        {
            if (this.projects[i] == null)
            {
                return "Problem::toString: Projects are not defined appropriately";
            }
        }
        return "Students: \n" + Arrays.toString(this.students).replace("[", "")
                .replace("]", "")
                .replace(", ", "\n")
                .trim() + "\n";
    }
}
