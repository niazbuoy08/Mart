import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProductUI {
    private JFrame frame;
    private JTextField updateIdField;
    private JTextField newNameField;
    private JTextField newDescriptionField;
    private JTextField newPriceField;
    private JTextField newReorderThresholdField;

    public UpdateProductUI() {
        frame = new JFrame("Update Product Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel for update product form
        JPanel updateProductPanel = createUpdateProductPanel();
        frame.add(updateProductPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = createButtonPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createUpdateProductPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Components for update product form
        JLabel updateIdLabel = new JLabel("Product ID to Update:");
        updateIdField = new JTextField();
        JLabel newNameLabel = new JLabel("New Name:");
        newNameField = new JTextField();
        JLabel newDescriptionLabel = new JLabel("New Description:");
        newDescriptionField = new JTextField();
        JLabel newPriceLabel = new JLabel("New Price:");
        newPriceField = new JTextField();
        JLabel newReorderThresholdLabel = new JLabel("New Quantity:");
        newReorderThresholdField = new JTextField();

        // Add components to the panel
        panel.add(updateIdLabel);
        panel.add(updateIdField);
        panel.add(newNameLabel);
        panel.add(newNameField);
        panel.add(newDescriptionLabel);
        panel.add(newDescriptionField);
        panel.add(newPriceLabel);
        panel.add(newPriceField);
        panel.add(newReorderThresholdLabel);
        panel.add(newReorderThresholdField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Button to submit product update
        JButton updateButton = new JButton("Update Product");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve values from text fields
                int productIdToUpdate = Integer.parseInt(updateIdField.getText());
                String newProductName = newNameField.getText();
                String newProductDescription = newDescriptionField.getText();
                int newProductPrice = Integer.parseInt(newPriceField.getText());
                int newReorderThreshold = Integer.parseInt(newReorderThresholdField.getText());

                // Call method to update product using the entered product information
                ProductManagement product = new ProductManagement();
                product.updateProduct(productIdToUpdate, newProductName, newProductDescription, newProductPrice, newReorderThreshold);
                JOptionPane.showMessageDialog(frame, "Product updated successfully!");

                // Clear input fields
                updateIdField.setText("");
                newNameField.setText("");
                newDescriptionField.setText("");
                newPriceField.setText("");
                newReorderThresholdField.setText("");
            }
        });

        // Button to cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the update product UI
                frame.dispose();
            }
        });

        // Add buttons to the panel
        panel.add(updateButton);
        panel.add(cancelButton);

        return panel;
    }

    public void display() {
        frame.setVisible(true);
    }


}
