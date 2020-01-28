package wineStore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This is the controller for admin login and request management
 */
public class EmployeeController implements Initializable {

    /**
     * These are the values needed to connect to the DB
     */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/esercitazione4";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    Database database = new Database();

    /**
     * The button for satisfy the request made from the users
     */
    @FXML
    private Button requestButton;

    /**
     * The ComboBox tha allows the admin to choose the wine to manage
     */
    @FXML
    private ComboBox<String> wineSelector;

    /**
     * The requests table found in the DB is shown in the GUI via TableView
     */
    @FXML
    private TableView<ModelTable> requestTable;

    /**
     * First column of the TableView
     */
    @FXML
    private TableColumn<ModelTable, String> col_id;

    /**
     * Second column of the TableView
     */
    @FXML
    private TableColumn<ModelTable, String> col_name;

    /**
     * Third column if the TableView
     */
    @FXML
    private TableColumn<ModelTable, String> col_quantity;

    /**
     * This list TODO
     */
    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    /**
     * This list is used to set the name of the wine selected so that it can be sent to the DB
     */
    ObservableList<String> wineListSelector = FXCollections.observableArrayList("GATTINARA D.O.C.G. RISERVA - 2013",
            "NABUCCO MONTE DELLE VIGNE - 2013");

    public EmployeeController() throws SQLException {
    }

    /**
     * Since this controller implements Initializable this method is necessary
     *
     * @param url standard for initialize method
     * @param resourceBundle standard for initialize method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * I set the items in the selector with the data found in the ObservableList
         */
        wineSelector.setItems(wineListSelector);

        /**
         * When the GUI loads the TableView gets filled with the requests table's data,
         * it's done at execution time since the Controller is Ititializable.
         * It's a simple MySQL query, i use a ResultSet to fill the ObservableList.
         */
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM requests");
            while (rs.next()) {
                observableList.add(new ModelTable(rs.getString("id"),
                        rs.getString("name"), rs.getString("quantity")));
                /**
                 * The method setCellValueFactory is used to populate individual cells in the column
                 */
                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                /**
                 * I fill the table with the data i previously put in the ObservableList
                 */
                requestTable.setItems(observableList);
            }
        } catch (Exception e_loadTable) {
            e_loadTable.printStackTrace();
        }
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void fulfillRequests(ActionEvent actionEvent) {

        Window window = requestButton.getScene().getWindow();

        String wineToFullfill = wineSelector.getSelectionModel().getSelectedItem();
        System.out.println(wineToFullfill);

        if (wineSelector.getValue() == null){
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please select a wine");
            return;
        } else {
            boolean fullfillCheck = database.fulfillRequests(wineToFullfill);
            if (!fullfillCheck) {
                infoBox("Something went wrong", null, "Failed");
            } else {

                try {
                    ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM requests");
                    while (rs.next()) {
                        observableList.add(new ModelTable(rs.getString("id"),
                                rs.getString("name"), rs.getString("quantity")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                requestTable.setItems(observableList);

                showAlert(Alert.AlertType.CONFIRMATION, window, "Request made!", "Your request has been sent");
            }
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
