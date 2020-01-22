package oom;

/**
 * @author Toma-Florin Ungureanu
 */
public class Actor extends Person
{
    private String name;
    private int actorId;

    public Actor()
    {

    }

    public Actor(String name, int actorId)
    {
        this.name = name;
        this.actorId = actorId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getActorId()
    {
        return actorId;
    }

    public void setActorId(int actorId)
    {
        this.actorId = actorId;
    }
}