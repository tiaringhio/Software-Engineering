package wineStore;

import java.sql.*;

/**
 * This class talks directly to the database trough MySQL syntax, using JDBC
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
public class Database {
    /**
     * These are the values needed to connect to the DB
     */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/esercitazione4";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    /**
     * These are the queries i use to interrogate and update the DB
     */
    private static final String REGISTRATION_QUERY = "INSERT INTO users (name, surname, user, password) VALUES (?, ?, ?, ?)";
    private static final String LOGIN_QUERY = "SELECT * FROM users WHERE user = ? and password = ?";
    private static final String ADMIN_LOGIN_QUERY = "SELECT * FROM admins WHERE user = ? and password = ?";
    private static final String CHECK_QUERY = "SELECT quantity FROM wines WHERE name = ?";
    private static final String BUY_QUERY = "UPDATE wines SET quantity = quantity - ? WHERE name = ?";
    private static final String ADD_TO_PURCHASES_QUERY =  "INSERT INTO purchases (name, quantity) VALUES (?, ?)";
    private static final String REQUEST_QUERY = "INSERT INTO requests (name, quantity) VALUES (?, ?)";
    private static final String FULLFILL_REQUEST_QUERY = "UPDATE wines AS w\n" +
            "SET w.quantity = w.quantity + (SELECT SUM(r.quantity) FROM requests AS r\n" +
            "WHERE r.name = ?\n" +
            "GROUP BY r.name)\n" +
            "WHERE w.name = ?";
    private static final String DELETE_REQUESTS = "DELETE FROM requests WHERE name = ?";

    /**
     * This method gets data from the UserController and does a query to insert the data in the DB,
     * then it returns true or false so that the method in the UserController can notify the user
     * about what happened
     *
     * @param name user's registration name
     * @param surname user's registration surname
     * @param userReg user's registration username
     * @param passwordReg user's registration password
     * @return true or false
     */
    public boolean register(String name, String surname, String userReg, String passwordReg) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTRATION_QUERY);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, userReg);
            preparedStatement.setString(4, passwordReg);

            int registrationResult = preparedStatement.executeUpdate();

            if (registrationResult > 0) {
                return true;
            }
        } catch (SQLException e_reg) {
            printSQLException(e_reg);
        }
        return false;
    }

    /**
     * This method gets data from the UserController and does a query to check if the data corresponds to the data
     * in the DB, then it returns true or false so that the method in the UserController can notify the user
     * about what happened
     *
     * @param user user's username
     * @param password user's password
     * @return true or false
     */
    public boolean login(String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e_log) {
            printSQLException(e_log);
        }
        return false;
    }

    /**
     * This method gets data from the UserController and does a query to check if the data corresponds to the data
     * in the DB, then it returns true or false so that the method in the UserController can notify the admin
     * about what happened
     *
     * @param user admin's username
     * @param password admin's password
     * @return true or false
     */
    public boolean adminLogin(String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_LOGIN_QUERY);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e_log) {
            printSQLException(e_log);
        }
        return false;
    }

    /**
     * This method gets a wine name from the WinesController and performs a simple query to check
     * how many bottles are available
     *
     * @param name the wine to check
     * @return the quantity found
     */
    public int checkQuantity (String name) {
        int checkQuantity = 0;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_QUERY);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int quantityAvailable =  resultSet.getInt("quantity");
                checkQuantity = quantityAvailable;
            }

        } catch (SQLException e_check) {
            printSQLException(e_check);
        }
        return checkQuantity;
    }

    /**
     * This method gets a wine's name and quantity from the WinesController, performs a query to modify the
     * quantity found in the respective wine in the wines table, then performs another query to add the purchase
     * to the purchases table
     *
     * @param name wine's name
     * @param quantity wine's quantity
     * @return true or false
     */
    public boolean buy(String name, int quantity) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement buyStatement = connection.prepareStatement(BUY_QUERY);
            buyStatement.setString(2, name);
            buyStatement.setInt(1, quantity);

            PreparedStatement purchaseStatement = connection.prepareStatement(ADD_TO_PURCHASES_QUERY);
            purchaseStatement.setString(1, name);
            purchaseStatement.setInt(2, quantity);

            int buyResult = buyStatement.executeUpdate();
            int purchaseResult = purchaseStatement.executeUpdate();

            if (buyResult > 0 && purchaseResult > 0) {
                return true;
            }
        } catch (SQLException e_buy) {
            printSQLException(e_buy);
        }
        return false;
    }

    /**
     * This method gets a wine's name and quantity from the WinesController, performs a query to add an entry to
     * the requests table with the data obtained from input.
     *
     * @param name wine's name
     * @param quantity wine's quantity
     * @return true or false
     */
    public boolean request(String name, int quantity) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(REQUEST_QUERY);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, quantity);

            int requestResult = preparedStatement.executeUpdate();

            if (requestResult > 0) {
                return true;
            }
        }
        catch (SQLException e_req) {
            printSQLException(e_req);
        }
        return false;
    }

    /**
     * This method gets a wine's name from the EmployeeController, performs a query to modify the quantity of the
     * selected wine in the wines table to add the quantity obtained from input, then performs another query to remove
     * the entry from the requests table
     *
     * @param name wine's name
     * @return true or false
     */
    public boolean fulfillRequests(String name) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement fullfillStatement = connection.prepareStatement(FULLFILL_REQUEST_QUERY);
            fullfillStatement.setString(1, name);
            fullfillStatement.setString(2, name);

            int fullfillResult = fullfillStatement.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_REQUESTS);
            deleteStatement.setString(1, name);

            int deleteResult = deleteStatement.executeUpdate();

            if (fullfillResult > 0 && deleteResult > 0) {
                return true;
            }
        } catch (SQLException e_full) {
            printSQLException(e_full);
        }
        return false;
    }

    /**
     * This method allows the coder to better understand the exceptions thrown
     *
     * @param exception exception to manage
     */
    public static void printSQLException(SQLException exception) {
        for (Throwable e: exception) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = exception.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
