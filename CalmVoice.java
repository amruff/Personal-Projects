package AACapps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalmVoice {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("CalmVoice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);

        // Set a gradient background using a custom panel
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(135, 206, 250); // Sky blue
                Color endColor = new Color(255, 182, 193);   // Light pink
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("CalmVoice", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        gradientPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a grid panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(5, 3, 10, 10)); // 5 rows, 3 columns, spacing 10px

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
            button.setBackground(new Color(173, 216, 230)); // Light blue
            button.setForeground(Color.DARK_GRAY);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Add hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(135, 206, 235)); // Deeper blue
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(173, 216, 230)); // Original blue
                }
            });

            // Add action listener for button click
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Replace this block with text-to-speech functionality
                    JOptionPane.showMessageDialog(frame, "You clicked: " + phrase);
                }
            });

            buttonPanel.add(button);
        }

        // Add padding around the grid
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BorderLayout());
        container.add(buttonPanel, BorderLayout.CENTER);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add components to the main gradient panel
        gradientPanel.add(container, BorderLayout.CENTER);

        // Add the gradient panel to the frame
        frame.add(gradientPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
