package commands;
import server.SocialNetworkServer;
import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public abstract class Command
{
    private Socket socket = null;
    private SocialNetworkServer server = null;


    Command(Socket socket)
    {
        this.socket = socket;
    }
    abstract String parseCommand(String request);

}
