package frontend.controllers;

import backend.controllers.MovieAppUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * @author Toma-Florin Ungureanu
 */
public class SignUpController extends Controller
{
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Text eventText;
    @FXML private Label signUpLabel;
    @FXML private Text generatedPassField;

    @FXML
    void goBackToLogin(ActionEvent actionEvent) throws IOException
    {
        nextScene(new LogInController(),"../fxmls/login.fxml", actionEvent, null);
    }

    @FXML
    void generatePassword() throws SQLException
    {
        MovieAppUserController movieAppUserCtl = new MovieAppUserController();
        String randomPass = movieAppUserCtl.generateRandomPass();
        passwordField.setText(randomPass);
        passwordField.setVisible(true);
        generatedPassField.setText("Don't tell anyone: " + randomPass);
    }

    public boolean signUp(ActionEvent actionEvent) throws SQLException, IOException
    {
        signUpLabel.setText("Sign Up");
        eventText.setText("");

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        MovieAppUserController movieAppUserCtl = new MovieAppUserController();
        String retVal = movieAppUserCtl.register(lastName,firstName,email,password);
        StringTokenizer stringTokenizer = new StringTokenizer(retVal, ".");
        String token = stringTokenizer.nextToken();
        switch (token)
        {
            case "Eroare: Unul sau mai multe campuri au fost lasate necompletate":
            {
                reset(actionEvent);
                eventText.setText("One or more fields not completed!");
                break;
            }

            case "Eroare: Email-ul este deja folosit de un alt cont":
            {
                reset(actionEvent);
                eventText.setText("Email already in use!");
                break;
            }

            case "Eroare: Parola este prea scurta":
            {
                eventText.setText("Password is too short(less then 6)");
                passwordField.setText("");
                break;
            }

            case "Eroare: Email invalid":
            {
                eventText.setText("Invalid email");
                emailField.setText("");
                break;
            }

            case "Succes: Inregistrare cu succes":
            {
                nextScene(new HomeController(), "../fxmls/home.fxml", actionEvent, emailField.getText());
                break;
            }
        }

        return true;
    }

    public void reset(ActionEvent actionEvent)
    {
        eventText.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        eventText.setText("Data reset");
        generatedPassField.setText("");
    }

}
