package View;

import javax.swing.*;
import java.awt.*;
import static Model.Constants.*;

public class Menu {

    private JButton restartButton;
    private JButton quitButton;

    public Menu() {
        /** RESTART button configuration **/
        restartButton = new JButton("RESTART");
        restartButton.setBounds(BUTTON_X, RESTART_BTN_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        restartButton.setBackground(Color.DARK_GRAY);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.setFocusable(false); // Remove focus border on click
        restartButton.setVisible(false);   // Initially hidden, will be shown when game over

        /** QUIT button configuration **/
        quitButton = new JButton("QUIT");
        quitButton.setBounds(BUTTON_X, QUIT_BTN_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton.setFocusable(false); // Remove focus border on click
        quitButton.setVisible(false); // Initially hidden, will be shown when game over
    }

    /** Method to draw the game over background and text
     * @param finalScore the player's final score to display on the game over screen
     **/
    public void drawBackground(Graphics2D g2, int finalScore) {
        // Semi-transparent white background
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);

        // GAME OVER text
        g2.setColor(new Color(136, 0, 156));
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        String go = "You Dint Catch Your Rendez-Vous !";
        int goWidth = g2.getFontMetrics().stringWidth(go);
        g2.drawString(go, (DISPLAY_WIDTH - goWidth) / 2, DISPLAY_HEIGHT / 2 - 80);

        // Final Score text
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String fs = "Final Score : " + finalScore;
        int fsWidth = g2.getFontMetrics().stringWidth(fs);
        g2.drawString(fs, (DISPLAY_WIDTH - fsWidth) / 2, DISPLAY_HEIGHT / 2 - 40);
    }

    // Getters for the buttons to allow the controller to add action listeners
    public JButton getRestartButton() { return restartButton; }
    public JButton getQuitButton() { return quitButton; }

    /*---- Methods to show/hide buttons when game over or when restarting ----*/

    public void showButtons() {
        restartButton.setVisible(true);
        quitButton.setVisible(true);
    }

    public void hideButtons() {
        restartButton.setVisible(false);
        quitButton.setVisible(false);
    }
}