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

    // Images
    private Image bgImage;
    private Image heartImage;
    private Image noHeartImage;
    private Image bonusImage;

    private final int HEART_SIZE = 30;
    private final int HEART_GAP = 5;

    public Display(Position p, Line l, Score s, Menu m, Collision c, BonusElement b, BonusItems bi){
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        setLayout(null);

        // --- AJOUT IMPORTANT POUR LE CLAVIER ---
        this.setFocusable(true); // Permet au panel de recevoir les touches clavier
        this.requestFocusInWindow(); // Demande le focus dès le lancement
        // ---------------------------------------

        myPosition = p;
        myLine = l;
        myScore = s;
        myMenu = m;
        myCollision = c;
        myBonusElement = b;
        myBonusItems = bi;

        try {
            bgImage = new ImageIcon("media/background.png").getImage();
            heartImage = new ImageIcon("media/heart.png").getImage();
            noHeartImage = new ImageIcon("media/no_heart.png").getImage();
            bonusImage = new ImageIcon("media/bonus.png").getImage();
        } catch (Exception e) {}

        this.add(myMenu.getRestartButton());
        this.add(myMenu.getQuitButton());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. FOND
        if (bgImage != null) g2.drawImage(bgImage, 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null);
        else { g2.setColor(Color.LIGHT_GRAY); g2.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT); }

        // 2. DECOR
        myBonusElement.draw(g2);

        // CLIGNOTEMENT
        AlphaComposite transparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        boolean shouldBlink = IS_INVINCIBLE && System.currentTimeMillis() % 200 < 100;
        Stroke ringStroke = new BasicStroke(7.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        // 3. JOUEUR ARRIERE
        if (shouldBlink) g2.setComposite(transparent);
        g2.setStroke(ringStroke);
        g2.setColor(new Color(0,140,75));
        g2.drawArc(X_START, MAX_HEIGHT - myPosition.getPosition(), OVAL_WIDTH, OVAL_HEIGHT, 90, 180);

        // 4. LIGNE
        g2.setComposite(opaque);
        g2.setStroke(new BasicStroke(3));
        // Petite correction couleur (noire) ou transparence comme tu veux
        g2.setColor(new Color(255, 255, 255, 150));
        for (int i = 0; i < myLine.size() - 1; i++) {
            g2.drawLine(myLine.getPoint(i).x, myLine.getPoint(i).y,
                    myLine.getPoint(i + 1).x, myLine.getPoint(i + 1).y);
        }

        // 5. BONUS
        ArrayList<Point> bonuses = myBonusItems.getBonuses();
        for(Point p : bonuses) {
            int drawX = p.x - (BONUS_SIZE / 2);
            int drawY = p.y - (BONUS_SIZE / 2);
            if (bonusImage != null) g2.drawImage(bonusImage, drawX, drawY, BONUS_SIZE, BONUS_SIZE, null);
            else { g2.setColor(Color.GREEN); g2.fillRect(drawX, drawY, BONUS_SIZE, BONUS_SIZE); }
        }

        // 6. JOUEUR AVANT
        if (shouldBlink) g2.setComposite(transparent);
        g2.setStroke(ringStroke);
        g2.setColor(new Color(0,219,117));
        g2.drawArc(X_START, MAX_HEIGHT - myPosition.getPosition(), OVAL_WIDTH, OVAL_HEIGHT, 270, 180);
        g2.setComposite(opaque);

        // 7. HUD
        int currentLife = myCollision.getLifePoints();
        for (int i = 0; i < MAX_PVs; i++) {
            int x = 20 + (i * (HEART_SIZE + HEART_GAP));
            if (i < currentLife) {
                if (heartImage != null) g2.drawImage(heartImage, x, 20, HEART_SIZE, HEART_SIZE, null);
                else { g2.setColor(Color.RED); g2.fillOval(x, 20, HEART_SIZE, HEART_SIZE); }
            } else {
                if (noHeartImage != null) g2.drawImage(noHeartImage, x, 20, HEART_SIZE, HEART_SIZE, null);
                else { g2.setColor(Color.GRAY); g2.drawOval(x, 20, HEART_SIZE, HEART_SIZE); }
            }
        }

        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2.setColor(Color.WHITE);
        String scoreText = "Score : " + myScore.getScore();
        int xScore = DISPLAY_WIDTH - g2.getFontMetrics().stringWidth(scoreText) - 20;
        g2.drawString(scoreText, xScore, 40);

        // 8. MENUS
        if (GAME_OVER) {
            myMenu.drawBackground(g2, myScore.getScore());
            if (!myMenu.getRestartButton().isVisible()) myMenu.showButtons();
        } else {
            if (myMenu.getRestartButton().isVisible()) myMenu.hideButtons();
            if (!GAME_RUNNING) {
                g2.setFont(new Font("Arial", Font.BOLD, 30));
                g2.setColor(Color.WHITE);
                String msg = "Click or Space to Start!"; // Petit update texte
                g2.drawString(msg, (DISPLAY_WIDTH - g2.getFontMetrics().stringWidth(msg)) / 2, DISPLAY_HEIGHT / 2 - 60);
            }
        }
    }
}