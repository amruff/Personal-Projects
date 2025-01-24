package AACapps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SerenitySpeak {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("SerenitySpeak");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        // Create a title label
        JLabel title = new JLabel("SerenitySpeak", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(70, 130, 180)); // Steel blue
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        
        // Set a calm color scheme for the background
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250)); // Light blue-green
        panel.setLayout(new GridLayout(5, 3, 10, 10)); // 5 rows, 3 columns, spacing 10px
        
        // Array of common phrases
        String[] phrases = {
            "I want food",
            "I need help",
            "I'm thirsty",
            "I feel happy",
            "I feel sad",
            "I need a break",
            "Yes",
            "No",
            "I want to play",
            "I'm tired",
            "I don't like this",
            "I love you",
            "I'm excited",
            "Please",
            "Thank you"
        };

        // Loop to create buttons for each phrase
        for (String phrase : phrases) {
            JButton button = new JButton(phrase);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.setBackground(new Color(255, 255, 204)); // Soft yellow
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            
            // Add action listener for text-to-speech
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Use the system's text-to-speech
                        Runtime.getRuntime().exec(new String[]{"say", phrase}); // For macOS
                        // For Windows, replace with "cmd", "/c", "echo " + phrase + " | powershell -c Add-Type -AssemblyName System.Speech; $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; $speak.Speak(\"" + phrase + "\")"
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            panel.add(button);
        }

        // Add padding around the grid
        JPanel container = new JPanel();
        container.setBackground(new Color(224, 247, 250));
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
