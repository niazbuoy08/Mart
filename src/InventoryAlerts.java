import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InventoryAlerts {

    public static List<String> getAlerts() {
        List<String> alerts = new ArrayList<>();
        String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // Update with your DB details
        String user = "Mart";
        String password = "niaz08";

        String query = "SELECT Message FROM Alerts WHERE Alert_Time >= SYSDATE - 1"; // Adjust time frame as needed

        try (Connection connection = DBC_connection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                alerts.add(resultSet.getString("Message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alerts;
    }



    public static void displayAlerts(List<String> alerts) {
        if (alerts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No inventory alerts at this time.", "Inventory Alerts", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder alertMessage = new StringBuilder("Inventory Alerts:\n");
            for (String alert : alerts) {
                alertMessage.append(alert).append("\n");
            }
            JOptionPane.showMessageDialog(null, alertMessage.toString(), "Inventory Alerts", JOptionPane.WARNING_MESSAGE);
        }
    }


    public static void main(String[] args) {
        List<String> alerts = getAlerts();
        displayAlerts(alerts);
    }
}

