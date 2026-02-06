package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList; // Import nécessaire
import static Model.Constants.*;

public class Collision extends Thread {
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private BonusItems myBonusItems; // Nouveau lien
    private int lifePoints;

    // Constructeur mis à jour
    public Collision(Position p, Line l, Score s, BonusItems b) {
        myPosition = p;
        myLine = l;
        myScore = s;
        myBonusItems = b;
        lifePoints = MAX_PVs;
        this.start();
    }

    public int getLifePoints() { return lifePoints; }

    public void reset() {
        lifePoints = MAX_PVs;
        IS_INVINCIBLE = false;
    }

    @Override
    public void run() {
        while (true) {
            if (GAME_RUNNING && !GAME_OVER) {
                // 1. Gestion Game Over
                if (lifePoints <= 0) {
                    GAME_RUNNING = false;
                    GAME_OVER = true;
                    System.out.println("GAME OVER");
                }
                // 2. Gestion Dégâts (Ligne)
                else if (!IS_INVINCIBLE && isTouchingLine() && myScore.getScore() > 45) {
                    lifePoints--;
                    System.out.println("TOUCHÉ ! Vies restantes : " + lifePoints);
                    activateInvincibility();
                }

                // 3. Gestion Bonus (Soin)
                checkBonusCollision();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    // --- NOUVELLE METHODE : Ramassage de Bonus ---
    private void checkBonusCollision() {
        // Rectangle du joueur (approximatif pour la collision bonus)
        int pX = X_START;
        int pY = MAX_HEIGHT - myPosition.getPosition();
        Rectangle playerRect = new Rectangle(pX, pY, OVAL_WIDTH, OVAL_HEIGHT);

        // On parcourt une copie de la liste pour éviter les erreurs de modification concurrente
        ArrayList<Point> bonuses = new ArrayList<>(myBonusItems.getBonuses());

        for (Point bonusPt : bonuses) {
            // Rectangle du bonus (centré sur le point)
            int bX = bonusPt.x - (BONUS_SIZE / 2);
            int bY = bonusPt.y - (BONUS_SIZE / 2);
            Rectangle bonusRect = new Rectangle(bX, bY, BONUS_SIZE, BONUS_SIZE);

            if (playerRect.intersects(bonusRect)) {
                // Collision détectée !

                // On soigne SEULEMENT si on n'est pas full vie
                if (lifePoints < MAX_PVs) {
                    lifePoints++;
                    System.out.println("BONUS ! Vie récupérée : " + lifePoints);
                }

                // Dans tous les cas, le bonus disparait si on le touche
                myBonusItems.removeBonus(bonusPt);
                break; // On ne prend qu'un bonus à la fois par frame
            }
        }
    }

    private boolean isTouchingLine() {
        // ... (Ton code existant pour la collision ligne, inchangé) ...
        int playerCenterX = X_START + (OVAL_WIDTH / 2);
        int ovalTopY = MAX_HEIGHT - myPosition.getPosition();
        int ovalBottomY = ovalTopY + OVAL_HEIGHT;
        int safeCeiling = ovalTopY + TOLERANCE;
        int safeFloor = ovalBottomY - TOLERANCE;

        Point p1 = null; Point p2 = null;
        try {
            for (int i = 0; i < myLine.size() - 1; i++) {
                Point a = myLine.getPoint(i);
                Point b = myLine.getPoint(i + 1);
                if (a.x <= playerCenterX && b.x >= playerCenterX) {
                    p1 = a; p2 = b; break;
                }
            }
        } catch (Exception e) { return false; }

        if (p1 == null || p2 == null) return false;

        double slope = (double)(p2.y - p1.y) / (p2.x - p1.x);
        double lineYAtPlayerX = p1.y + slope * (playerCenterX - p1.x);

        if (lineYAtPlayerX < safeCeiling || lineYAtPlayerX > safeFloor) return true;
        return false;
    }

    private void activateInvincibility() {
        new Thread(() -> {
            IS_INVINCIBLE = true;
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            IS_INVINCIBLE = false;
        }).start();
    }
}