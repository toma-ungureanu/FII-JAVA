package exercitiul2.graphs;

/**
 * @author Toma-Florin Ungureanu
 */

public class CompleteGraph extends GenericGraph
{
    /**
     * Function to create COMPLETE graph
     *
     * @param nrOfVertices represents the dimension of matrix
     * @return true if the creation was successful, false otherwise
     */
    public boolean create(int nrOfVertices)
    {
        boolean retVal = initialize(nrOfVertices);
        if (!retVal)
        {
            System.out.println("Task failed");
            return false;
        }
        // 1 everywhere besides the main diagonal
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = 1;

        for (int i = 0; i < matrix.length; i++)
            matrix[i][i] = 0;

        setDegree();
        return true;
    }

}
