package controllers;
import oom.Movie;

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
        Query query = em.createQuery("select m.movieName from Movie m where m.movieName like :name");
        List movies = query.setParameter("name", movieName).getResultList();
        em.close();
        Movie movie = new Movie();
        movie.setId((Long)movies.get(1));
        movie.setMovieName((String)movies.get(0));
        return movie;
    }
}
