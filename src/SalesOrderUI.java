import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesOrderUI {
    private JFrame frame;
    private JTextField orderIDField;
    private JTextField customerIDField;
    private JTextField productIDField;
    private JTextField quantityField;
    private JTextField orderDateField;

    public SalesOrderUI() {
        frame = new JFrame("Sell Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose only this frame on close
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel for sales order form
        JPanel salesOrderPanel = createSalesOrderPanel();
        frame.add(salesOrderPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = createButtonPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createSalesOrderPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Components for sales order form
        JLabel orderIDLabel = new JLabel("Order ID:");
        orderIDField = new JTextField();
        JLabel customerIDLabel = new JLabel("Customer ID:");
        customerIDField = new JTextField();
        JLabel productIDLabel = new JLabel("Product ID:");
        productIDField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField();
        JLabel orderDateLabel = new JLabel("Order Date:");
        orderDateField = new JTextField();

        // Add components to the panel
        panel.add(orderIDLabel);
        panel.add(orderIDField);
        panel.add(customerIDLabel);
        panel.add(customerIDField);
        panel.add(productIDLabel);
        panel.add(productIDField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(orderDateLabel);
        panel.add(orderDateField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Button to submit sales order
        JButton submitButton = new JButton("Order");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int orderID = Integer.parseInt(orderIDField.getText());
                    int customerID = Integer.parseInt(customerIDField.getText());
                    int productID = Integer.parseInt(productIDField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDateField.getText());

                    SalesOrderManagement sales = new SalesOrderManagement();
                    sales.recordSalesOrder(orderID, customerID, productID, quantity, orderDate);
                    JOptionPane.showMessageDialog(frame, "Products sold successfully!");

                    // Clear input fields
                    orderIDField.setText("");
                    customerIDField.setText("");
                    productIDField.setText("");
                    quantityField.setText("");
                    orderDateField.setText("");
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
                // Close the sales order UI
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
