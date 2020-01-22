package game;

import graphs.Edge;
import graphs.Graph;

import java.util.Collections;

/**
 * @author Toma-Florin Ungureanu
 */
public class Board
{
    private final Graph complete;

    /**
     * Constructor for the Board class
     * The constructor creates a complete graph with number vertices
     * @param number the number of vertices of the graph
     */
    public Board(int number)
    {
        complete = new Graph(number);

        for (int i = 0; i < number - 1; i++)
        {
            for (int j = i + 1; j < number; j++)
            {
                complete.add(new Edge(i, j));
            }
        }

        Collections.shuffle(complete);
    }

    /**
     * Function to extract an edge from the array
     * The function is synchronized, although there are no race conditions
     * for the edge list
     * @return
     */
    public synchronized Edge extract()
    {
        Edge edge = (Edge)complete.pollFirst();
        System.out.println("Extracted: (" + edge.getNumber1() + ", " + edge.getNumber2() + ")\n");
        return edge;
    }

    public boolean isEmpty()
    {
        return complete.isEmpty();
    }

    public Graph getComplete()
    {
        return complete;
    }
}
