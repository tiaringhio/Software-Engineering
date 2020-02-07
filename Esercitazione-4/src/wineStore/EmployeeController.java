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
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This is the controller for admin login and request management
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
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
     * The button to show the request table
     */
    @FXML
    private Button requestScene;

    /**
     * The button to show the purchases table
     */
    @FXML
    private Button purchasesScene;

    /**
     * The button to show the wines table
     */
    @FXML
    private Button winesScene;

    /**
     * The button for logging the admin out
     */
    @FXML
    private Button logoutButton;

    /**
     * This Pane will contain the request table
     */
    @FXML
    private Pane requestPane;

    /**
     * This Pane will contain the purchases table
     */
    @FXML
    private Pane purchasesPane;

    /**
     * This Pane will contain the wines table
     */
    @FXML
    private Pane winesPane;

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
     * The requests table found in the DB is shown in the GUI via TableView.
     * Every column of the request TableView
     */
    @FXML
    private TableView<ModelTable> requestTable;

    @FXML
    private TableColumn<ModelTable, String> col_id;

    @FXML
    private TableColumn<ModelTable, String> col_name;

    @FXML
    private TableColumn<ModelTable, String> col_quantity;

    /**
     * The purchases table found in the DB is shown in the GUI via TableView.
     * Every column of the purchases TableView
     */
    @FXML
    private TableView<ModelTable> purchasesTable;

    @FXML
    private TableColumn<ModelTable, String> col_purch_id;

    @FXML
    private TableColumn<ModelTable, String> col_purch_name;

    @FXML
    private TableColumn<ModelTable, String> col_purch_quantity;

    /**
     * The wines table found in the DB is shown in the GUI via TableView.
     * Every column of the wine TableView.
     */
    @FXML
    private TableView<ModelTable> winesTable;

    @FXML
    private TableColumn<ModelTable, String> col_wines_id;

    @FXML
    private TableColumn<ModelTable, String> col_wines_name;

    @FXML
    private TableColumn<ModelTable, String> col_wines_quantity;

    /**
     * Used to change scene back and forth
     */
    Stage stage = new Stage();
    Scene scene;

    /**
     * This lists are used to load the data from the respective table,
     * they will be then assigned to the correct table to be displayed
     */
    ObservableList<ModelTable> requestsObservableList = FXCollections.observableArrayList();
    ObservableList<ModelTable> purchasesObservableList = FXCollections.observableArrayList();
    ObservableList<ModelTable> winesObservableList = FXCollections.observableArrayList();

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
                requestsObservableList.add(new ModelTable(rs.getString("id"),
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
                requestTable.setItems(requestsObservableList);
            }
        } catch (Exception e_loadTable) {
            e_loadTable.printStackTrace();
        }
    }

    /**
     * This method shows the request Pane and loads the proper data in the TableView
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    public void showRequestsScene(ActionEvent actionEvent) {
        Window window = requestScene.getScene().getWindow();
        purchasesPane.setVisible(false);
        winesPane.setVisible(false);
        purchasesObservableList.clear();
        winesObservableList.clear();
        requestsObservableList.clear();
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
            /**
             * If the table is empty i show a message to the admin
             */
            if (!rs.next()) {
                infoBox("No more requests", null, "Job done");
            }
            else {
                do {
                    requestsObservableList.add(new ModelTable(rs.getString("id"),
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
                    requestTable.setItems(requestsObservableList);
                    requestPane.setVisible(true);
                } while (rs.next());
            }
        } catch (Exception e_loadTable) {
            e_loadTable.printStackTrace();
        }
    }

    /**
     * This method shows the purchases Pane and loads the proper data in the TableView
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    private void showPurchaseScene(ActionEvent actionEvent) {
        Window window = purchasesScene.getScene().getWindow();
        requestPane.setVisible(false);
        winesPane.setVisible(false);
        requestsObservableList.clear();
        winesObservableList.clear();
        purchasesObservableList.clear();

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
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM purchases");
            while (rs.next()) {
                purchasesObservableList.add(new ModelTable(rs.getString("id"),
                        rs.getString("name"), rs.getString("quantity")));
                /**
                 * The method setCellValueFactory is used to populate individual cells in the column
                 */
                col_purch_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_purch_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_purch_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                /**
                 * I fill the table with the data i previously put in the ObservableList
                 */
                purchasesTable.setItems(purchasesObservableList);
                purchasesPane.setVisible(true);
            }
        } catch (Exception e_loadTable) {
            e_loadTable.printStackTrace();
        }
    }

    /**
     * This method shows the wines Pane and loads the proper data in the TableView
     *
     * @param actionEvent The button gets clicked
     */
    @FXML
    public void showWinesScene(ActionEvent actionEvent) {
        Window window = winesScene.getScene().getWindow();
        purchasesPane.setVisible(false);
        requestPane.setVisible(false);
        requestsObservableList.clear();
        purchasesObservableList.clear();
        winesObservableList.clear();

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
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM wines");
            while (rs.next()) {
                winesObservableList.add(new ModelTable(rs.getString("id"),
                        rs.getString("name"), rs.getString("quantity")));
                /**
                 * The method setCellValueFactory is used to populate individual cells in the column
                 */
                col_wines_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_wines_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_wines_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                /**
                 * I fill the table with the data i previously put in the ObservableList
                 */
                winesTable.setItems(winesObservableList);
                winesPane.setVisible(true);
            }
        } catch (Exception e_loadTable) {
            e_loadTable.printStackTrace();
        }
    }

    /**
     * This method allows the admin to fulfill the requests from the users, as displayed
     * in the table.
     * It takes as input the wine to fulfill, which is selected through a ComboB
     * ox
     * @param actionEvent The button gets clicked
     */
    @FXML
    public void fulfillRequests(ActionEvent actionEvent) {
        Window window = requestButton.getScene().getWindow();

        /**
         * I assign the selection to a String variable, to make the code more readable
         */
        String wineToFulfill = wineSelector.getSelectionModel().getSelectedItem();
        System.out.println(wineToFulfill);

        /**
         * I check if the wine has been selected, if it hasn't i show an error message to the admin
         */
        if (wineSelector.getValue() == null){
            showAlert(Alert.AlertType.ERROR, window, "Form error!", "Please select a wine");
            /**
             * If the wine has been selected i send the previously defined variable to the DB
             * trough the proper function
             */
        } else {
            boolean fulfillCheck = database.fulfillRequests(wineToFulfill);
            /**
             * If something went wrong i notify the user
             */
            if (!fulfillCheck) {
                infoBox("Something went wrong", null, "Failed");
                /**
                 * Otherwise i update the table with the new data.
                 * When the GUI loads the TableView gets filled with the requests table's data,
                 * it's done at execution time since the Controller is Ititializable.
                 * It's a simple MySQL query, i use a ResultSet to fill the ObservableList.
                 */
            } else {
                try {
                    ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM requests");
                    while (rs.next()) {
                        requestsObservableList.add(new ModelTable(rs.getString("id"),
                                rs.getString("name"), rs.getString("quantity")));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /**
                 * The method setCellValueFactory is used to populate individual cells in the column
                 */
                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                /**
                 * I fill the table with the data i previously put in the ObservableList
                 */
                requestTable.setItems(requestsObservableList);

                /**
                 * Finally i show a confirmation to the admin
                 */
                showAlert(Alert.AlertType.CONFIRMATION, window, "Request fulfilled!", "The request has been fulfilled");
            }
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
