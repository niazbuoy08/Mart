import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductUI {
    private JFrame frame;
    private JTextField deleteIdField;

    public DeleteProductUI() {
        frame = new JFrame("Delete Product Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel for delete product form
        JPanel deleteProductPanel = createDeleteProductPanel();
        frame.add(deleteProductPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = createButtonPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createDeleteProductPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Components for delete product form
        JLabel deleteIdLabel = new JLabel("Product ID to Delete:");
        deleteIdField = new JTextField();

        // Add components to the panel
        panel.add(deleteIdLabel);
        panel.add(deleteIdField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Button to submit product deletion
        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the product ID to delete
                int productIdToDelete = Integer.parseInt(deleteIdField.getText());

                // Call method to delete product using the entered product ID
                ProductManagement product = new ProductManagement();
                product.deleteProduct(productIdToDelete);
                JOptionPane.showMessageDialog(frame, "Product deleted successfully!");

                // Clear input fields
                deleteIdField.setText("");
            }
        });

        // Button to cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the delete product UI
                frame.dispose();
            }
        });

        // Add buttons to the panel
        panel.add(deleteButton);
        panel.add(cancelButton);

        return panel;
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        DeleteProductUI deleteProductUI = new DeleteProductUI();
        deleteProductUI.display();
    }
}
