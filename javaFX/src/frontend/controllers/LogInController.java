package frontend.controllers;

import backend.controllers.MovieAppUserController;
import backend.object_oriented_model.MovieAppEmail;
import backend.object_oriented_model.MovieAppUser;
import backend.object_oriented_model.UserAndEmail;
import backend.storage.StoreData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LogInController extends Controller implements Initializable
{
    @FXML private Text stslabel;
    @FXML private Button signUpButton;
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Button logInButton;
    @FXML private Text eventArea;
    @FXML private CheckBox rememberCred;

    @FXML
    public void signUp(ActionEvent event) throws IOException
    {
        nextScene(new SignUpController(),"../fxmls/signup.fxml", event, null);
    }

    @FXML
    public boolean logIn(ActionEvent event) throws IOException,
            ParserConfigurationException,
            TransformerException
    {
        eventArea.setText("");
        //select from backend.database with
        String user = userNameField.getText();
        String password = passwordField.getText();

        MovieAppUserController movieUserCtrl = new MovieAppUserController();

        String retVal;
        try
        {
            retVal = movieUserCtrl.logIn(user, password);
        }
        catch (SQLException excp)
        {
            eventArea.setText("Password is incorrect");
            stslabel.setText("Login Failed");
            userNameField.clear();
            passwordField.clear();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(excp.getMessage().substring(0,26));
            alert.show();
            return false;
        }

        switch (retVal)
        {
            case "Logare cu succes.":
            {
                if (rememberCred.isSelected())
                {
                    StoreData storeData = new StoreData();
                    String userInfo = storeData.getAccountInfo(userNameField.getText());
                    List<UserAndEmail> parsedUserInfo = storeData.parseAccountInfo(userInfo);
                    storeData.storeAccountData(parsedUserInfo);
                }

                else
                {
                    File file = new File(StoreData.savePath);

                    if(file.delete())
                    {
                        System.out.println("File deleted successfully");
                    }
                    else
                    {
                        System.out.println("Failed to delete the file");
                    }
                }
                nextScene(new HomeController(),"../fxmls/home.fxml", event, userNameField.getText());
                break;
            }

            case "Utilizatorul nu exista.":
            {
                eventArea.setText("Account not found");
                stslabel.setText("Login Failed");
                userNameField.clear();
                passwordField.clear();
                break;
            }
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        File f = new File(StoreData.savePath);
        if(f.exists() && !f.isDirectory())
        {
            StoreData storeData = new StoreData();
            List<UserAndEmail> accountInfo = storeData.readStoredData();
            MovieAppUser movieAppUser = (MovieAppUser) accountInfo.get(0);
            MovieAppEmail movieAppEmail = (MovieAppEmail) accountInfo.get(1);

            userNameField.setText(movieAppEmail.getEmail());
            passwordField.setText(movieAppUser.getPassword());
        }
    }

}
