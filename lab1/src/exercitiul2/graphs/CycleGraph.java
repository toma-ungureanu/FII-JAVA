package exercitiul2.graphs;

/**
 * @author Toma-Florin Ungureanu
 */

public class CycleGraph extends GenericGraph
{
    /**
     * Function to create CYCLE graph
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

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (i + 1 == j || j + 1 == i)
                {
                    matrix[i][j] = 1;
                }
            }
        }
        matrix[0][dim - 1] = 1;
        matrix[dim - 1][0] = 1;

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (matrix[i][j] == 0)
                {
                    int random = (int) (Math.random() * 2);
                    matrix[i][j] = random;
                    matrix[j][i] = random;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++)
        {
            matrix[i][i] = 0;
        }

        setDegree();
        return true;
    }
}
