import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registerUser {

    public void userRegistration(String fullName, String username, String password) throws SQLException {
        String hashedPassword = PasswordUtil.hashPassword(password);
        String sql = "INSERT INTO users (full_name, username, password) VALUES (?, ?, ?)";

        try (Connection conn = DBC_connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, username);
            pstmt.setString(3, hashedPassword);
            pstmt.executeUpdate();
        }
    }
}
