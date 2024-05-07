import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HomeUI {

    private JFrame frame;

    public HomeUI() {
        frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.add(new JLabel("Welcome to the Home Page!", SwingConstants.CENTER));
    }

    public void display() {
        frame.setVisible(true);
    }
}

