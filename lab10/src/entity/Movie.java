package entity;

/**
 * @author Toma-Florin Ungureanu
 */
public class Movie
{
    private String name;
    private String directorName;

    public Movie()
    {

    }

    public Movie(String name, String directorName)
    {
        this.name = name;
        this.directorName = directorName;
    }

    public String getDirectorName()
    {
        return directorName;
    }

    public void setDirectorName(String directorName)
    {
        this.directorName = directorName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
