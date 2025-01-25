package AACapps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class VocalLink {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("VocalLink");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        // Create a title label
        JLabel title = new JLabel("VocalLink", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(70, 130, 180)); // Steel blue
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // Set a high-contrast color scheme for accessibility
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(5, 3, 10, 10)); // 5 rows, 3 columns, spacing 10px

        // Array of actions
        String[] actions = {
            "Read time",
            "Check weather",
            "Start navigation",
            "Call emergency",
            "Open messages",
            "Battery status",
            "Volume up",
            "Volume down",
            "Play music",
            "Pause music",
            "Stop music",
            "Next track",
            "Previous track",
            "WiFi status",
            "Bluetooth status"
        };

        // Add keyboard shortcuts and improve button feedback
        int mnemonicIndex = 1; // To assign unique keyboard shortcuts (Alt + number keys)

        // Loop to create buttons for each action
        for (String action : actions) {
            JButton button = new JButton(action);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(Color.YELLOW);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            
            // Assign keyboard shortcuts for buttons
            button.setMnemonic(KeyEvent.VK_0 + mnemonicIndex);
            mnemonicIndex = (mnemonicIndex % 9) + 1; // Cycle through 1-9

            // Add focus listener for text-to-speech on hover or focus
            button.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    try {
                        Runtime.getRuntime().exec(new String[]{"say", action}); // For macOS
                        // For Windows, replace with "cmd", "/c", "echo " + action + " | powershell -c Add-Type -AssemblyName System.Speech; $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; $speak.Speak(\"" + action + "\")"
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // Do nothing
                }
            });

            // Add action listener for text-to-speech when clicked
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Runtime.getRuntime().exec(new String[]{"say", "You selected " + action}); // For macOS
                        // For Windows, replace with the same command used above
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            panel.add(button);
        }

        // Add padding around the grid
        JPanel container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH); // Add the title at the top
        container.add(panel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add the panel to the frame
        frame.add(container);

        // Make the frame visible
        frame.setVisible(true);
    }
}