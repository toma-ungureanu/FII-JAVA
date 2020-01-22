package oom;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author Toma-Florin Ungureanu
 */

@Entity
@Table(name = "ACTORS", schema = "DBA")
public class Actor implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String actorName;

    @Column(name = "ACTOR_ID")
    private Long actorId;

    public Actor(){}

    public Actor(String actorName, Long id ,Long actorId)
    {
        this.actorName = actorName;
        this.id = id;
        this.actorId = actorId;
    }

    public void setActorId(Long actorId)
    {
        this.id = actorId;
    }

    public void setActorName(String actorName)
    {
        this.actorName = actorName;
    }

    public Long getActorId()
    {
        return id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getActorName()
    {
        return actorName;
    }
}