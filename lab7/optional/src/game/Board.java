package game;

import graphs.Edge;
import graphs.Graph;
import player.ManualPlayer;
import player.Player;
import player.RandomPlayer;
import player.SmartPlayer;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Toma-Florin Ungureanu
 */
public class Board
{
    private final Graph complete;
    private int size;

    /**
     * Constructor for the Board class
     * The constructor creates a complete graph with number vertices
     *
     * @param number the number of vertices of the graph
     */
    public Board(int number)
    {
        size = number;
        complete = new Graph(number, size);
        for (int i = 0; i < number - 1; i++)
        {
            for (int j = i + 1; j < number; j++)
            {
                complete.add(new Edge(i, j));
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    /**
     * Function to extract an edge from the array
     * The function is synchronized, although there are no race conditions
     * for the edge list
     *
     * @return
     */
    public synchronized Edge extract(Player player)
    {
        while (!player.getLock())
        {
            try
            {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("Player's " + player.getName() + " turn\n");
        Edge edge = null;
        if (player instanceof RandomPlayer)
        {
            Random random = new Random();
            int randomNum = random.nextInt(complete.size());
            edge = (Edge) complete.get(randomNum);
            complete.remove(randomNum);
        } else if (player instanceof SmartPlayer)
        {
            edge = (Edge) complete.get(complete.size() - 1);
            complete.remove(complete.size() - 1);
        } else if (player instanceof ManualPlayer)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of the edge you want to select.\nThe edges are sorted\n");
            int edgeNo = scanner.nextInt();
            edge = (Edge) complete.get(edgeNo);
            complete.remove(edgeNo);
        }

        player.setLock(false);
        notifyAll();
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
