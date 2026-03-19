import java.awt.*;              // Import Swing components
import java.time.LocalDateTime;                 // Import layout managers
import java.util.UUID;    // For the system date and time
import javax.swing.*;             // This is for generating the E-Signatures

/**
 * Attendance tracker app
 * Uses Java Swing to display the attendance details
 */
public class AttendanceTracker {

    public static void main(String[] args) {

        // Create the main frame
        JFrame frame = new JFrame("Attendance Tracker");
        frame.setSize(500, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Creates 4 rows and 2 columns

        // Create labels
        JLabel nameLabel = new JLabel("Attendance Name:");
        JLabel courseLabel = new JLabel("Course and Year:");
        JLabel timeInLabel = new JLabel("Time In:");
        JLabel signatureLabel = new JLabel("E-Signature:");

        // Text fields
        JTextField nameField = new JTextField();
        JTextField courseField = new JTextField();
        JTextField timeInField = new JTextField();
        JTextField signatureField = new JTextField();

        // Obtain the systems date and time
        String timeIn = LocalDateTime.now().toString();
        timeInField.setText(timeIn);
        timeInField.setEditable(false); // Prevent editing

        // Generate E-Signature
        String eSignature = UUID.randomUUID().toString();
        signatureField.setText(eSignature);
        signatureField.setEditable(false); // Prevent editing

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(courseLabel);
        panel.add(courseField);
        panel.add(timeInLabel);
        panel.add(timeInField);
        panel.add(signatureLabel);
        panel.add(signatureField);

        // Add panel to the frame
        frame.add(panel);

        // Center the jpanel on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
