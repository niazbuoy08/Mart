import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class RestockInventoryUI {
    private JFrame frame;
    private JTextField orderIDField;
    private JTextField supplierIDField;
    private JTextField purchaseDateField;
    private JTextField productIDField;
    private JTextField quantityField;
    private JTextField purchasePriceField;

    public RestockInventoryUI() {
        frame = new JFrame("Restock Inventory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose only this frame on close
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel for purchase order form
        JPanel purchaseOrderPanel = createPurchaseOrderPanel();
        frame.add(purchaseOrderPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = createButtonPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createPurchaseOrderPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Components for purchase order form
        JLabel orderIDLabel = new JLabel("Order ID:");
        orderIDField = new JTextField();
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDField = new JTextField();
        JLabel purchaseDateLabel = new JLabel("Purchase Date:");
        purchaseDateField = new JTextField();
        JLabel productIDLabel = new JLabel("Product ID:");
        productIDField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        purchasePriceField = new JTextField();

        // Add components to the panel
        panel.add(orderIDLabel);
        panel.add(orderIDField);
        panel.add(supplierIDLabel);
        panel.add(supplierIDField);
        panel.add(purchaseDateLabel);
        panel.add(purchaseDateField);
        panel.add(productIDLabel);
        panel.add(productIDField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(priceLabel);
        panel.add(purchasePriceField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Button to submit purchase order
        JButton submitButton = new JButton("Restock");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int orderID = Integer.parseInt(orderIDField.getText());
                    int supplierID = Integer.parseInt(supplierIDField.getText());
                    Date purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDateField.getText());
                    int productID = Integer.parseInt(productIDField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    double purchasePrice = Double.parseDouble(purchasePriceField.getText());

                    RestockInventoryManagement purchase = new RestockInventoryManagement();
                    purchase.recordPurchaseOrder(orderID, supplierID, purchaseDate, productID, quantity, purchasePrice);
                    JOptionPane.showMessageDialog(frame, "Product restocked successfully!");

                    // Clear input fields
                    orderIDField.setText("");
                    supplierIDField.setText("");
                    purchaseDateField.setText("");
                    productIDField.setText("");
                    quantityField.setText("");
                    purchasePriceField.setText("");
                } catch (NumberFormatException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Button to cancel
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the purchase order UI
                frame.dispose();
            }
        });

        // Add buttons to the panel
        panel.add(submitButton);
        panel.add(cancelButton);

        return panel;
    }

    public void display() {
        frame.setVisible(true);
    }


}
