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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Toma-Florin Ungureanu
 */
public class HomeController extends Controller implements Initializable
{
    @FXML private Button filterByButton;
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
    private String userEmail;
    private Integer selectedMovieId;
    private Integer userId;
    private MovieAppRatingController movieAppRatingController = new MovieAppRatingController();
    private TextInputDialog textInputDialog = new TextInputDialog("Modify rating");

    @FXML
    public void goToProfile(ActionEvent actionEvent) throws IOException
    {
        nextScene(new MyProfileController(), "../fxmls/myProfile.fxml", actionEvent, this.userEmail);
    }

    @FXML
    private void deleteRating(ActionEvent actionEvent) throws SQLException
    {
        checkPreConditions();
        movieAppRatingController.deleteRating(userId, selectedMovieId);
        tableView.refresh();
        setTableView();
    }

    @FXML
    private void modifyRating(ActionEvent actionEvent)
    {
        MovieModel movieModel = tableView.getSelectionModel().getSelectedItem();
        AppMovie appMovie = new AppMovie(movieModel);
        textInputDialog.setTitle("Modify rating");
        textInputDialog.setHeaderText("Modify " + appMovie.getTitle() + " rating value: ");
        textInputDialog.setContentText("Rating: ");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(this::parseResult);
        setTableView();
    }

    @FXML
    void filterBy(ActionEvent event) throws IOException
    {
        nextScene(new FilterByController(), "../fxmls/filterBy.fxml", event, getCredentials());
    }

    @FXML
    void logout(ActionEvent event) throws IOException
    {
        nextScene(new LogInController(), "../fxmls/login.fxml", event, null);
    }

    @FXML
    void recommendMovies(ActionEvent event)
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
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

    private void parseResult(String result)
    {
        try
        {
            int movieRating = Integer.parseInt(result);
            if (movieRating < 1 || movieRating > 10)
            {
                throw new NumberFormatException("Value needs to be an integer from 1 to 10!");
            }

            checkPreConditions();
            movieAppRatingController.modifyRating(userId, selectedMovieId, movieRating);

        } catch (NumberFormatException | SQLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    private void checkPreConditions() throws SQLException
    {
        if (userId == null)
        {
            StoreData storeData = new StoreData();
            String accountInfo = storeData.getAccountInfo(userEmail);
            List<UserAndEmail> userInfo = storeData.parseAccountInfo(accountInfo);
            userId = userInfo.get(0).getId();
        }

        selectedMovieId = movieAppRatingController
                .getMovieId(tableView.getSelectionModel().getSelectedItem()
                .getTitle());
    }

    void setCredentials(String email)
    {
        this.userEmail = email;
    }

    private String getCredentials()
    {
        return this.userEmail;
    }

    void setTableView()
    {
        AppMovieController appMovieController = new AppMovieController();
        StoreData storeData = new StoreData();
        MovieAppUser movieAppUser =
                (MovieAppUser) storeData.parseAccountInfo(storeData.getAccountInfo(userEmail)).get(0);
        String userMovieList = appMovieController.getUserMovieList(String.valueOf(movieAppUser.getId()));
        if(userMovieList != null)
        {
            tableView.setItems(parseMovieList(userMovieList));
        }
    }

}
