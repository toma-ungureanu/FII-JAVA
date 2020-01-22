package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Toma-Florin Ungureanu
 */

@Entity
@Table(name = "PERSONS")
public class Person implements Serializable
{
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "persons_id_seq")
    @GeneratedValue(generator = "sequence")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    public Person()
    {

    }

    public Person(String personName)
    {
        this.name = personName;
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
