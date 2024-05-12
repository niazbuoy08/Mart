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
        frame.setLayout(new BorderLayout());  // Set layout manager

        // Create a main panel with teal aesthetics
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0, 128, 128));  // Teal background

        // Welcome label with customized font and color
        JLabel welcomeLabel = new JLabel("Welcome to the Home Page!", SwingConstants.CENTER);
        welcomeLabel.setForeground(new Color(255, 255, 255));  // White text
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));  // Set font type and size
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Panel for adding a product
        JPanel addProductPanel = createAddProductPanel();
        mainPanel.add(addProductPanel, BorderLayout.NORTH);

        // Panel for updating a product
        JPanel updateProductPanel = createUpdateProductPanel();
        mainPanel.add(updateProductPanel, BorderLayout.CENTER);

        JPanel deleteProductPanel = createDeleteProductPanel();
        mainPanel.add(deleteProductPanel, BorderLayout.SOUTH);
        JButton purchaseOrderButton = createPurchaseOrderButton();
        mainPanel.add(purchaseOrderButton, BorderLayout.SOUTH);
        // Add the main panel to the frame
        frame.add(mainPanel);
    }

    // Method to create the panel for adding a product
    private JPanel createAddProductPanel() {
        JPanel addProductPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns
        JLabel idLabel = new JLabel("Product ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JLabel reorderLabel = new JLabel("Reorder Threshold:");
        JTextField reorderField = new JTextField();
        JButton addProductButton = new JButton("Add Product");

        // Add components to the addProductPanel
        addProductPanel.add(idLabel);
        addProductPanel.add(idField);
        addProductPanel.add(nameLabel);
        addProductPanel.add(nameField);
        addProductPanel.add(descriptionLabel);
        addProductPanel.add(descriptionField);
        addProductPanel.add(priceLabel);
        addProductPanel.add(priceField);
        addProductPanel.add(reorderLabel);
        addProductPanel.add(reorderField);
        addProductPanel.add(addProductButton);

        // Add action listener to the "Add Product" button
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve values from text fields
                int productId = Integer.parseInt(idField.getText());
                String productName = nameField.getText();
                String productDescription = descriptionField.getText();
                double productPrice = Double.parseDouble(priceField.getText());
                int reorderThreshold = Integer.parseInt(reorderField.getText());
                ProductManagement product= new ProductManagement();
                product.addProduct(productId, productName, productDescription, productPrice, reorderThreshold);
                idField.setText("");
                nameField.setText("");
                descriptionField.setText("");
                priceField.setText("");
                reorderField.setText("");
            }
        });

        return addProductPanel;
    }

    // Method to create the panel for updating a product
    private JPanel createUpdateProductPanel() {
        JPanel updateProductPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns
        JLabel updateIdLabel = new JLabel("Product ID to Update:");
        JTextField updateIdField = new JTextField();
        JLabel newNameLabel = new JLabel("New Name:");
        JTextField newNameField = new JTextField();
        JLabel newDescriptionLabel = new JLabel("New Description:");
        JTextField newDescriptionField = new JTextField();
        JLabel newPriceLabel = new JLabel("New Price:");
        JTextField newPriceField = new JTextField();
        JLabel newreorderThresholdlabel = new JLabel("New Reorder Threshold:");
        JTextField newreorderThresholdField = new JTextField();
        JButton updateProductButton = new JButton("Update Product");

        // Add components to the updateProductPanel
        updateProductPanel.add(updateIdLabel);
        updateProductPanel.add(updateIdField);
        updateProductPanel.add(newNameLabel);
        updateProductPanel.add(newNameField);
        updateProductPanel.add(newDescriptionLabel);
        updateProductPanel.add(newDescriptionField);
        updateProductPanel.add(newPriceLabel);
        updateProductPanel.add(newPriceField);
        updateProductPanel.add(newreorderThresholdlabel);
        updateProductPanel.add(newreorderThresholdField);
        updateProductPanel.add(updateProductButton);

        // Add action listener to the "Update Product" button
        updateProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve values from text fields
                int productIdToUpdate = Integer.parseInt(updateIdField.getText());
                String newProductName = newNameField.getText();
                String newProductDescription = newDescriptionField.getText();
                double newProductPrice = Double.parseDouble(newPriceField.getText());
                int newreorderThreshold = Integer.parseInt(newreorderThresholdField.getText());
                ProductManagement product= new ProductManagement();
                product.updateProduct(productIdToUpdate, newProductName, newProductDescription, newProductPrice,newreorderThreshold);
                updateIdField.setText("");
                newNameField.setText("");
                newDescriptionField.setText("");
                newPriceField.setText("");
                newreorderThresholdField.setText("");
            }
        });

        return updateProductPanel;
    }
    private JPanel createDeleteProductPanel() {
        JPanel deleteProductPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns
        JLabel deleteIdLabel = new JLabel("Product ID to Delete:");
        JTextField deleteIdField = new JTextField();
        JButton deleteProductButton = new JButton("Delete Product");

        // Add components to the deleteProductPanel
        deleteProductPanel.add(deleteIdLabel);
        deleteProductPanel.add(deleteIdField);
        deleteProductPanel.add(deleteProductButton);

        // Add action listener to the "Delete Product" button
        deleteProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve the product ID to delete
                int productIdToDelete = Integer.parseInt(deleteIdField.getText());

                // Call the delete product method from ProductManagement
                ProductManagement.deleteProduct(productIdToDelete);

                // Clear the text field after deleting the product
                deleteIdField.setText("");
            }
        });

        return deleteProductPanel;
    }
    private JButton createPurchaseOrderButton() {
        JButton purchaseOrderButton = new JButton("Purchase Order");
        purchaseOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PurchaseOrderUI purchaseOrderUI = new PurchaseOrderUI();
                purchaseOrderUI.display();
            }
        });
        return purchaseOrderButton;
    }


    public void display() {
        frame.setVisible(true);
    }


}
