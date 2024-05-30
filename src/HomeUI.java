import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomeUI {
    private JFrame frame;

    public HomeUI() {
        frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(1, 2));  // Split layout into two halves

        // Left panel similar to LoginUI
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 153, 153)); // Custom purple color
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("RadiantSkin");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(label);
        leftPanel.add(Box.createVerticalGlue());

        leftPanel.setPreferredSize(new Dimension(400, frame.getHeight()));

        // Right panel for buttons, similar to LoginUI
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexibility
        rightPanel.setBackground(Color.WHITE); // Set background color to white
        GridBagConstraints gbc = new GridBagConstraints();

        // Constraints common to all components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(20, 50, 10, 50); // Margin for components, increased top inset

        // Buttons similar to LoginUI
        gbc.weightx = 0.0; // Reset to default
        gbc.anchor = GridBagConstraints.CENTER; // Center align components
        gbc.weighty = 1; // Request any extra vertical space
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row

        JButton addProductButton = new JButton("Add Product");
        rightPanel.add(addProductButton, gbc);

        JButton updateProductButton = new JButton("Update Product");
        rightPanel.add(updateProductButton, gbc);

        JButton deleteProductButton = new JButton("Delete Product");
        rightPanel.add(deleteProductButton, gbc);

        JButton purchaseOrderButton = new JButton("Restock Inventory");
        rightPanel.add(purchaseOrderButton, gbc);

        JButton salesOrderButton = new JButton("Sell Product");
        rightPanel.add(salesOrderButton, gbc);

        JButton showInventoryButton = new JButton("Show Inventory");
        rightPanel.add(showInventoryButton, gbc);

        JButton showInventoryAlertsButton = new JButton("Show Inventory Alerts");
        rightPanel.add(showInventoryAlertsButton, gbc);

        JButton logoutButton = new JButton("Logout");
        rightPanel.add(logoutButton, gbc);

        // Add action listeners for buttons
        addProductButton.addActionListener(e -> {
            AddProductUI addProductUI = new AddProductUI();
            addProductUI.display();
        });

        updateProductButton.addActionListener(e -> {
            UpdateProductUI updateProductUI = new UpdateProductUI();
            updateProductUI.display();
        });

        deleteProductButton.addActionListener(e -> {
            DeleteProductUI deleteProductUI = new DeleteProductUI();
            deleteProductUI.display();
        });

        purchaseOrderButton.addActionListener(e -> {
            RestockInventoryUI restockInventoryUI = new RestockInventoryUI();
            restockInventoryUI.display();
        });

        salesOrderButton.addActionListener(e -> {
            SalesOrderUI salesOrderUI = new SalesOrderUI();
            salesOrderUI.display();
        });

        showInventoryButton.addActionListener(e -> {
            ShowInventoryUI showInventoryUI = new ShowInventoryUI();
            showInventoryUI.display();
        });

        showInventoryAlertsButton.addActionListener(e -> {
            List<String> alerts = InventoryAlerts.getAlerts();
            InventoryAlerts.displayAlerts(alerts);
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginUI().display();
        });

        // Add left and right panels to the frame
        frame.add(leftPanel);
        frame.add(rightPanel);
    }

    public void display() {
        frame.setVisible(true);
    }
}
