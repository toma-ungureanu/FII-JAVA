package commands;
import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public class ReadCommand extends Command
{
    public ReadCommand(Socket socket) { super(socket);}
    public String parseCommand(String request)
    {
        return "READ: You are now reading the messages received by the server: " + request;
    }
}
