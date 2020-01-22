package com.problem;

import com.projects.Project;
import com.student.Student;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Toma-Florin Ungureanu
 */
public class Matching
{
    private Problem problem;
    private int nrOfStuds;
    private int nrOfProjects;


    /**
     * Parameter constructor for matching problem solver
     *
     * @param problem to solve
     */
    public Matching(Problem problem)
    {
        this.problem = problem;
        nrOfStuds = problem.getStudents().length;
        nrOfProjects = problem.getProjects().length;
    }

    /**
     * check the conditions such that
     * the problem can be solved in optimal conditions
     *
     * @param problem problem to be solved
     * @return true if conditions are met, false otherwise
     */
    private boolean checkBasicConditions(Problem problem)
    {
        if (problem == null)
        {
            System.out.println("You have no problem to solve");
            return false;
        }

        Student[] students = problem.getStudents();
        Project[] projects = problem.getProjects();

        if (students == null || projects == null)
        {
            System.out.println("Basic conditions not met! Students or projects array is empty!");
            return false;
        }

        if (students.length > projects.length)
        {
            System.out.println("There is no matching possible. Too few projects, too many students!");
            return false;
        }

        for (int studentCount = 0; studentCount < students.length; studentCount++)
        {
            if (students[studentCount].getPreferences() == null)
            {
                System.out.println("Basic conditions not met! Student " + (studentCount + 1)
                        + " has no preferences");
                return false;
            }
        }

        for (int projectCount = 0; projectCount < projects.length; projectCount++)
        {
            if (projects[projectCount] == null)
            {
                System.out.println("Basic conditions not met! Student " + (projectCount + 1)
                        + " is null");
                return false;
            }
        }

        System.out.println("Matching::checkBasicConditions: Conditions met. Proceeding with solving the problem!\n");
        return true;
    }

    /**
     * Function to print the obtained the solution
     *
     * @param assignedProjects the array with the projects in order
     * @param cost             the cost of the solution represented by preferences from each student
     */
    private void printSolution(ArrayList<Project> assignedProjects, int cost)
    {
        System.out.println("Matching::printSolution: Problem solved. Solution is: ");
        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            System.out.println("Student: " + problem.getStudents()[studentCount].getName() + "\n" + assignedProjects.get(studentCount));
        }
        System.out.println("The cost is: " + cost);
    }

    /**
     * Greedy function to solve the matching problem
     *
     * @return true if a solution was found, false otherwise
     */
    public boolean greedySolve()
    {
        int cost = 0;
        if (!checkBasicConditions(problem) || problem == null)
        {
            return false;
        }

        ArrayList<Project> assignedProjects = new ArrayList<>();
        ArrayList<Project> projects = new ArrayList<>(Arrays.asList(problem.getProjects()));

        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            for (int projectCount = 0; projectCount < problem.getStudents()[studentCount].getPreferences().length; projectCount++)
            {
                if (projects.contains(problem.getStudents()[studentCount].getPreferences()[projectCount]))
                {
                    if (projectCount <= projects.size())
                    {
                        assignedProjects.add(studentCount,
                                problem.getStudents()[studentCount].getPreferences()[projectCount]);
                        projects.remove(problem.getStudents()[studentCount].getPreferences()[projectCount]);
                        cost += (projectCount + 1);
                    } else
                    {
                        System.out.println("Matching::greedySolve: No project left for student: " + studentCount +
                                "\n");
                        return false;
                    }
                }
            }
        }

        printSolution(assignedProjects, cost);
        convertToMatrix();
        return true;
    }

    public int neighbourOf(Student student1, Student student2)
    {
        int counter = 0;
        for (int preference = 0; preference < student1.getPreferences().length; preference++)
        {
            counter++;
        }
        for (int preference = 0; preference < student2.getPreferences().length; preference++)
        {
            counter++;
        }
        return counter;
    }

    /**
     * Function to convert the problem into a matrix of strings that describes the students and their prefernces
     *
     * @return the matrix of strings
     */
    public ArrayList<ArrayList<String>> convertToMatrix()
    {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            matrix.add(new ArrayList<>());
        }

        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            for (int projectCount = 0; projectCount < nrOfProjects; projectCount++)
                matrix.get(studentCount).add(projectCount, "-");
        }

        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            for (int projectCount = 0; projectCount < nrOfProjects; projectCount++)
            {
                Project[] toSearchIn = problem.getStudents()[studentCount].getPreferences();
                Project toBeSearched = problem.getProjects()[projectCount];
                if (Arrays.asList(toSearchIn).contains(toBeSearched))
                {
                    matrix.get(Character.getNumericValue(problem.getStudents()[studentCount].getName().charAt(1)) - 1)
                            .set(projectCount, problem.getProjects()[projectCount].getName());
                }
            }
        }
        return matrix;
    }

    /**
     * Function to convert the string matrix into a cost matrix which is needed for the maximum matching problem
     *
     * @param matrix the matrix to be converted
     * @return matrix of integers describing the cost of each edge
     */
    public ArrayList<ArrayList<Integer>> convertToCostMatrix(ArrayList<ArrayList<String>> matrix)
    {
        ArrayList<ArrayList<Integer>> values = new ArrayList<>();
        for (int row = 0; row < matrix.size(); row++)
        {
            values.add(new ArrayList<>());
        }

        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            for (int projectCount = 0; projectCount < nrOfProjects; projectCount++)
            {
                if (!matrix.get(studentCount).get(projectCount).equals("-"))
                {
                    values.get(studentCount).add(projectCount, projectCount + 1);
                } else
                {
                    values.get(studentCount).add(projectCount, Integer.MAX_VALUE);
                }
            }
        }
        return values;
    }

    /**
     * Function to check whether the problem can be solved
     *
     * @return true if the problem is solvable, false otherwise
     */
    public boolean hallTheoremCheck()
    {
        for (int studentCount = 0; studentCount < nrOfStuds; studentCount++)
        {
            if (problem.getStudents()[studentCount] != null)
            {
                for (int projectCount = studentCount + 1; projectCount < nrOfStuds; projectCount++)
                {
                    if (neighbourOf(problem.getStudents()[studentCount], problem.getStudents()[projectCount]) < 2)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
