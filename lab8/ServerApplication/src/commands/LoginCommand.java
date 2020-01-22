package commands;
import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public class LoginCommand extends Command
{
    public LoginCommand(Socket socket){ super(socket);}
    public String parseCommand(String userName)
    {
        return "LOGIN: You are now logged in as: " + userName;
    }

}
