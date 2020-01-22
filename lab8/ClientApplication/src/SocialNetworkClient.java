import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * @author Toma-Florin Ungureanu
 */
public class SocialNetworkClient
{
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
    private Set<String> friendList = new HashSet<>();
    private Socket clientSocket;
    private boolean loggedIn = false;
    private String loggedAs;

    public SocialNetworkClient(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    public SocialNetworkClient()
    { }

    public String getName()
    {
        return loggedAs;
    }

    public Set<String> getFriendList()
    {
        return friendList;
    }

    public void closeClientSocket()
    {
        try
        {
            this.clientSocket.close();
        } catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
        }
    }

    protected void handleConnection()
    {
        Socket clientSocket;
        SocialNetworkClient client;
        try
        {
            clientSocket = new Socket(SERVER_ADDRESS, PORT);
            client = new SocialNetworkClient(clientSocket);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner in = new Scanner(clientSocket.getInputStream());

            System.out.println("Enter your commands: ");
            while (true)
            {
                String request = client.readFromKeyboard();
                if (request.equalsIgnoreCase("exit"))
                {
                    out.close();
                    in.close();
                    client.closeClientSocket();
                    break;
                } else
                {
                    client.sendRequestToServer(clientSocket, request, out, in);
                }
            }
        } catch (Exception excp)
        {
            System.out.println("Server is closed");
            System.exit(1);
        }
    }

    /**
     * Create a new socket with the SERVER_ADDRESS and PORT to send to
     *
     * @param request
     */
    void sendRequestToServer(Socket socket, String request, PrintWriter out, Scanner in)
    {

        out.println(request);
        if (!in.hasNextLine())
        {
            System.out.println("Connection was closed, the client will exit");
            System.exit(0);
        } else
        {
            String response = in.nextLine();
            Scanner scanner = new Scanner(response);
            String shortenedResp = scanner.next();

            switch (shortenedResp)
            {
                case "LOGIN:":
                {
                    if (loggedIn)
                    {
                        System.out.println("You are already logged in as: " + loggedAs);
                        break;
                    }

                    else
                    {
                        String userName = request.substring(6);
                        loggedIn = true;
                        loggedAs = userName;
                        System.out.println("You are now logged in as " + loggedAs);

                        break;
                    }
                }
                case "FRIEND:":
                {
                    if (loggedIn)
                    {
                        String friendListString = response.substring(8);
                        List<String> friendArray = Arrays.asList(friendListString.substring(1,
                                friendListString.length() - 1)
                                .split(", "));

                        friendList.addAll(friendArray);
                        System.out.println("You now have: " + friendList.size() + " friends");
                        System.out.println("Your friends are: " + friendList.toString().substring(1,
                                friendList.toString().length() - 1));
                        break;
                    } else
                    {
                        System.out.println("You must log in first!");
                    }
                }
                case "REGISTER:":
                {
                    System.out.println(response.substring(10));
                    break;
                }
                case "MESSAGE:":
                {
                    if (!loggedIn)
                    {
                        System.out.println("You must login first!");
                        break;
                    } else
                    {
                        StringTokenizer tokenizer = new StringTokenizer(response, ":");
                        List<String> tokens = new ArrayList<>();
                        while (tokenizer.hasMoreTokens())
                        {
                            tokens.add(tokenizer.nextToken());
                        }

                        break;
                    }
                }
                case "READ:":
                {
                    System.out.println(response.substring(5));
                    break;
                }
                case "STOP:":
                {
                    System.out.println(response.substring(6));
                    try
                    {
                        socket.close();
                    } catch (IOException excp)
                    {
                        System.out.println(excp.getMessage() + " " + excp.getStackTrace());
                    }
                }
            }
        }
    }

    /**
     * Read the command(s) from keyboard
     *
     * @return the command(s)
     */
    private String readFromKeyboard()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}