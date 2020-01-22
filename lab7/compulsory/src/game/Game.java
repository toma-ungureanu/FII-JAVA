package game;

import player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Toma-Florin Ungureanu
 */
public class Game
{
    private Board board;
    private Player winner;
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

    public void setWinner(Player player)
    {
        this.winner = player;
    }

    public Player getWinner()
    {
        return winner;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public List<Player> getPlayers()
    {
        return players;
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
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(players.size());
        for (Player player : players)
        {
            tpe.execute(player);
        }
        tpe.shutdown();
    }
}
