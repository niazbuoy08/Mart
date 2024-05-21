import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUI {
    private JFrame frame;

    public HomeUI() {
        frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(1, 2));  // Split layout into two halves

        // Left panel with teal background (empty for now)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0, 128, 128));  // Teal background

        // Right panel for welcome label and buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(0, 128, 128));  // Teal background

        // Welcome label with customized font and color
        JLabel welcomeLabel = new JLabel("Welcome to the Home Page!", SwingConstants.CENTER);
        welcomeLabel.setForeground(new Color(255, 255, 255));  // White text
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));  // Set font type and size
        rightPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));  // 5 rows for 5 buttons
        buttonPanel.setBackground(new Color(0, 128, 128));  // Teal background
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));  // Padding

        // Button for adding product
        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddProductUI addProductUI = new AddProductUI();
                addProductUI.display();
            }
        });
        buttonPanel.add(addProductButton);

        // Button for updating product
        JButton updateProductButton = new JButton("Update Product");
        updateProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateProductUI updateProductUI = new UpdateProductUI();
                updateProductUI.display();
            }
        });
        buttonPanel.add(updateProductButton);

        // Button for deleting product
        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteProductUI deleteProductUI = new DeleteProductUI();
                deleteProductUI.display();
            }
        });
        buttonPanel.add(deleteProductButton);

        // Button for purchase order
        JButton purchaseOrderButton = new JButton("Purchase Order");
        purchaseOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PurchaseOrderUI purchaseOrderUI = new PurchaseOrderUI();
                purchaseOrderUI.display();
            }
        });
        buttonPanel.add(purchaseOrderButton);
        JButton salesOrderButton = new JButton("Sales Order");
        salesOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SalesOrderUI salesOrderUI = new SalesOrderUI();
                salesOrderUI.display();
            }
        });
        buttonPanel.add(salesOrderButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginUI().display();
            }
        });
        buttonPanel.add(logoutButton);

        rightPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add panels to the frame
        frame.add(leftPanel);
        frame.add(rightPanel);
    }

    public void display() {
        frame.setVisible(true);
    }


}
