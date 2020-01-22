import com.github.javafaker.Faker;
import dao.MovieController;
import dao.MovieListController;
import dao.PersonController;
import database.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
    {
        try {
            PersonController persons = new PersonController();
            MovieController movies = new MovieController();

            persons.create("Francis Ford Coppola");
            persons.create("Marlon Brando");
            persons.create("Al Pacino");

            for(int i = 0; i < 10; i++)
            {
                Faker faker = new Faker();
                persons.create(faker.name().fullName());
            }

            Database.commit();
            movies.create("The Godfather");

            List<String> fakerList = new ArrayList<>();
            for(int i = 0; i < 10; i++)
            {
                Faker faker = new Faker();
                String fakeMovieName = faker.book().title();
                fakerList.add(fakeMovieName);
                movies.create(fakeMovieName);
            }

            //Add other movies to the database
            MovieListController movieListController = new MovieListController();
            movieListController.setMovieList(fakerList);
            movieListController.createReport();
            Database.commit();
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
