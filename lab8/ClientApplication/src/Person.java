import java.io.IOException;
import java.net.Socket;

/**
 * @author Toma-Florin Ungureanu
 */
public class Person extends SocialNetworkClient
{
   Person()
   {
       handleConnection();
   }

    public static void main(String[] args)
    {
        Person person = new Person();
    }
}
