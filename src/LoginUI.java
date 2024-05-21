import javax.swing.*;
import java.awt.*;

public class LoginUI {
     private JFrame frame;
     private JTextField userField;
     private JPasswordField passField;

     private JButton signupButton;

     public LoginUI() {
          frame = new JFrame("Custom Login UI");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(800, 500);
          frame.setLocationRelativeTo(null);

          JPanel leftPanel = createLeftPanel();
          JPanel rightPanel = createRightPanel();

          frame.add(leftPanel, BorderLayout.WEST);
          frame.add(rightPanel, BorderLayout.CENTER);
     }

     public void display() {
          frame.setVisible(true);
     }

     private JPanel createLeftPanel() {
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

          return leftPanel;
     }

     private JPanel createRightPanel() {
          JPanel rightPanel = new JPanel();
          rightPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexibility
          rightPanel.setBackground(Color.WHITE); // Set background color to white
          GridBagConstraints gbc = new GridBagConstraints();

          // Constraints common to all components
          gbc.fill = GridBagConstraints.HORIZONTAL;
          gbc.gridwidth = GridBagConstraints.REMAINDER;
          gbc.insets = new Insets(20, 50, 10, 50); // Margin for components, increased top inset

          gbc.weightx = 0.1; // Request any extra horizontal space
          gbc.anchor = GridBagConstraints.LINE_END; // Right align components
          rightPanel.add(new JLabel("Username:"), gbc);

          gbc.weightx = 0.9; // Request any extra horizontal space
          gbc.anchor = GridBagConstraints.LINE_START; // Left align components
          userField = new JTextField(20);
          rightPanel.add(userField, gbc);

          // Password label and text field
          gbc.anchor = GridBagConstraints.LINE_END;
          gbc.insets = new Insets(10, 50, 10, 50); // Reset top inset, keep default for others
          rightPanel.add(new JLabel("Password:"), gbc);

          gbc.anchor = GridBagConstraints.LINE_START;
          passField = new JPasswordField(20);
          rightPanel.add(passField, gbc);

          // Email label and text field (if required)
          // Add similar blocks for email label and text field here

          // Buttons
          gbc.anchor = GridBagConstraints.CENTER; // Center align components
          gbc.weighty = 1; // Request any extra vertical space
          gbc.weightx = 0.0; // Reset to default
          gbc.gridwidth = GridBagConstraints.RELATIVE; // Set the buttons next to each other
          signupButton = new JButton("Signup");
          rightPanel.add(signupButton, gbc);

          gbc.gridwidth = GridBagConstraints.REMAINDER; // End row
          JButton loginButton = new JButton("Login");
          rightPanel.add(loginButton, gbc);

          // Set up action listeners for buttons
          signupButton.addActionListener(e -> {
               frame.setVisible(false);
               new SignupUI().display();
          });


          loginButton.addActionListener(e -> attemptLogin());
          signupButton.addActionListener(e -> {
               frame.setVisible(false);
               new SignupUI().display();
          });


          return rightPanel;
     }


     private void attemptLogin() {
          String username = userField.getText();
          char[] password = passField.getPassword();
          String passwordString = new String(password);

          User user = new LoginUser().loginUser(username, passwordString);
          if (user != null) {
               JOptionPane.showMessageDialog(frame, "Login successful!");
               frame.dispose();
               new HomeUI().display();
          } else {
               JOptionPane.showMessageDialog(frame, "Invalid username or password.");
               passField.setText("");
          }
     }

}