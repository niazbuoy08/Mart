import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
    public static void addProduct(int productId, String name, String description, double price,int reorderThreshold) {
        String query = "INSERT INTO Products (Product_ID, Name, Description, Price,Reorder_Threshold) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = DBC_connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
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

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";

        try (Connection conn = DBC_connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int productId = rs.getInt("Product_ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                int reorderThreshold = rs.getInt("Reorder_Threshold");

                Product product = new Product(productId, name, description, price, reorderThreshold);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public void updateProduct(int productId, String newName, String newDescription, int newPrice,int newReorderThreshold) {
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











