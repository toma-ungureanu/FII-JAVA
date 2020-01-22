package oom;

/**
 * @author Toma-Florin Ungureanu
 */
public class Person
{
    private String name;
    private int id;

    public Person(String name, int specificId)
    {
        this.name = name;
        this.id = specificId;
    }

    public Person()
    {
        this.name = "John Doe";
        this.id = 1;
    }
}
