import server.SocialNetworkServer;

/**
 * @author Toma-Florin Ungureanu
 */
public class SocialNetwork
{
    SocialNetworkServer socialNetworkServer;

    public SocialNetwork()
    {
        socialNetworkServer = new SocialNetworkServer();
    }

    public SocialNetworkServer getSocialNetworkServer()
    {
        return socialNetworkServer;
    }

    public static void main(String[] args)
    {
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.getSocialNetworkServer().handleConnection();
    }
}
