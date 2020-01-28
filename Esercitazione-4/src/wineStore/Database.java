package wineStore;

import java.sql.*;

public class Database {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/esercitazione4";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String REGISTRATION_QUERY = "INSERT INTO users (name, surname, user, password) VALUES (?, ?, ?, ?)";
    private static final String LOGIN_QUERY = "SELECT * FROM users WHERE user = ? and password = ?";
    private static final String ADMIN_LOGIN_QUERY = "SELECT * FROM admins WHERE user = ? and password = ?";
    private static final String CHECK_QUERY = "SELECT quantity FROM wines WHERE name = ?";
    private static final String BUY_QUERY = "UPDATE wines SET quantity = quantity - ? WHERE name = ?";
    private static final String ADD_TO_PURCHASES_QUERY =  "INSERT INTO purchases (wine_name, quantity) VALUES (?, ?)";
    private static final String REQUEST_QUERY = "INSERT INTO requests (name, quantity) VALUES (?, ?)";
    private static final String FULLFILL_REQUEST_QUERY = "UPDATE wines AS w\n" +
            "SET w.quantity = w.quantity + (SELECT SUM(r.quantity) FROM requests AS r\n" +
            "WHERE r.name = ?\n" +
            "GROUP BY r.name)\n" +
            "WHERE w.name = ?";
    private static final String DELETE_REQUESTS = "DELETE FROM requests WHERE name = ?";

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

    public boolean login(String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e_log) {
            printSQLException(e_log);
        }
        return false;
    }

    public boolean adminLogin(String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_LOGIN_QUERY);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e_log) {
            printSQLException(e_log);
        }
        return false;
    }

    public int checkQuantity (String name) {
        int checkQuantity = 0;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_QUERY);
            preparedStatement.setString(1, name);

            System.out.println(preparedStatement);

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

    public boolean buy(String name, int quantity) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement buyStatement = connection.prepareStatement(BUY_QUERY);
            buyStatement.setString(2, name);
            buyStatement.setInt(1, quantity);

            PreparedStatement purchaseStatement = connection.prepareStatement(ADD_TO_PURCHASES_QUERY);
            purchaseStatement.setString(1, name);
            purchaseStatement.setInt(2, quantity);

            System.out.println(buyStatement);
            System.out.println(purchaseStatement);

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

    public boolean fulfillRequests(String name) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement fullfillStatement = connection.prepareStatement(FULLFILL_REQUEST_QUERY);
            fullfillStatement.setString(1, name);
            fullfillStatement.setString(2, name);
            int resultSet = fullfillStatement.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_REQUESTS);
            deleteStatement.setString(1, name);
            int resultSet1 = deleteStatement.executeUpdate();

            if (resultSet > 0 && resultSet1 > 0) {
                return true;
            }
        } catch (SQLException e_full) {
            printSQLException(e_full);
        }
        return false;
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
