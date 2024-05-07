import javax.swing.*;
import java.awt.*;
import java.sql.Connection; // Import Connection from java.sql package
import java.sql.PreparedStatement; // Import PreparedStatement from java.sql package
import java.sql.SQLException;

public class SignupUI {
    private JFrame frame;
    private JTextField fullNameField; // Declare as class member
    private JTextField emailField; // Declare as class member
    private JPasswordField passwordField; // Declare as class member

    public SignupUI() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Create a split pane to hold both the logo and the form
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(350); // You can adjust this for the size of your logo
        splitPane.setEnabled(false); // This will make the divider fixed

        // Create and add the logo panel
        JPanel logoPanel = createLogoPanel();
        splitPane.setLeftComponent(logoPanel);

        // Create and add the sign-up form
        JPanel signupFormPanel = createSignupFormPanel();
        splitPane.setRightComponent(signupFormPanel);

        frame.add(splitPane);
    }

    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(0, 153, 153)); // Set the background color

        // Add your logo
        ImageIcon logo = new ImageIcon("E:\\Downloads\\megamart.png"); // Replace with your logo path
        JLabel logoLabel = new JLabel(logo);



        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.add(Box.createVerticalGlue());
        logoPanel.add(logoLabel);
        logoPanel.add(Box.createVerticalGlue());

        return logoPanel;
    }

    private JPanel createSignupFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Add "SIGN UP" label
        JLabel signupLabel = new JLabel("SIGN UP");
        signupLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(signupLabel, gbc);

        // Add "Full Name" label and text field
        JLabel nameLabel = new JLabel("Full name");
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(nameLabel, gbc);

        fullNameField = new JTextField(15); // Now accessible throughout class
        gbc.weightx = 1;
        formPanel.add(fullNameField, gbc);

        // Add "Email" label and text field
        JLabel emailLabel = new JLabel("Username");
        gbc.weightx = 0;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(15);
        gbc.weightx = 1;
        formPanel.add(emailField, gbc);


        JLabel passwordLabel = new JLabel("Password");
        gbc.weightx = 0;
        formPanel.add(passwordLabel, gbc);


        passwordField = new JPasswordField(15); // Now accessible throughout class
        gbc.weightx = 1;
        formPanel.add(passwordField, gbc);


        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(e -> attemptSignup()); // Hook up the button to the method
        gbc.fill = GridBagConstraints.NONE; // Do not let the button stretch to fill the space
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(signupButton, gbc);

        // Add "I've an account" button
        // Inside the createSignupFormPanel method of SignupUI class
        JButton loginButton = new JButton("I have an Account");
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

// Add action listener for the "I have an Account" button
        loginButton.addActionListener(e -> {
            frame.setVisible(false); // Hide the signup frame
            LoginUI loginUI = new LoginUI(); // Create an instance of the LoginUI
            loginUI.display(); // Display the login frame
        });

        // Add action listeners for buttons (omitted for brevity)

        return formPanel;
    }
    private void attemptSignup() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Validation could be more complex with proper error messages
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hash the password
        String hashedPassword = PasswordUtil.hashPassword(password);

        // Create an instance of registerUser
        registerUser userRegistrar = new registerUser();

        // Call userRegistration method to register the user
        try {
            userRegistrar.userRegistration(fullName, email, hashedPassword);

            // If registration is successful, display a success message and close the signup frame
            JOptionPane.showMessageDialog(frame, "Signup successful!");
            frame.dispose();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print the full stack trace
            System.out.println("SQL State: " + ex.getSQLState()); // Print the SQL state

            // Handle the exception based on the SQL state or other information
            if (ex.getSQLState().startsWith("23")) {
                JOptionPane.showMessageDialog(frame, "A user with this email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            ex.printStackTrace();
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    // Additional methods for handling sign-up logic...
}
