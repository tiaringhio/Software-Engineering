package wineStore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This is the controller for login and user registration
 */
public class WinesController implements Initializable {

    /**
     * These are the values needed to connect to the DB
     */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/esercitazione4";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    Database database = new Database();

    /**
     * The ComboBox tha allows the user to choose the wine
     */
    @FXML
    private  ComboBox<String> wineSelector;

    /**
     * The button for requesting more bottles
     */
    @FXML
    private Button requestWine;

    /**
     * The button to buy wine
     */
    @FXML
    private Button buyWine;

    /**
     * The button for logging the user out
     */
    @FXML
    private Button logoutButton;

    /**
     * TexField to choose ho much wine to buy
     */
    @FXML
    private TextField quantityWine;

    /**
     * Button to check how many bottles are available for each wine,
     * it works in tandem with the quantity
     */
    @FXML
    private Button checkAvailable;

    /**
     * This TexField will show ho many bottles are available,
     * it works in tandem with the availability button
     */
    @FXML
    private TextField availableWine;

    /**
     * Used to change scene back and forth
     */
    Stage stage = new Stage();
    Scene scene;

    /**
     * This list is used to set the name of the wine selected so that it can be sent to the DB
     */
    ObservableList<String> wineList = FXCollections.observableArrayList("GATTINARA D.O.C.G. RISERVA - 2013",
            "NABUCCO MONTE DELLE VIGNE - 2013");


    public WinesController() throws SQLException {
    }

    /**
     * Since this controller implements Initializable this method is necessary
     *
     * @param url standard for initialize method
     * @param resourceBundle standard for initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wineSelector.setItems(wineList);
    }

    /**
     * This method loads the available bottles for the selected wine.
     * When clicked the button will send to the DB the name of the wine selected along with the quantity,
     * The DB will respond with how many bottles are available.
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    private void loadQuantity(ActionEvent actionEvent) {

        /**
         * The windows object is used to show alerts to the user
         */
        Window window = checkAvailable.getScene().getWindow();

        /**
         * I save the wine selection in a variable to improve code readability
         */
        String wineSelected = wineSelector.getSelectionModel().getSelectedItem();

        /**
         *  Before calling the function i check that the TextFields aren't empty,
         *  if they are i send a message to the user letting him/her know what's missing.
         *
         *  The first thing checked is the wine selection
         */
        if (wineSelector.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please select a wine");
            return;
        }

        /**
         * I make a simple query to the DB to check how many bottles of the selected wine are available,
         * the value received from the DB is then shown in a Read-Only TextField in the GUI.
         * I chose to use PreparedStatements because the query are parametrized and thus faster and more secure
         */
        try {
            String loadQuantityQuery = "SELECT quantity FROM wines WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(loadQuantityQuery);
            preparedStatement.setString(1, wineSelected);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int quantityAvailable = resultSet.getInt("quantity");
                availableWine.setText(String.valueOf(quantityAvailable));
            }
        } catch (SQLException e_load) {
            Database.printSQLException(e_load);
        }
    }

    /**
     * This method lets the user request more bottles of the selected wine.
     * The function sends the data given by the user to the database class,
     * the data it's then sent to the DB and stored in the requests table.
     * The user has to select the quantity along with the wine.
     * The function is triggered when the user clicks the request button in the GUI.
     *
     * @param actionEvent
     */
    @FXML
    private void request(ActionEvent actionEvent) {

        /**
         * The windows object is used to show alerts to the user
         */
        Window window = requestWine.getScene().getWindow();

        /**
         *  Before calling the function i check that the TextFields aren't empty,
         *  if they are i send a message to the user letting him/her know what's missing.
         *
         *  The first thing checked is the wine selection
         */
        if (wineSelector.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please select a wine");
            return;
        }

        /**
         * Then i check the quantity
         */
        if (quantityWine.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a quantity");
            return;
        }

        /**
         * I assign tha values to some variables to improve code readability
         */
        String wineSelected = wineSelector.getSelectionModel().getSelectedItem();
        int quantitySelected = Integer.parseInt(quantityWine.getText());

        /**
         * Finally i send the data to the DB, the DB will respond with a true or a false,
         * i will use the answer to show a message to the user where i tell him/her what happened.
         */
        try {
            boolean requestResult = database.request(wineSelected, quantitySelected);
            if (!requestResult) {
                showAlert(Alert.AlertType.ERROR, window, "Failed", "Something went wrong");
                return;
            } else {
                showAlert(Alert.AlertType.CONFIRMATION, window, "Request made!", "Your request has been sent");
            }
        } catch (Exception e_request) {
            e_request.printStackTrace();
        }
    }

    /**
     * This method lets the user buy some wine.
     * THe function sends the data given by the user to the database class,
     * the data it's then checked with the DB through a MySQL query,
     * that function will change the quantity available in the wines table.
     * The function is triggered when the user clicks the buy button in the GUI.
     *
     * @param event The button gets clicked
     */
    @FXML
    void buy(ActionEvent event) {

        /**
         * The windows object is used to show alerts to the user
         */
        Window window = buyWine.getScene().getWindow();

        /**
         *  Before calling the function i check that the TextFields aren't empty,
         *  if they are i send a message to the user letting him/her know what's missing.
         *
         *  The first thing checked is the wine selection
         */
        if (wineSelector.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please select a wine");
            return;
        }

        /**
         * Then i check the quantity
         */
        if (quantityWine.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please enter a quantity");
            return;
        }

        /**
         * I assign tha values to some variables to improve code readability
         */
        String wineSelected = wineSelector.getSelectionModel().getSelectedItem();
        int quantitySelected = Integer.parseInt(quantityWine.getText());

        /**
         * Finally i send the data to the DB, the DB will respond with a true or a false,
         * i will use the answer to show a message to the user where i tell him/her what happened.
         */
        try {
            int quantityCheck = database.checkQuantity(wineSelected);
            if (quantityCheck >= Integer.parseInt(quantityWine.getText())) {
                boolean buyCheck = database.buy(wineSelected, quantitySelected);
                if (!buyCheck) {
                    infoBox("Something went wrong", null, "Failed");
                } else {
                    infoBox("Purchase successful", null, "Success");
                }
            } else {
                infoBox("There are only " + quantityCheck + " bottles available", null, "Failed");
            }
        } catch (NumberFormatException e_buy) {
            e_buy.printStackTrace();
        }
    }

    /**
     * At the click of the logout button i close thw wines form
     * and open the login/registration form
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    void logout(ActionEvent actionEvent) {
        try {
            Window window = logoutButton.getScene().getWindow();
            infoBox("See you next time", null, "Logout");
            Node source = (Node) actionEvent.getSource();
            stage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("/user_form.fxml")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
