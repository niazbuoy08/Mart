import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class SalesOrderManagement {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "Mart";
    private static final String PASSWORD = "niaz08";

    public static void recordSalesOrder(int orderId, int customerId, int productId, int quantity, Date orderDate) {

        try (Connection conn = DBC_connection.getConnection()) {
            try (CallableStatement stmt = conn.prepareCall("{ call Record_Sales_Order(?, ?, ?, ?, ?) }")) {


                stmt.setInt(1, orderId);
                stmt.setInt(2, customerId);
                stmt.setInt(3, productId);
                stmt.setInt(4, quantity);
                stmt.setDate(5, new java.sql.Date(orderDate.getTime()));


                stmt.execute();


            }

        } catch (SQLException e) {
            if (e.getErrorCode() == -20001) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
    }
}
