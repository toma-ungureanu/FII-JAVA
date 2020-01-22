package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Toma-Florin Ungureanu
 */
public class MovieListController
{
    private List<String> movieList = new ArrayList<>();

    public MovieListController()
    {

    }

    public boolean addToList(String movieName)
    {
        MovieController movieController = new MovieController();
        try
        {
            if (movieController.findByName(movieName) != null)
            {
                this.movieList.add(movieName);
            }
            return true;
        }
        catch (SQLException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getStackTrace());
            return false;
        }
    }

    public void removeFromList(String movieName)
    {
        this.movieList.remove(movieName);
    }

    public List<String> getMovieList()
    {
        return movieList;
    }

    public boolean setMovieList(List<String> movieList)
    {
        for(String movie : movieList)
        {
            if(!addToList(movie))
            {
                return false;
            }
        }
        return true;
    }

    public void createReport()
    {
        StringBuilder html = new StringBuilder();
        html.append("<!doctype html>\n");
        html.append("<html lang='en'>\n");

        html.append("<head>\n");
        html.append("<meta charset='utf-8'>\n");
        html.append("<title>Report</title>\n");
        html.append("</head>\n\n");

        html.append("<body>\n");
        html.append("<h1>Movie list</h1>\n");
        // Make a list in HTML
        html.append("<ul>\n");

        for(String movie: movieList)
        {
            html.append("<li>" + movie + "</li>\n");
        }

        html.append("</ul>\n");
        html.append("</body>\n\n");

        html.append("</html>");

        try
        {
            File catalogFile = new File("C:\\Users\\thomi\\IdeaProjects\\lab9\\src\\report\\report.html");
            catalogFile.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(catalogFile.getPath());
            PrintWriter writer = new PrintWriter(fileOut);
            writer.write(html.toString());
            writer.close();
            fileOut.close();
            System.out.println("Report was saved in: " + catalogFile.getPath());
        } catch (IOException excp)
        {
            System.out.println(excp.getMessage() + " " + excp.getCause());
            System.exit(1);
        }
    }
}
