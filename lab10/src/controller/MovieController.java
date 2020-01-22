package controller;

import entity.Movie;
import entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieController
{
    private EntityManagerFactory emf;

    public MovieController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public void create(Movie movie)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        em.close();
    }

    public Movie findByName(String movieName)
    {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select m from Movie m where m.name like :name");
        List movies = query.setParameter("name", movieName).getResultList();
        em.close();
        return movies.isEmpty() ? null : (Movie)movies.get(0);
    }
}
