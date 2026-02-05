package View;

import java.awt.*;
import static Model.Constants.*;

public class Menu {

    public Menu() {
        // Rien à initialiser de particulier
    }

    /**
     * Dessine le menu de Game Over par dessus le jeu
     */
    public void draw(Graphics2D g2, int finalScore) {
        // 1. Fond semi-transparent
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);

        // 2. Titre Game Over
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        String go = "GAME OVER";
        int goWidth = g2.getFontMetrics().stringWidth(go);
        g2.drawString(go, (DISPLAY_WIDTH - goWidth) / 2, DISPLAY_HEIGHT / 2 - 80);

        // 3. Score Final
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String fs = "Final Score : " + finalScore;
        int fsWidth = g2.getFontMetrics().stringWidth(fs);
        g2.drawString(fs, (DISPLAY_WIDTH - fsWidth) / 2, DISPLAY_HEIGHT / 2 - 40);

        // 4. Bouton RESTART
        g2.setColor(Color.DARK_GRAY);
        g2.fillRoundRect(BUTTON_X, RESTART_BTN_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 20, 20);
        g2.setColor(Color.WHITE);
        g2.drawString("RESTART", BUTTON_X + 60, RESTART_BTN_Y + 32);

        // 5. Bouton QUIT
        g2.setColor(Color.DARK_GRAY);
        g2.fillRoundRect(BUTTON_X, QUIT_BTN_Y, BUTTON_WIDTH, BUTTON_HEIGHT, 20, 20);
        g2.setColor(Color.WHITE);
        g2.drawString("QUIT", BUTTON_X + 80, QUIT_BTN_Y + 32);
    }

    /** Vérifie si on a cliqué sur RESTART */
    public boolean isRestartClicked(int x, int y) {
        return (x >= BUTTON_X && x <= BUTTON_X + BUTTON_WIDTH &&
                y >= RESTART_BTN_Y && y <= RESTART_BTN_Y + BUTTON_HEIGHT);
    }

    /** Vérifie si on a cliqué sur QUIT */
    public boolean isQuitClicked(int x, int y) {
        return (x >= BUTTON_X && x <= BUTTON_X + BUTTON_WIDTH &&
                y >= QUIT_BTN_Y && y <= QUIT_BTN_Y + BUTTON_HEIGHT);
    }
}