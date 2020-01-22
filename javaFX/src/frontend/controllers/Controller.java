package frontend.controllers;

import backend.object_oriented_model.AppMovie;
import frontend.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

/**
 * @author Toma-Florin Ungureanu
 */
public abstract class Controller
{
    void nextScene(Controller controller, String fxml, ActionEvent event, String email) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Parent parent = loader.load();
        Scene loginScene = new Scene(parent);

        if(controller instanceof HomeController)
        {
            HomeController homeController = loader.getController();
            homeController.setCredentials(email);
            homeController.setTableView();
        }

        else if(controller instanceof FilterByController)
        {
            FilterByController filterByController = loader.getController();
            filterByController.setCredentials(email);
        }

        else if(controller instanceof MyProfileController)
        {
            MyProfileController myProfileController = loader.getController();
            myProfileController.setCredentials(email);
        }

        Node node = (Node)event.getSource();
        Stage home = (Stage)node.getScene().getWindow();

        home.setScene(loginScene);
        home.show();
    }

    ObservableList<MovieModel> parseMovieList(String result)
    {
        ObservableList<MovieModel> observableList = FXCollections.observableArrayList();
        //all the movies in the search are delimited by @
        StringTokenizer movieTokenizer = new StringTokenizer(result, "@");
        AppMovie appMovie;
        while (movieTokenizer.hasMoreTokens())
        {
            //all the columns in the movie are delimited by ^
            StringTokenizer columnsTokenizer = new StringTokenizer(
                    movieTokenizer.nextToken(), "^");

            //create a new object for each row
            MovieModel movieModel = new MovieModel();

            int currentColumn = 0;
            String header, strippedString, receivedString;
            while (columnsTokenizer.hasMoreTokens())
            {
                currentColumn++;

                switch (currentColumn)
                {
                    case 1:
                    {
                        header = "titlu=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setTitle(strippedString);
                            }
                        } else movieModel.setTitle("-");
                        break;
                    }

                    case 2:
                    {
                        header = "nota=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setRating(Float.parseFloat(strippedString));
                            }
                        } else
                        {
                            movieModel.setRating(Float.parseFloat("0"));
                        }
                        break;
                    }

                    case 3:
                    {
                        header = "genuri=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setGenre(strippedString);
                            }
                        } else
                        {
                            movieModel.setGenre("-");
                        }
                        break;
                    }

                    case 4:
                    {
                        header = "dataLansare=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                SimpleDateFormat dateParser = new SimpleDateFormat("dd-MMM-yy");
                                try
                                {
                                    java.util.Date parsedDate = dateParser.parse(strippedString);
                                    movieModel.setReleaseDate(new java.sql.Date(parsedDate.getTime()));
                                }
                                catch (ParseException excp)
                                {
                                    System.out.println(excp.getMessage() + " " + excp.getStackTrace());
                                    return null;
                                }
                            }
                        } else
                        {
                            movieModel.setReleaseDate(null);
                        }
                        break;
                    }

                    case 5:
                    {
                        header = "buget=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setBudget(Integer.parseInt(strippedString));
                            }
                        } else
                        {
                            movieModel.setBudget(0);
                        }

                        break;
                    }

                    case 6:
                    {
                        header = "profit=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setProfit(Long.parseLong(strippedString));
                            }
                        } else
                        {
                            movieModel.setProfit(Long.parseLong("0"));
                        }

                        break;
                    }

                    case 7:
                    {
                        header = "durata=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setDuration(Integer.parseInt(strippedString));
                            }
                        } else
                        {
                            movieModel.setDuration(0);
                        }
                        break;
                    }

                    case 8:
                    {
                        header = "limba=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setLanguage(strippedString);
                            }
                        } else
                        {
                            movieModel.setLanguage("-");
                        }

                        break;
                    }

                    case 9:
                    {
                        header = "paginaWeb=";
                        receivedString = columnsTokenizer.nextToken();
                        if (!receivedString.equals(header))
                        {
                            strippedString = receivedString.substring(header.length());
                            if (!strippedString.equals(""))
                            {
                                movieModel.setWebPage(strippedString);
                            }
                        } else
                        {
                            movieModel.setWebPage("-");
                        }
                        break;
                    }
                }
            }
            observableList.add(movieModel);
        }
        return observableList;
    }
}
