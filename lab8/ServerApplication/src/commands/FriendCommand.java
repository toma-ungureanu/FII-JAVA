package commands;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Toma-Florin Ungureanu
 */
public class FriendCommand extends Command
{
    public FriendCommand(Socket socket)
    {
        super(socket);
    }
    public String parseCommand(String request)
    {
        StringTokenizer tokenizer = new StringTokenizer(request, ", ");
        List<String> friendList = new ArrayList<>();
        while(tokenizer.hasMoreTokens())
        {
            friendList.add(tokenizer.nextToken());
        }
        return "FRIEND: " + friendList.toString();
    }
}
