package exercitiul2.graphs;

/**
 * @author Toma-Florin Ungureanu
 */

public class RandomGraph extends GenericGraph
{
    private boolean[] visited = null;   // marked[v] = has vertex v been marked?

    /**
     * Function to create RANDOM graph
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

        visited = new boolean[dim];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (i < j)
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

    /**
     * Method to reset the visited array
     */
    private void resetVisited()
    {
        for (int i = 0; i < dim; i++)
        {
            visited[i] = false;
        }
    }

    /**
     * Method for depth-first search algortihm to detect the connected components
     *
     * @param vertex we search the vertices which have an edge with the given one
     */
    private void dfs(int vertex)
    {
        int i;
        visited[vertex] = true;
        for (i = 0; i < dim; i++)
        {
            if (matrix[vertex][i] == 1 && !visited[i])
            {
                dfs(i);
            }
        }
    }

    /**
     * Function to count the number of connected components in the graph
     *
     * @return the number of connected components
     */
    public int nrOfConnectedComponents()
    {
        int count = 0;
        for (int i = 1; i < dim; i++)
        {
            if (!visited[i])
            {
                count++;
                dfs(i);
            }
        }

        int count1 = 0;
        for(int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if(matrix[i][j] == 0)
                {
                    count1++;
                }
            }
        }

        if(count1 == dim * dim)
        {
            return dim;
        }

        return count;
    }

    /**
     * function to check if the graph is connected
     *
     * @return true if the graph is connected, false otherwise
     */
    public boolean checkConnected()
    {
        resetVisited();
        int i;

        dfs(1);
        for (i = 0; i < dim; i++)
        {
            if (!visited[i])
            {
                resetVisited();
                return false;
            }
        }

        resetVisited();
        return true;
    }
}

