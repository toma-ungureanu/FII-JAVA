package frontend.controllers;

import backend.database.DatabaseConnection;
import backend.object_oriented_model.MovieAppEmail;
import backend.object_oriented_model.MovieAppUser;
import backend.object_oriented_model.UserAndEmail;
import backend.storage.StoreData;
import frontend.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Toma-Florin Ungureanu
 */
public class MyProfileController extends Controller implements Initializable
{
    @FXML private Text lastNameField;
    @FXML private Text firstNameField;
    @FXML private Text emailField;
    @FXML private Text passwordField;
    @FXML private Text idField;
    @FXML private TableView<UserModel> usersTable;
    @FXML private TableColumn<UserModel, Integer> idColumn;
    @FXML private TableColumn<UserModel, String> lastNameColumn;
    @FXML private TableColumn<UserModel, String> firstNameColumn;
    @FXML private TableColumn<UserModel, String> emailColumn;
    @FXML private TableColumn<UserModel, String> passwordColumn;
    private String email;
    private Integer userId;

    @FXML
    public void goHome(ActionEvent actionEvent) throws IOException
    {
        nextScene(new HomeController(), "../fxmls/home.fxml", actionEvent, email);
    }

    @FXML
    public void executeStatements(ActionEvent actionEvent) throws SQLException
    {
        executeUnsafeStatement();
        executeSafeStatement();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    void setCredentials(String email)
    {
        this.email = email;
        StoreData storeData = new StoreData();
        String result = storeData.getAccountInfo(this.email);
        this.userId = storeData.parseAccountInfo(result).get(0).getId();
    }

    private void executeSafeStatement() throws SQLException
    {
        //getAccount info is the function that uses Callable statement
        StoreData storeData = new StoreData();
        String result = storeData.getAccountInfo(email);
        List<UserAndEmail> userEmailList = storeData.parseAccountInfo(result);
        MovieAppUser movieAppUser = (MovieAppUser) userEmailList.get(0);
        MovieAppEmail movieAppEmail = (MovieAppEmail) userEmailList.get(1);

        idField.setText(String.valueOf(movieAppUser.getId()));
        firstNameField.setText(movieAppUser.getLastName());
        lastNameField.setText(movieAppUser.getFirstName());
        passwordField.setText(movieAppUser.getPassword());
        emailField.setText(movieAppEmail.getEmail());
    }

    private void executeUnsafeStatement() throws SQLException
    {
        ObservableList<UserModel> userModels = FXCollections.observableArrayList();
        UserModel userModel;
        String sqlInj = " OR 1=1";
        Connection con = DatabaseConnection.getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM UTILIZATORI NATURAL JOIN EMAILS WHERE ID=" + this.userId + sqlInj);
        while(rs.next())
        {
            userModel = new UserModel();
            userModel.setId(rs.getInt("ID"));
            userModel.setEmail(rs.getString("EMAIL"));
            userModel.setLastName(rs.getString("PRENUME"));
            userModel.setFirstName(rs.getString("NUME"));
            userModel.setPassword(rs.getString("PAROLA"));
            userModels.addAll(userModel);
        }

        usersTable.setItems(userModels);
    }
}
