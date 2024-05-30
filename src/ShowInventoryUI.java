import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowInventoryUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public ShowInventoryUI() {
        frame = new JFrame("Inventory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500); // Adjusted size to match the login UI
        frame.setLocationRelativeTo(null);

        // Create table model and set column names
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Product ID", "Name", "Description", "Price", "Reorder Threshold"});

        // Create table with the model
        table = new JTable(model);

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add Refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTableData());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Initial data load
        refreshTableData();
    }

    private void refreshTableData() {
        // Clear existing data
        model.setRowCount(0);

        // Retrieve products from the database and add to the model
        List<Product> products = ProductManagement.getProducts();
        for (Product product : products) {
            model.addRow(new Object[]{
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getReorderThreshold()
            });
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShowInventoryUI showInventoryUI = new ShowInventoryUI();
            showInventoryUI.display();
        });
    }
}
