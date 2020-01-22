package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class SocialNetworkServer
{
    private static final int PORT = 8100;
    private ServerSocket serverSocket;
    private boolean running = false;
    private static final int maxClients = 100;
    private static final List<ClientThread> clients = new ArrayList<>(maxClients);

    public void handleConnection()
    {
        SocialNetworkServer server = new SocialNetworkServer();
        server.init();
        server.waitForClients();
    }

    /**
     * Start the server
     */
    private void init()
    {
        try
        {
            System.out.println("*Starting up sound*");
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Listening for commands!");
        } catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }

    /**
     * As long as the server is running, listen for sockets
     */
    private void waitForClients()
    {
        try
        {
            while(running)
            {
                clients.add(new ClientThread(serverSocket.accept(),clients, this));
                clients.get(clients.size() - 1).start();
            }
        } catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }

    public void stop() throws IOException
    {
        System.out.println("Closing server socket");
        this.running = false;
        serverSocket.close();
    }

}