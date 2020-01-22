package player;

import game.Board;
import game.CliqueGame;
import game.ConnectivityGame;
import game.Game;
import graphs.Edge;
import graphs.Graph;

/**
 * @author Toma-Florin Ungureanu
 */
public abstract class Player implements Runnable
{
    private static final int THINKING_TIME = 1000;

    private boolean available = false;
    private String name;
    private Game game;
    private Graph graph;

    public Player(String name)
    {
        this.name = name;
    }

    /**
     * The player extracts an edge from the graph and puts it in a new created one owned by it
     *
     * @return true if the player can continue to extract edges from the graph, false otherwise
     * @throws InterruptedException Thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity
     */
    public boolean play() throws InterruptedException
    {
        Board board = game.getBoard();
        if (board.getComplete().isEmpty()) { System.exit(1); }
        graph.add(board.extract(this));

        Thread.sleep(THINKING_TIME);

        if(game instanceof ConnectivityGame)
        {
            if (graph.isSpanningTree())
            {
                winGame();
            }
        }
        else if(game instanceof CliqueGame)
        {
            if(graph.isClique())
            {
                winGame();
            }
        }

        return true;
    }

    public void winGame()
    {
        game.setWinner(this);
        System.out.println(this.name + " Wins!\n");
        for (Object print : graph)
        {
            System.out.println("(" + ((Edge) print).getNumber1() + ", " + ((Edge) print).getNumber2() + ")");
        }
        System.out.println();
        game.setEndGame(true);
        System.exit(0);
    }

    public void setLock(boolean val)
    {
        available = val;
    }

    public boolean getLock()
    {
        return available;
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
            graph = new Graph(game.getBoard().getComplete().getNumberOfVertices(), game.getBoard().getSize());
            while (!game.getBoard().isEmpty() && !game.getTimeKeeper().isOver())
            {
                synchronized (this)
                {
                    setLock(true);
                    play();
                    notifyAll();
                }
            }
        } catch (InterruptedException excp)
        {
            System.out.println(excp.getMessage() + "\n" + excp.getCause());
        }
    }

    public String getName()
    {
        return name;
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