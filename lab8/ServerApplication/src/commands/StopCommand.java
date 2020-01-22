package commands;

import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public class StopCommand extends Command
{
    public StopCommand(Socket socket) { super(socket); }
    public String parseCommand(String request)
    {
        return "STOP: Stopping the server...";
    }
}
