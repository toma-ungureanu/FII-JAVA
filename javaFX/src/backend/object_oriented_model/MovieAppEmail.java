package backend.object_oriented_model;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieAppEmail implements UserAndEmail
{
    private int id;
    private String email;

    public MovieAppEmail()
    { }

    public MovieAppEmail(int id, String email)
    {
        this.id = id;
        this.email = email;
    }

    public void setMovieAppEmail(int id, String email)
    {
        this.id = id;
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
