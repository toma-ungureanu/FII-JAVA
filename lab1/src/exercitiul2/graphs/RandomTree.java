package exercitiul2.graphs;


/**
 * @author Toma-Florin Ungureanu
 */

public class RandomTree extends GenericGraph
{
    /**
     * Function to create a random tree from a Prufer Sequence
     * Specifically, consider a labeled tree T with vertices {1, 2, ..., n}. At step i, remove the leaf with the
     * smallest label and set the ith element of the Prüfer sequence to be the label of this leaf's neighbour.
     * The Prüfer sequence of a labeled tree is unique and has length n − 2.
     * @param nrOfVertices + 2 is number of vertices of the graph
     * @return
     */
    public boolean create(int nrOfVertices)
    {
        boolean retVal = initialize(nrOfVertices + 2);
        if (!retVal)
        {
            System.out.println("Task failed");
            return false;
        }

        //create a random Prufer sequence
        int pruferSequence[] = new int[nrOfVertices];

        for (int i = 0; i < nrOfVertices; i++)
        {
            pruferSequence[i] = (int) (Math.random() * (nrOfVertices));
        }

        //create a set of vertices with degree 0
        int vertexSet[] = new int[nrOfVertices + 2];

        for (int i = 0; i < nrOfVertices + 2; i++)
        {
            vertexSet[i] = 0;
        }

        //
        for (int i = 0; i < nrOfVertices; i++)
        {
            vertexSet[pruferSequence[i]] += 1;
        }

        int j =0;
        for (int i = 0; i < nrOfVertices; i++)
        {
            for (j = 0; j < nrOfVertices + 2; j++)
            {
                if (vertexSet[j] == 0)
                {
                    vertexSet[j] = -1;
                    matrix[j][pruferSequence[i]] = matrix[pruferSequence[i]][j] = 1;
                   // System.out.println("(" + (j) + "," + pruferSequence[i] + ")  ");
                    vertexSet[pruferSequence[i]]--;
                    break;
                }
            }
        }

        int x = 0, y = 0;
        for(int i = 0; i < nrOfVertices + 2; i++)
        {
            if (vertexSet[i] == 0 && j == 0 )
            {
                x = i;
              //  System.out.print("(" + (i+1) + ",");
                j++;
            }

            else if (vertexSet[i] == 0 && j == 1 )
            {
                y = i;
               // System.out.print((i + 1) + ") ");
            }
        }
        matrix[y][pruferSequence[x]] = matrix[pruferSequence[x]][y] = 1;
        System.out.println();

        return true;
    }

}
