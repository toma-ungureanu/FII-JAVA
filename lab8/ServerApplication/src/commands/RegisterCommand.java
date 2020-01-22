package commands;
import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public class RegisterCommand extends Command
{
    public RegisterCommand(Socket socket)
    {
        super(socket);
    }

    public String parseCommand(String userName)
    {
        return "REGISTER: Hello, " + userName + "!";
    }

}
