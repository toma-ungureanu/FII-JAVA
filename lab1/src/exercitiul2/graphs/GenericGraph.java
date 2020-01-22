package exercitiul2.graphs;

import java.util.Arrays;

/**
 * @author Toma-Florin Ungureanu
 */

public abstract class GenericGraph
{
    protected int dim;
    protected int[][] matrix;
    protected long NrOfEdges;
    protected long[] degrees;

    /**
     * abstract function to create a graph
     */
    public abstract boolean create(int nrOfVertices);

    /**
     * sets the number of vertices in the graph as dim
     *
     * @param dim is the number of vertices in the graph
     */
    private void setDim(int dim)
    {
        this.dim = dim;
    }

    /**
     * validate the arguments and allocate space for
     * matrix and degrees
     */
    protected boolean initialize(int input)
    {
        if (input % 2 == 0)
        {
            System.out.println("Enter an ODD number!");
            return false;
        }

        setDim(input);
        matrix = new int[dim][dim];
        degrees = new long[dim];
        return true;
    }

    /**
     * Method to calculate the degree of each vertex in the graph
     */
    void setDegree()
    {
        int j = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            while (j < matrix[i].length)
            {
                if (matrix[i][j] == 1)
                {
                    degrees[i]++;
                }
                j++;
            }
            j = 0;
        }
    }

    /**
     * Function to get the dimension of matrix
     *
     * @return dim
     */
    public int getDim()
    {
        return dim;
    }

    /**
     * print a row from the matrix parameter
     *
     * @param line is the number of the row
     */
    private void printRow(int line)
    {
        // print the sides of the box and the number
        for (int j = 0; j < matrix[line].length; j++)
        {
            System.out.print("\t\u2503\t");
            System.out.print(matrix[line][j]);
        }

        System.out.print("\t\u2503\t");
        System.out.println();

        // print the lower part of the box
        if (line < matrix.length - 1)
        {
            System.out.print("\t\u2503");
            for (int j = 0; j < matrix[line].length - 1; j++)
            {
                System.out.print("\t\u2501\t\u254B");
            }

            System.out.print("\t\u2501\t\u2503");
            System.out.println();
        }
    }

    /**
     * Method to print the graph
     */
    public void displayGraph()
    {
        //the left upper corner
        System.out.print("\t\u250F\t");

        //the upper part of the box
        for (int i = 0; i < matrix.length - 1; i++)
        {
            System.out.print("\u2501\t\u2530\t");
        }

        System.out.print("\u2501\t");

        //the right upper corner
        System.out.print("\u2513");
        System.out.println();

        //print the sides of the box and the number
        for (int i = 0; i < matrix.length; i++)
        {
            printRow(i);
        }

        //print the lower left corner
        System.out.print("\t\u2517\t");

        //print upper part of the box
        for (int i = 0; i < matrix.length - 1; i++)
        {
            System.out.print("\u2501\t\u253B\t");
        }

        System.out.print("\u2501\t");

        //print lower right corner
        System.out.print("\u251B");
        System.out.println();
    }

    /**
     * Method to print the number of edges in graph
     */
    public void displayNrOfEdges()
    {
        //check for the main diagonal if we find any "1"
        //undirected graph is symmetric against the main diagonal
        long edges = 0;
        System.out.println();
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (i < j && matrix[i][j] == 1)
                {
                    edges++;
                }
            }
        }
        System.out.println("The number of edges is: " + edges);
        NrOfEdges = edges;
    }

    /**
     * Function to search for the maximum element in the degrees array
     */
    public long maxDegree()
    {
        System.out.println();
        long max = Arrays.stream(degrees).max().getAsLong();
        System.out.println("\u0394(G) is: " + max);

        return max;
    }

    /**
     * Function to search for the minimum element in the degrees array
     */
    public long minDegree()
    {
        System.out.println();
        long min = Arrays.stream(degrees).min().getAsLong();
        System.out.println("\u03B4(G) is: " + min);
        System.out.println();
        return min;
    }

    /**
     * chech if the graph matches one of its properties:
     * sum of degrees = 2 * number of edges
     *
     * @return true if the graph matches the property; false otherwise
     */
    public boolean checkSumDegrees()
    {
        int sum = 0;

        for (long i : degrees)
        {
            sum += i;
        }

        return (sum == 2 * NrOfEdges);
    }

}
