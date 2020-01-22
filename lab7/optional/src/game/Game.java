package game;

import player.Player;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Toma-Florin Ungureanu
 */
public abstract class Game
{
    private Board board;
    private Player winner;
    private static final TimeKeeper timeKeeper = new TimeKeeper();
    private final LinkedList<Player> players = new LinkedList<>();

    /**
     * Method to add a player to the game
     * @param player to be added
     */
    public void addPlayer(Player player)
    {
        players.add(player);
        player.setGame(this);
    }

    public void setEndGame(boolean val)
    {
        timeKeeper.setEndGame(val);
    }

    public void setWinner(Player player)
    {
        this.winner = player;
    }

    public Board getBoard()
    {
        return board;
    }

    public TimeKeeper getTimeKeeper()
    {
        return timeKeeper;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }


    /**
     * Method to start the task of each player to extract one edge at a time
     */
    public void start()
    {

        //This is with Array of threads

//        List<Thread> threads = new ArrayList<>();
//        List<Runnable> runnables = new ArrayList<>();
//        for (int player = 0; player < players.size(); player++)
//        {
//            runnables.add(players.get(player));
//            threads.add(new Thread(runnables.get(player)));
//            threads.get(player).start();
//        }

        //This is with thread pool
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(players.size() + 1);
        Thread daemon = new Thread(timeKeeper);
        daemon.setDaemon(true);
        daemon.start();
        for (Player player : players)
        {
            tpe.execute(player);
        }
        tpe.shutdown();
    }
}
