package commands;
import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public class MessageCommand extends Command
{
    public MessageCommand(Socket socket) {super(socket);}

    public String parseCommand(String request)
    {
        return "MESSAGE: " + request;
    }


}
