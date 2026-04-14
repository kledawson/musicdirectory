package net.dbproj.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.dbproj.login.bean.LoginBean;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employees?useSSL=false", "root", "9576");
                
             // Create a PreparedStatement to query the login table
             PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?")) {

            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);

            // Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Check if user exists and retrieve role
            if (rs.next()) {
                // Set username and role in the LoginBean
                loginBean.setUsername(rs.getString("username"));
                loginBean.setRole(rs.getString("role"));  // Set the role
                status = true;  // User found, valid credentials
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }
    public String getUserRole(String username) throws ClassNotFoundException {
        String role = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/employees?useSSL=false", "root", "9576");

            PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT role FROM login WHERE username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                role = rs.getString("role"); // Retrieve the role from the database
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return role;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
