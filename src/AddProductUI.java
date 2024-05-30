import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductUI {
    private JFrame frame;
    private JTextField idField;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JTextField reorderthresholdField;

    public AddProductUI() {
        frame = new JFrame("Add Product Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel for add product form
        JPanel addProductPanel = createAddProductPanel();
        frame.add(addProductPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = createButtonPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createAddProductPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Components for add product form
        JLabel idLabel = new JLabel("Product ID:");
        idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel reorderthresholdLabel = new JLabel("Quantity:");
        reorderthresholdField = new JTextField();

        // Add components to the panel
        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(reorderthresholdLabel);
        panel.add(reorderthresholdField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Button to submit product
        JButton submitButton = new JButton("Add");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieve values from text fields
                    int productId = Integer.parseInt(idField.getText());
                    String productName = nameField.getText();
                    String productDescription = descriptionField.getText();
                    double productPrice = Double.parseDouble(priceField.getText());
                    int reorder = Integer.parseInt(reorderthresholdField.getText());

                    // Call method to add product to inventory using the entered product information
                    ProductManagement.addProduct(productId, productName, productDescription, productPrice, reorder);
                    JOptionPane.showMessageDialog(frame, "Product added successfully!");


                    // Clear input fields
                    idField.setText("");
                    nameField.setText("");
                    descriptionField.setText("");
                    priceField.setText("");
                    reorderthresholdField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Button to cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the add product UI
                frame.dispose();
            }
        });

        // Add buttons to the panel
        panel.add(submitButton);
        panel.add(cancelButton);

        return panel;
    }

    // Method to add a product to the inventory
    private void addProduct(int productId, String productName, String productDescription, double productPrice, int reorderThreshold) {
        // Implement logic to add product to inventory
        // This is just a placeholder method, you need to replace it with your actual implementation
        System.out.println("Adding product to inventory:");
        System.out.println("ID: " + productId);
        System.out.println("Name: " + productName);
        System.out.println("Description: " + productDescription);
        System.out.println("Price: " + productPrice);
        System.out.println("Reorder Threshold: " + reorderThreshold);
    }

    public void display() {
        frame.setVisible(true);
    }


}
