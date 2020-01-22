import game.Board;
import game.Game;
import player.Player;

public class MainCompulsory
{
    public static void main(String[] args)
    {
        Game game = new Game();
        game.setBoard(new Board(6));
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
       //game.addPlayer(new Player("Player 3"));
        game.start();
    }
}
