package player;

import game.Board;
import game.Game;
import graphs.Edge;
import graphs.Graph;

/**
 * @author Toma-Florin Ungureanu
 */
public class Player implements Runnable
{
    private static final int THINKING_TIME = 100;
    private String name;
    private Game game;
    private Graph graph;

    public Player(String name)
    {
        this.name = name;
    }

    /**
     * The player extracts an edge from the graph and puts it in a new created one owned by it
     * @return true if the player can continue to extract edges from the graph, false otherwise
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied,
     * and the thread is interrupted, either before or during the activity
     */
    private boolean play() throws InterruptedException
    {
        Board board = game.getBoard();
        if (board.isEmpty()) { return false; }
        graph.add(board.extract());

        Thread.sleep(THINKING_TIME);

        if (graph.isSpanningTree())
        {
            game.setWinner(this);
            System.out.println(this.name + " Wins!\n");
            for(Object print : graph)
            {
                System.out.println("(" +((Edge) print).getNumber1() + ", " + ((Edge) print).getNumber2() + ")");
            }
            System.out.println();
            System.exit(0);
        }

        return true;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    /**
     * Method used for playing the game until the board is empty
     */
    @Override
    public void run()
    {
      try
      {
         graph = new Graph(game.getBoard().getComplete().getNumberOfVertices());
         while(!game.getBoard().isEmpty())
         {
             play();
         }
      }
      catch (InterruptedException excp)
      {
          System.out.println(excp.getMessage() + "\n" + excp.getCause());
      }
    }

    @Override
    public String toString()
    {
        return "Player{" +
                "name='" + name + '\'' +
                ", game=" + game +
                ", graph=" + graph +
                '}';
    }
}