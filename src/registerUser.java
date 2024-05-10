import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class registerUser {

    public void userRegistration(String fullName, String username, String password) throws SQLException {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        String sql = "INSERT INTO users (full_name, username, password) VALUES (?, ?, ?)";

        try (Connection conn = DBC_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("User registered successfully: " + username);
            } else {
                System.out.println("Failed to register user: " + username);
            }
        } catch (SQLException e) {
            System.err.println("Error during user registration: " + e.getMessage());
            throw e;
        }
    }
}
