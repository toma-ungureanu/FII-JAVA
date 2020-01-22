package oom;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Toma-Florin Ungureanu
 */

@Entity
@Table(name = "DIRECTORS", schema = "DBA")
public class Director implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String directorName;

    @Column(name = "DIRECTOR_ID")
    private Long directorId;

    public Director(){}

    public Director(String directorName, Long id, Long directorId)
    {
        this.directorName = directorName;
        this.id = id;
        this.directorId = directorId;
    }

    public Long getDirectorId()
    {
        return directorId;
    }

    public String getDirectorName()
    {
        return directorName;
    }

    public void setDirectorName(String directorName)
    {
        this.directorName = directorName;
    }

    public void setDirectorId(Long directorId)
    {
        this.directorId = directorId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
