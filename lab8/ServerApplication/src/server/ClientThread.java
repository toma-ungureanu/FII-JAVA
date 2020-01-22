package server;

import commands.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Toma-Florin Ungureanu
 */
public class ClientThread extends Thread
{
    private static final int EXCEEDTIME = 20;
    private final List<ClientThread> clientThreads;
    private final SocialNetworkServer socialNetworkServer;
    private boolean active;
    private ClientMonitor clientMonitor;
    private List<String> printLog = new ArrayList<>();
    private Socket socket;

    public ClientThread(Socket socket, List<ClientThread> clientThreads, SocialNetworkServer socialNetworkServer)
    {
        this.socket = socket;
        this.clientThreads = clientThreads;
        this.clientMonitor = new ClientMonitor(this);
        this.socialNetworkServer = socialNetworkServer;
        active = true;
        printLog.add(printMessages());
        System.out.println(printLog);
    }

    public void setActive(boolean isActive)
    {
        this.active = isActive;
    }

    public void setSocket(boolean isOpen)
    {
        if (!isOpen)
        {
            try
            {
                socket.close();
            } catch (IOException excp)
            {
                System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            }

        }
    }

    private String printMessages()
    {
        return "Created new thread for socket: " + socket + " and id: " + this;
    }

    /**
     * Create a new thread for each client
     */
    public void run()
    {
        Thread thread = new Thread(clientMonitor);
        thread.start();
        while (active)
        {
            sendResponseToClient(thread);
        }
        System.out.println("Closing client's socket. Id: " + this.getId());
    }

    /**
     * Execute the command(s) given to the server
     *
     * @param request the command(s) to be executed
     * @return the String of command(s)
     */
    private String execute(String request)
    {
        Scanner scanner = new Scanner(request);
        if (scanner.hasNextLine())
        {
            String shortenedReq = scanner.next();
            return commandHandler(request, shortenedReq);
        } else return "Invalid command";
    }

    private void sendResponseToClient(Thread thread)
    {
        try
        {
            if (!socket.isClosed())
            {
                Scanner in = new Scanner(socket.getInputStream()); //client -> server
                String request;
                if (in.hasNextLine())
                {
                    thread.interrupt();
                    request = in.nextLine();
                    String response = execute(request);
                    PrintWriter out = new PrintWriter(socket.getOutputStream()); //server -> client stream
                    out.println(response);
                    out.flush();
                    if (response.substring(0, 5).equals("STOP:"))
                    {
                        System.out.println("Ending all connections...");
                        active = false;
                        clientMonitor.setRunning(false);
                        synchronized (clientThreads)
                        {
                            for (ClientThread clientThread : clientThreads)
                            {
                                clientThread.setActive(false);
                                clientThread.setSocket(false);
                                clientThread.join(1000);
                            }
                        }
                        socialNetworkServer.stop();
                    }
                }
            } else
            {
                System.out.println("Removing client from array");
                clientThreads.remove(this);
                active = false;
            }
        } catch (IOException | InterruptedException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }

    }

    private String commandHandler(String request, String shortenedReq)
    {
        Command command;
        switch (shortenedReq)
        {
            case "login":
            {
                command = new LoginCommand(socket);
                String userName = this.getId() + " " + request.substring(6);
                return ((LoginCommand) command).parseCommand(userName);
            }
            case "register":
            {
                command = new RegisterCommand(socket);
                String userName = request.substring(9);
                return ((RegisterCommand) command).parseCommand(userName);
            }
            case "friend":
            {
                command = new FriendCommand(socket);
                String friendList = request.substring(7);
                return ((FriendCommand) command).parseCommand(friendList);
            }
            case "send":
            {
                command = new MessageCommand(socket);
                String message = request.substring(5);
                return ((MessageCommand) command).parseCommand(message);
            }
            case "read":
            {
                command = new ReadCommand(socket);
                return ((ReadCommand) command).parseCommand(printLog.toString());
            }

            case "message":
            {
                command = new MessageCommand(socket);
                String message = request.substring(8);
                String overHead = this.getId() + " " + message;
                return ((MessageCommand) command).parseCommand(overHead);
            }

            case "stop":
            {
                command = new StopCommand(socket);
                return ((StopCommand) command).parseCommand(request);
            }
            default:
            {
                return "Invalid command!";
            }
        }
    }
}
