import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;

public class RestockInventoryManagement {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "Mart";
    private static final String PASSWORD = "niaz08";

    public static void recordPurchaseOrder(int orderId, int supplierId, Date purchaseDate,
                                           int productId, int quantity, double purchasePrice) {
        try (Connection conn = DBC_connection.getConnection()) {

            try (CallableStatement stmt = conn.prepareCall("{ call Record_Purchase_Order(?, ?, ?, ?, ?, ?) }")) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, supplierId);
                stmt.setDate(3, new java.sql.Date(purchaseDate.getTime()));
                stmt.setInt(4, productId);
                stmt.setInt(5, quantity);
                stmt.setDouble(6, purchasePrice);
                stmt.executeUpdate();
                System.out.println("Product restocked successfully.");
            }



        } catch (SQLException e) {
            if (e.getErrorCode() == 20001) {
                System.out.println(e.getMessage());

            } else {
                e.printStackTrace();
            }
        }
    }


}
