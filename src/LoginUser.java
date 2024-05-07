import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUser {

    public User loginUser(String username, String password) {
        String sql = "SELECT username, password FROM users WHERE username = ?";

        try (Connection conn = DBC_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("No user found with the username: " + username);
                return null;
            } else {
                String storedPassword = rs.getString("password");
                if (PasswordUtil.checkPassword(password, storedPassword)) {
                    System.out.println("Login successful for user: " + username);
                    return new User(rs.getString("username"));
                } else {
                    System.out.println("Password mismatch for user: " + username);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception while logging in: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
