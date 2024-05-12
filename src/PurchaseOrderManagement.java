import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PurchaseOrderManagement {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "Mart";
    private static final String PASSWORD = "niaz08";

    public static void recordPurchaseOrder(int orderId, int supplierId, Date purchaseDate,
                                           int productId, int quantity, double purchasePrice) {
        try (Connection conn = DBC_connection.getConnection()) {
            // Record the purchase order
            try (CallableStatement stmt = conn.prepareCall("{ call Record_Purchase_Order(?, ?, ?, ?, ?, ?) }")) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, supplierId);
                stmt.setDate(3, new java.sql.Date(purchaseDate.getTime()));
                stmt.setInt(4, productId);
                stmt.setInt(5, quantity);
                stmt.setDouble(6, purchasePrice);
                stmt.execute();
                System.out.println("Purchase order recorded successfully.");
            }

            // Update reorder threshold in product table
            updateReorderThreshold(conn, productId, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateReorderThreshold(Connection conn, int productId, int quantity) {
        try {
            // Retrieve current reorder threshold
            int currentThreshold = getCurrentReorderThreshold(conn, productId);

            // Update reorder threshold
            int newThreshold = currentThreshold + quantity;

            // Update reorder threshold in product table
            try (PreparedStatement stmt = conn.prepareStatement("UPDATE Products SET Reorder_Threshold = ? WHERE Product_ID = ?")) {
                stmt.setInt(1, newThreshold);
                stmt.setInt(2, productId);
                stmt.executeUpdate();
                System.out.println("Reorder threshold updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getCurrentReorderThreshold(Connection conn, int productId) throws SQLException {
        int currentThreshold = 0;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT Reorder_Threshold FROM Products WHERE Product_ID = ?")) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    currentThreshold = rs.getInt("Reorder_Threshold");
                }
            }
        }
        return currentThreshold;
    }
}
