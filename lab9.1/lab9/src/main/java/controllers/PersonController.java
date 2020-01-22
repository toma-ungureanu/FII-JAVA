package controllers;

import oom.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class PersonController
{
    private EntityManagerFactory emf;

    public PersonController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public void create(Person person)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public Person findByName(String personName)
    {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select t from Person t where t.name like :name");
        List persons = query.setParameter("name", personName).getResultList();
        em.close();
        return persons.isEmpty() ? null : (Person) persons.get(0);
    }
}