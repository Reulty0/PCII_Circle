package View;

import javax.swing.*;
import java.awt.*;
import static Model.Constants.*;

public class Menu {

    private JButton restartButton;
    private JButton quitButton;

    public Menu() {
        // --- Configuration du Bouton RESTART ---
        restartButton = new JButton("RESTART");
        restartButton.setBounds(BUTTON_X, RESTART_BTN_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        restartButton.setBackground(Color.DARK_GRAY);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.setFocusable(false); // TRÈS IMPORTANT : Empêche le bouton de voler le focus du clavier (Barre Espace)
        restartButton.setVisible(false);   // Caché au début

        // --- Configuration du Bouton QUIT ---
        quitButton = new JButton("QUIT");
        quitButton.setBounds(BUTTON_X, QUIT_BTN_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton.setFocusable(false);
        quitButton.setVisible(false);
    }

    /**
     * Méthode pour dessiner uniquement le texte et le fond semi-transparent
     * Les boutons se dessinent tout seuls car ce sont des composants Swing
     */
    public void drawBackground(Graphics2D g2, int finalScore) {
        // Fond semi-transparent
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);

        // Titre Game Over
        g2.setColor(new Color(136, 0, 156));
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        String go = "You Dint Catch Your Rendez-Vous !";
        int goWidth = g2.getFontMetrics().stringWidth(go);
        g2.drawString(go, (DISPLAY_WIDTH - goWidth) / 2, DISPLAY_HEIGHT / 2 - 80);

        // Score Final
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String fs = "Final Score : " + finalScore;
        int fsWidth = g2.getFontMetrics().stringWidth(fs);
        g2.drawString(fs, (DISPLAY_WIDTH - fsWidth) / 2, DISPLAY_HEIGHT / 2 - 40);

        // Note : On ne dessine plus les boutons ici, ils sont ajoutés au panel
    }

    // Getters pour récupérer les boutons et leur ajouter des actions
    public JButton getRestartButton() { return restartButton; }
    public JButton getQuitButton() { return quitButton; }

    // Gestion de la visibilité des boutons
    public void showButtons() {
        restartButton.setVisible(true);
        quitButton.setVisible(true);
    }

    public void hideButtons() {
        restartButton.setVisible(false);
        quitButton.setVisible(false);
    }
}