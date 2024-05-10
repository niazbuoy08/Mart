import javax.swing.*;
import java.awt.*;

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

        frame.add(mainPanel);
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        HomeUI homeUI = new HomeUI();
        homeUI.display();
    }
}
