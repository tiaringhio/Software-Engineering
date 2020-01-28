package wineStore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This is the controller for login and user registration
 */
public class UserController {

    /**
     * The DB is initialized to allow me to run some methods locally without needing the DB class
     */
    Database database = new Database();

    /**
     * name TextField
     */
    @FXML
    private TextField nameRegField;

    /**
     * surname TextField
     */
    @FXML
    private TextField surnameRegField;

    /**
     * username TextField
     */
    @FXML
    private TextField usernameRegField;

    /**
     * password PasswordField
     */
    @FXML
    private PasswordField passwordRegField;

    /**
     * confirm password PasswordField
     */
    @FXML
    private PasswordField confirmRegField;

    /**
     * The register button triggers the register function
     */
    @FXML
    private Button registerButton;

    /**
     * username for login TextField
     */
    @FXML
    private TextField usernameLogField;

    /**
     * password for login PasswordField
     */
    @FXML
    private PasswordField passwordLogField;

    /**
     * this button triggers the login for the admins
     */
    @FXML
    private Button adminLoginButton;

    /**
     * this button triggers the login for the standard users
     */
    @FXML
    private Button loginButton;

    /**
     * This method lets the user register.
     * The function sends the data given by the user and sends them to the database class
     * they're then added to the DB through a MySQL query.
     * The function is triggered when the users clicks the register button in the GUI.
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    public void register(ActionEvent actionEvent) {

        /**
         * The windows object is used to show alerts to the user
         */
        Window window = registerButton.getScene().getWindow();

        /**
         *  Before calling the function i check that all the TextFields aren't empty,
         *  if they are i send a message to the user letting him/her know what's missing
         *
         *  First TextField check is the name
         */
        if (nameRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a name");
            return;
        }
        /**
         * Then i check the surname
         */
        if (surnameRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a surname");
            return;
        }
        /**
         * Then i check the username
         */
        if (usernameRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a username");
            return;
        }
        /**
         * Then i check the password
         */
        if (passwordRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a password");
            return;
        }
        /**
         * Last i check that the passwords match
         */
        if (!passwordRegField.getText().equals(confirmRegField.getText())) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "The passwords don't match");
            return;
        }

        /**
         * I assign tha values to some variables to make the function cleaner
         */
        String name = nameRegField.getText();
        String surname = surnameRegField.getText();
        String user = usernameRegField.getText();
        String password = passwordRegField.getText();

        /**
         * I send the values to the DB,
         * The DB will respond with a true or a false, i will use those values to inform the user about what happened
         */
        try {
            boolean registrationCheck = database.register(name, surname, user, password);
            if (!registrationCheck) {
                showAlert(Alert.AlertType.ERROR, window, "Something went wrong",
                        "Try again" + nameRegField.getText());
            } else {
                showAlert(Alert.AlertType.CONFIRMATION, window, "Registration Successful!",
                        "Welcome " + nameRegField.getText());
            }
        } catch (Exception e_registration) {
            e_registration.printStackTrace();
        }
    }

    /**
     * This method lets the user login.
     * The function sends the data given by the user and sends them to the database class
     * they're then checked with the DB through a MySQL query.
     * The function is triggered when the user clicks the login button in the GUI.
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    public void login(ActionEvent actionEvent) {

        /**
         * The windows object is used to show alerts to the user
         */
        Window window = loginButton.getScene().getWindow();

        /**
         *  Before calling the function i check that all the TextFields aren't empty,
         *  if they are i send a message to the user letting him/her know what's missing
         *
         *  First TextField check is the username
         */
        if (usernameLogField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a username");
            return;
        }

        /**
         * Then i check the password
         */
        if (passwordLogField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a password");
            return;
        }

        /**
         * I assign tha values to some variables to make the function cleaner
         */
        String userLog = usernameLogField.getText();
        String passwordLog = passwordLogField.getText();

        /**
         * I send the values to the DB,
         * The DB will respond with a true or a false, i will use those values to inform the user about what happened
         */
        try {
            boolean loginCheck = database.login(userLog, passwordLog);
            if (!loginCheck) {
                infoBox("Please enter the correct user and password", null, "Failed");
            } else {
                infoBox("Login successful", null, "Welcome back");
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/wines_form.fxml"));
                stage.setTitle("Welcome");
                stage.setScene(new Scene(root, 800, 500));
                stage.show();
            }
        } catch (IOException e_login) {
            e_login.printStackTrace();
        }
    }

    /**
     * This method lets the admin login.
     * The function sends the data given by the admin to the database class,
     * the data it's then checked with the DB through a MySQL query.
     * The function is triggered when the admin clicks the login button in the GUI.
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    public void adminLogin(ActionEvent actionEvent) {

        /**
         * The windows object is used to show alerts to the user
         */
        Window window = adminLoginButton.getScene().getWindow();

        /**
         *  Before calling the function i check that the TextFields aren't empty,
         *  if they are i send a message to the admin letting him/her know what's missing.
         *
         *  The first TextField checked is the username
         */
        if (usernameLogField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a username");
            return;
        }

        /**
         * Then i check the password
         */
        if (passwordLogField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a password");
            return;
        }

        /**
         * I assign tha values to some variables to make the function cleaner
         */
        String user = usernameLogField.getText();
        String password = passwordLogField.getText();

        /**
         * I send the values to the DB,
         * The DB will respond with a true or a false, i will use those values to inform the user about what happened
         */
        try {
            boolean loginCheck = database.adminLogin(user, password);
            if (!loginCheck) {
                infoBox("Please enter the correct user and password", null, "Failed");
            } else {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/employee_form.fxml"));
                stage.setTitle("Welcome");
                stage.setScene(new Scene(root, 800, 500));
                stage.show();
            }
        } catch (IOException e_adminLogin) {
            e_adminLogin.printStackTrace();
        }
    }

    /**
     * This method allows me to inform the user about what happens in the program.
     * It's used throughout the app by calling it and setting the proper parameters
     *
     * @param infoMessage The message displayed
     * @param headerText The header displayed
     * @param title The title displayed
     */
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    /**
     * This method contains every parameter useful to send alerts to the user.
     * It's used throughout the app by calling it and setting the proper parameters
     *
     * @param alertType The type of alert
     * @param window The window used
     * @param title The title displayed
     * @param message The message displayed
     */
    private static void showAlert(Alert.AlertType alertType, javafx.stage.Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.show();
    }
}
