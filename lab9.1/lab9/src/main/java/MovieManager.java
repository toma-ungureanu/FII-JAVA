
import controllers.MovieController;
import controllers.PersonController;
import oom.Movie;
import oom.Person;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * @author Toma-Florin Ungureanu
 */

public class MovieManager
{
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MoviesPU");

    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.print("Input command:");
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0])
            {
                case "create-person":
                    createPerson(params[1]); //the person name
                    break;
                case "create-movie":
                    createMovie(params[1]); //the movie name and the director
                    break;
                case "list-movie":
                    listMovie(params[1]); //the movie name
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    private void createPerson(String personName)
    {
        Person person = new Person(personName);
        PersonController personController = new PersonController(emf);
        personController.create(person);
    }

    private void createMovie(String movieName)
    {
        Movie movie = new Movie(movieName);
        MovieController movieController = new MovieController(emf);
        movieController.create(movie);
    }

    private void listMovie(String movieName)
    {
        MovieController movieController = new MovieController(emf);
        Movie movie = movieController.findByName(movieName);
        System.out.println("Movie with id: " +
                movie.getId() +
                "and title: " +
                movie.getMovieName());
    }

    public static void main(String args[])
    {
        new MovieManager().run();
    }
}
