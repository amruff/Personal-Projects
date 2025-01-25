package AACapps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.IOException;

public class MatchTTS {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Accessible Matching Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // Set a calm color scheme
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250)); // Light blue-green
        panel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 grid for cards

        // Create an array of colors for matching pairs
        Color[] colors = {
            Color.RED, Color.RED,
            Color.BLUE, Color.BLUE,
            Color.GREEN, Color.GREEN,
            Color.YELLOW, Color.YELLOW,
            Color.ORANGE, Color.ORANGE,
            Color.PINK, Color.PINK,
            Color.CYAN, Color.CYAN,
            Color.MAGENTA, Color.MAGENTA
        };

        // Shuffle the colors
        Random random = new Random();
        for (int i = 0; i < colors.length; i++) {
            int randomIndex = random.nextInt(colors.length);
            Color temp = colors[i];
            colors[i] = colors[randomIndex];
            colors[randomIndex] = temp;
        }

        // Create buttons and assign colors
        JButton[] buttons = new JButton[16];
        boolean[] revealed = new boolean[16];
        Color[] assignedColors = new Color[16];

        for (int i = 0; i < 16; i++) {
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setText("?"); // Initial hidden state
            buttons[i] = button;
            assignedColors[i] = colors[i];

            int index = i; // Final index for the listener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!revealed[index]) {
                        button.setBackground(assignedColors[index]);
                        String colorName = getColorName(assignedColors[index]);
                        button.setText(colorName); // Display color name
                        speak(colorName); // Announce the color name
                        revealed[index] = true;
                        checkForMatch(buttons, assignedColors, revealed);
                    }
                }
            });
            panel.add(button);
        }

        // Add panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to check for matching pairs
    private static void checkForMatch(JButton[] buttons, Color[] colors, boolean[] revealed) {
        int revealedCount = 0;
        int firstIndex = -1;
        int secondIndex = -1;

        // Find the revealed cards
        for (int i = 0; i < revealed.length; i++) {
            if (revealed[i]) {
                revealedCount++;
                if (firstIndex == -1) {
                    firstIndex = i;
                } else {
                    secondIndex = i;
                }
            }
        }

        if (revealedCount == 2) {
            // Store the indices locally for use in the Timer
            final int localFirstIndex = firstIndex;
            final int localSecondIndex = secondIndex;

            if (!colors[firstIndex].equals(colors[secondIndex])) {
                // No match: reset after a short delay
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttons[localFirstIndex].setBackground(Color.WHITE);
                        buttons[localFirstIndex].setText("?");
                        buttons[localSecondIndex].setBackground(Color.WHITE);
                        buttons[localSecondIndex].setText("?");
                        revealed[localFirstIndex] = false;
                        revealed[localSecondIndex] = false;
                    }
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                // Match found: announce success
                speak("Match found: " + getColorName(colors[firstIndex]));
            }
        }
    }

    // Helper method to get color names
    private static String getColorName(Color color) {
        if (Color.RED.equals(color)) return "RED";
        if (Color.BLUE.equals(color)) return "BLUE";
        if (Color.GREEN.equals(color)) return "GREEN";
        if (Color.YELLOW.equals(color)) return "YELLOW";
        if (Color.ORANGE.equals(color)) return "ORANGE";
        if (Color.PINK.equals(color)) return "PINK";
        if (Color.CYAN.equals(color)) return "CYAN";
        if (Color.MAGENTA.equals(color)) return "MAGENTA";
        return "";
    }

    // Helper method for text-to-speech
    private static void speak(String text) {
        try {
            Runtime.getRuntime().exec(new String[]{"say", text}); // For macOS
            // For Windows, replace with: "cmd", "/c", "echo " + text + " | powershell -c Add-Type -AssemblyName System.Speech; $speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; $speak.Speak(\"" + text + "\")"
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
