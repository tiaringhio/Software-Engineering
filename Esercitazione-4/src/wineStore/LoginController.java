package wineStore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private javafx.scene.control.Button submitButton;

    @FXML
    public void login(ActionEvent actionEvent) throws SQLException {

        Window window = submitButton.getScene().getWindow();

        System.out.println(userField.getText());
        System.out.println(passwordField.getText());

        if (userField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a username");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a password");
            return;
        }

        String user = userField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(user, password);

        if (!flag) {
            infoBox("Please enter the correct user and password", null, "Failed");
        } else {
            infoBox("Login succesful", null, "Success");
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
