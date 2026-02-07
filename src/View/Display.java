package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static Model.Constants.*;

public class Display extends JPanel {
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private Menu myMenu;
    private Collision myCollision;
    private BonusElement myBonusElement;
    private BonusItems myBonusItems;

    // Visual assets for better aesthetics
    private Image bgImage;
    private Image heartImage;
    private Image noHeartImage;
    private Image bonusImage;


    public Display(Position p, Line l, Score s, Menu m, Collision c, BonusElement b, BonusItems bi){
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        setLayout(null);

        // --- Important for the KeyBoard ---
        this.setFocusable(true); // Allows the panel to receive keyboard focus
        this.requestFocusInWindow(); // Requests focus so that key events are captured immediately
        // ---------------------------------------

        myPosition = p;
        myLine = l;
        myScore = s;
        myMenu = m;
        myCollision = c;
        myBonusElement = b;
        myBonusItems = bi;

        try { // Load images, if available
            bgImage = new ImageIcon("media/background.png").getImage();
            heartImage = new ImageIcon("media/heart.png").getImage();
            noHeartImage = new ImageIcon("media/no_heart.png").getImage();
            bonusImage = new ImageIcon("media/bonus.png").getImage();
        } catch (Exception e) {}

        this.add(myMenu.getRestartButton());
        this.add(myMenu.getQuitButton());
    }

    /**
     * This method is called automatically whenever the panel needs to be redrawn.
     * It handles all the drawing logic for the game, including the background,
     * player, line, bonuses, HUD, and menus.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /** 1. BACKGROUND */
        if (bgImage != null) g2.drawImage(bgImage, 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null); // Draw background image if available
        else { g2.setColor(Color.LIGHT_GRAY); g2.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT); } // Fallback: simple background color

        /** 2. BONUS ELEMENT */
        myBonusElement.draw(g2);

        /** Invincibility blinking effect */
        AlphaComposite transparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f); // 30% opacity for blinking effect
        AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f); // Full opacity for normal drawing
        boolean shouldBlink = IS_INVINCIBLE && System.currentTimeMillis() % 200 < 100; // Blink every 200ms when invincible


        Stroke ringStroke = new BasicStroke(7.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND); // Stroke for player rings

        /** 3. BACK PART OF THE PLAYER */
        if (shouldBlink) g2.setComposite(transparent); // Apply transparency if invincible
        g2.setStroke(ringStroke);
        g2.setColor(new Color(0,140,75));
        g2.drawArc(X_START, MAX_HEIGHT - myPosition.getPosition(), OVAL_WIDTH, OVAL_HEIGHT, 90, 180); // Draw the back half of the player (green arc)

        /** 4. LINE */
        g2.setComposite(opaque);
        g2.setStroke(new BasicStroke(4)); // Stroke for the line
        g2.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white for the line
        for (int i = 0; i < myLine.size() - 1; i++) { // Draw line segments between consecutive points
            g2.drawLine(myLine.getPoint(i).x, myLine.getPoint(i).y,
                    myLine.getPoint(i + 1).x, myLine.getPoint(i + 1).y);
        }

        /** 5. BONUS ITEMS */
        ArrayList<Point> bonuses = myBonusItems.getBonuses();
        /* Draw each bonus item as a green square (or an image if available) centered on its position.
           The size of the bonus is defined by BONUS_SIZE, and we adjust the drawing coordinates
           to center the image/square on the bonus's position. */
        for(Point p : bonuses) {
            int drawX = p.x - (BONUS_SIZE / 2); // Center the bonus image/square on the bonus position
            int drawY = p.y - (BONUS_SIZE / 2); // Center the bonus image/square on the bonus position
            if (bonusImage != null) g2.drawImage(bonusImage, drawX, drawY, BONUS_SIZE, BONUS_SIZE, null); // Draw bonus image if available
            else { g2.setColor(Color.GREEN); g2.fillRect(drawX, drawY, BONUS_SIZE, BONUS_SIZE); } // Fallback: simple green square for bonus items
        }

        /** 6. FRONT PART OF THE PLAYER */
        if (shouldBlink) g2.setComposite(transparent); // Apply transparency if invincible
        g2.setStroke(ringStroke);
        g2.setColor(new Color(0,219,117));
        g2.drawArc(X_START, MAX_HEIGHT - myPosition.getPosition(), OVAL_WIDTH, OVAL_HEIGHT, 270, 180); // Draw the front half of the player (lighter green arc)
        g2.setComposite(opaque); // Reset to full opacity for subsequent drawing

        /** 7. HUD (Health and Score) */

        int currentLife = myCollision.getLifePoints(); // Get the current life points from the collision model

        /* Draw the health as a series of heart icons (or circles if images are not available).
           We loop through the maximum number of life points (MAX_PVs) and draw a filled heart for each current life point,
           and an empty heart for the remaining life points. The hearts are spaced evenly with a defined gap. */
        for (int i = 0; i < MAX_PVs; i++) {

            int x = 20 + (i * (HEART_SIZE + HEART_GAP)); // Calculate x position for each heart based on its index, size, and gap

            if (i < currentLife) { // Draw filled heart for each current life point
                if (heartImage != null) g2.drawImage(heartImage, x, 20, HEART_SIZE, HEART_SIZE, null); // Draw filled heart image if available
                else { g2.setColor(Color.RED); g2.fillOval(x, 20, HEART_SIZE, HEART_SIZE); } // Fallback: simple red circle for filled heart

            } else { // Draw empty heart for remaining life points (the ones lost)
                if (noHeartImage != null) g2.drawImage(noHeartImage, x, 20, HEART_SIZE, HEART_SIZE, null); // Draw empty heart image if available
                else { g2.setColor(Color.GRAY); g2.drawOval(x, 20, HEART_SIZE, HEART_SIZE); } // Fallback: simple gray circle for empty heart
            }
        }

        /* Draw the score in the top-right corner of the screen. We set a monospaced font for better alignment,
           and calculate the x position based on the width of the score text to ensure it is right-aligned with some padding. */
        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        String scoreText = "Score : " + myScore.getScore();
        int xScore = DISPLAY_WIDTH - g2.getFontMetrics().stringWidth(scoreText) - 20;
        g2.drawString(scoreText, xScore, 40);

        /** 8. GAME OVER AND RESTART MENU */
        if (GAME_OVER) {
            myMenu.drawBackground(g2, myScore.getScore()); // Draw the game over background and score on the menu
            if (!myMenu.getRestartButton().isVisible()) myMenu.showButtons(); // Show restart and quit buttons if they are not already visible

        } else {
            if (myMenu.getRestartButton().isVisible()) myMenu.hideButtons(); // Hide restart and quit buttons if the game is running

            if (!GAME_RUNNING) { /* If the game is not running (i.e., at the start screen), we display a welcome message and instructions.*/

                g2.setFont(new Font("Arial", Font.BOLD, 30));

                //Instructions text
                g2.setColor(new Color(255,255,255,150));
                String msg = "Click or Space to Start!";
                g2.drawString(msg, (DISPLAY_WIDTH - g2.getFontMetrics().stringWidth(msg)) / 2, DISPLAY_HEIGHT / 3);
                // Center the instructions text horizontally and position it in the upper third of the screen

                // Title text
                g2.setColor(new Color(221, 0, 255));
                String title = "Love Rendez-Vous!";
                g2.drawString(title, (DISPLAY_WIDTH - g2.getFontMetrics().stringWidth(title)) / 2, DISPLAY_HEIGHT / 4);
                // Center the title text horizontally and position it in the upper third of the screen
            }
        }
    }
}