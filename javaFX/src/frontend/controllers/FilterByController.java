package frontend.controllers;

import backend.controllers.AppMovieController;
import backend.controllers.MovieAppRatingController;
import backend.object_oriented_model.AppMovie;
import backend.object_oriented_model.MovieAppUser;
import backend.object_oriented_model.UserAndEmail;
import backend.storage.StoreData;
import frontend.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Toma-Florin Ungureanu
 */
public class FilterByController extends Controller implements Initializable
{
    @FXML private TableView<MovieModel> tableView;
    @FXML private TableColumn<MovieModel, String> titleColumn;
    @FXML private TableColumn<MovieModel, String> genreColumn;
    @FXML private TableColumn<MovieModel, Date> releaseDateColumn;
    @FXML private TableColumn<MovieModel, Integer> budgetColumn;
    @FXML private TableColumn<MovieModel, Integer> durationColumn;
    @FXML private TableColumn<MovieModel, Long> profitColumn;
    @FXML private TableColumn<MovieModel, String> languageColumn;
    @FXML private TableColumn<MovieModel, String> webPageColumn;
    @FXML private TableColumn<MovieModel, Float> ratingColumn;
    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private DatePicker firstDatePicker;
    @FXML private DatePicker secondDatePicker;
    @FXML private ChoiceBox<Integer> durationBox;
    @FXML private Text filterLabel;
    @FXML private Text eventField;
    private String email;
    private Integer userId;
    private AppMovieController appMovieController = new AppMovieController();
    private TextInputDialog textInputDialog = new TextInputDialog("Rate it!");

    @FXML
    private void recommendMovies(ActionEvent actionEvent)
    {
        checkUserId();
        String result = appMovieController.getRecommendedMovieList(userId);
        tableView.setItems(parseMovieList(result));
    }

    @FXML
    private void getTop100(ActionEvent actionEvent)
    {
        tableView.setItems(parseMovieList(appMovieController.getTop30()));
    }

    @FXML
    private void goBackHome(ActionEvent actionEvent) throws IOException
    {
        nextScene(new HomeController(),"../fxmls/home.fxml", actionEvent, email);
    }

    @FXML
    private boolean filterButton(ActionEvent actionEvent) throws SQLException, java.text.ParseException
    {
        tableView.getItems().clear();
        eventField.setText("");

        String title = titleField.getText();
        String genre = genreField.getText();
        LocalDate firstDate = firstDatePicker.getValue();
        LocalDate secondDate = secondDatePicker.getValue();
        Integer duration = durationBox.getValue();

        java.sql.Date firstSent;
        java.sql.Date secondSent;

        if (firstDate == null)
        {
            firstSent = null;
        } else
        {
            firstSent = java.sql.Date.valueOf(firstDate);
        }

        if (secondDate == null)
        {
            secondSent = null;
        } else
        {
            secondSent = java.sql.Date.valueOf(secondDate);
        }

        String result = appMovieController.filterBy(title, genre, firstSent, secondSent, duration);

        if (result == null)
        {
            eventField.setText("No movies found!");
            return false;
        }

        switch (result)
        {
            case "Eroare: Toate campurile sunt lasate necompletate.":
            {
                eventField.setText("At least one field required!");
                return false;
            }

            case "Eroare: N-au fost trecute ambele date.":
            {
                eventField.setText("Both dates are required!");
                return false;
            }
        }

        tableView.setItems(parseMovieList(result));
        titleField.clear();
        genreField.clear();
        firstDatePicker.getEditor().clear();
        secondDatePicker.getEditor().clear();
        return true;
    }

    @FXML
    public void rateSpecificMovie(ActionEvent actionEvent)
    {
        MovieModel movieModel = tableView.getSelectionModel().getSelectedItem();
        AppMovie appMovie = new AppMovie(movieModel);

        textInputDialog.setTitle("Rate it!");
        textInputDialog.setHeaderText("Rate " + appMovie.getTitle() + " from 1 to 10: ");
        textInputDialog.setContentText("Rating: ");

        Optional<String> result = textInputDialog.showAndWait();

        result.ifPresent(this::parseResult);
    }

    private void parseResult(String result)
    {
        try
        {
            int movieRating = Integer.parseInt(result);
            if(movieRating < 1 || movieRating > 10)
            {
                throw  new NumberFormatException("Value needs to be an integer from 1 to 10!");
            }

            MovieAppRatingController movieAppRatingController = new MovieAppRatingController();
            int movieId = movieAppRatingController.getMovieId(tableView.getSelectionModel().getSelectedItem().getTitle());
            StoreData storeData = new StoreData();
            String accountInfo = storeData.getAccountInfo(email);
            List<UserAndEmail> userInfo = storeData.parseAccountInfo(accountInfo);
            MovieAppUser movieAppUser = (MovieAppUser) userInfo.get(0);

            movieAppRatingController.addRating(movieAppUser.getId(), movieId, movieRating);

        } catch (NumberFormatException | SQLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //choice box
        durationBox.getItems().addAll(90, 120, 150, 180, 300, 360);

        //tableview
        titleColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("genre"));
        webPageColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("webPage"));
        languageColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("language"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, Long>("profit"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, Date>("releaseDate"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, Integer>("budget"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, Integer>("duration"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<MovieModel, Float>("rating"));
    }

    public String getCredentials()
    {
        return email;
    }

    public void setCredentials(String email)
    {
        this.email = email;
    }

    public void checkUserId()
    {
        if (userId == null)
        {
            StoreData storeData = new StoreData();
            String accountInfo = storeData.getAccountInfo(email);
            List<UserAndEmail> userInfo = storeData.parseAccountInfo(accountInfo);
            userId = userInfo.get(0).getId();
        }
    }

}
