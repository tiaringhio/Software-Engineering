package wineStore;

import java.sql.*;

public class Database {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/esercitazione4";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String REGISTRATION_QUERY = "INSERT INTO users (name, surname, user, password) VALUES (?, ?, ?, ?)";
    private static final String LOGIN_QUERY = "SELECT * FROM users WHERE user = ? and password = ?";

    public boolean login(String userLog, String passwordLog) {
        // Establish connection
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            // Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY);
            preparedStatement.setString(1, userLog);
            preparedStatement.setString(2, passwordLog);

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

    public void register(String name, String surname, String userReg, String passwordReg) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTRATION_QUERY);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, userReg);
            preparedStatement.setString(4, passwordReg);

            preparedStatement.executeUpdate();
        } catch (SQLException e_reg) {
            printSQLException(e_reg);
        }
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
