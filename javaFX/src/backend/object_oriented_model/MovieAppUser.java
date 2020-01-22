package backend.object_oriented_model;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieAppUser implements UserAndEmail
{
    private int id;
    private String lastName;
    private String firstName;
    private String password;

    public MovieAppUser() {}

    public MovieAppUser(int id, String lastName, String firstName, String password)
    {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

    public void setMovieAppUser(int id, String lastName, String firstName, String password)
    {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
