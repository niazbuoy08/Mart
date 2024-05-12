import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManagement {


    static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE"; // Update with your database URL
    static final String USERNAME = "Mart";
    static final String PASSWORD = "niaz08";


    public static void addProduct(int productId, String name, String description, double price,int reorderThreshold) {
        String query = "INSERT INTO Products (Product_ID, Name, Description, Price,Reorder_Threshold) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = DBC_connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setDouble(4, price);
            stmt.setInt(5, reorderThreshold);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateProduct(int productId, String newName, String newDescription, double newPrice,int newReorderThreshold) {
        String query = "UPDATE Products SET Name = ?, Description = ?, Price = ?,Reorder_Threshold=? WHERE Product_ID = ?";

        try (Connection conn = DBC_connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, newDescription);
            stmt.setDouble(3, newPrice);
            stmt.setInt(4, newReorderThreshold);
            stmt.setInt(5, productId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update product.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void deleteProduct(int productId) {
        // SQL query to delete a product
        String deleteQuery = "DELETE FROM Products WHERE Product_ID = ?";

        try (Connection conn = DBC_connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
            // Set the product ID parameter in the prepared statement
            stmt.setInt(1, productId);

            // Execute the delete query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}











