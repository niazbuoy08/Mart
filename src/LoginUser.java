import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUser {

    /**
     * Attempts to log in a user with the provided username and password.
     * @param username the user's username
     * @param plainTextPassword the user's plain text password
     * @return User object if login is successful, null otherwise
     */
    public User loginUser(String username, String plainTextPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBC_connection.getConnection();  // Assuming DBC_connection is your database connection class
            String sql = "SELECT username, password FROM users WHERE username = ?";  // Adjust table and column names as necessary
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                // Check the password
                if (PasswordUtil.checkPassword(plainTextPassword, storedHashedPassword)) {
                    return new User(username);  // Assuming User constructor takes username. Adjust as needed.
                } else {
                    System.out.println("Password does not match.");
                    return null;
                }
            } else {
                System.out.println("No user found with that username.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            // Close resources to prevent resource leaks
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("SQL Exception on close: " + ex.getMessage());
            }
        }
    }
}
