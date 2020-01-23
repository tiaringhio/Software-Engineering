package wineStore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;

public class UserController {
    Database database = new Database();

    @FXML
    private TextField nameRegField;

    @FXML
    private TextField surnameRegField;

    @FXML
    private TextField usernameRegField;

    @FXML
    private PasswordField passwordRegField;

    @FXML
    private PasswordField confirmRegField;

    @FXML
    private Button registerButton;

    // Login
    @FXML
    private TextField usernameLogField;

    @FXML
    private PasswordField passwordLogField;

    @FXML
    private Button loginButton;

    @FXML
    public void register(ActionEvent actionEvent) throws SQLException {

        Window window = registerButton.getScene().getWindow();

        System.out.println(usernameRegField.getText());
        System.out.println(passwordRegField.getText());

        if (nameRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a name");
            return;
        }

        if (surnameRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a surname");
            return;
        }

        if (usernameRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a username");
            return;
        }

        if (passwordRegField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a password");
            return;
        }

        if (!passwordRegField.getText().equals(confirmRegField.getText())) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "The passwords don't match");
            return;
        }

        String name = nameRegField.getText();
        String surname = surnameRegField.getText();
        String userReg = usernameRegField.getText();
        String passwordReg = passwordRegField.getText();

        database.register(name, surname, userReg, passwordReg);

        showAlert(Alert.AlertType.CONFIRMATION, window, "Registration Successful!",
                "Welcome " + nameRegField.getText());
    }

    @FXML
    public void login(ActionEvent actionEvent) throws SQLException {

        Window window = loginButton.getScene().getWindow();

        if (usernameLogField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a username");
            return;
        }

        if (passwordLogField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a password");
            return;
        }

        String userLog = usernameLogField.getText();
        String passwordLog = passwordLogField.getText();

        boolean loginCheck = database.login(userLog, passwordLog);

        if (!loginCheck) {
            infoBox("Please enter the correct user and password", null, "Failed");
        } else {
            infoBox("Login successful", null, "Success");
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, javafx.stage.Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.show();
    }
}
