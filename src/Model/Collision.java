package Model;

import java.awt.Point;
import static Model.Constants.*;

public class Collision extends Thread {
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private int lifePoints;
    private boolean isInvincible = false;

    public Collision(Position p, Line l, Score s) {
        myPosition = p;
        myLine = l;
        myScore = s;
        lifePoints = MAX_PVs;
        this.start();
    }

    public void reset() {
        lifePoints = MAX_PVs;
        isInvincible = false;
    }

    @Override
    public void run() {
        while (true) {

            if (GAME_RUNNING && !GAME_OVER) {

                if (lifePoints <= 0) {
                    GAME_RUNNING = false;
                    GAME_OVER = true;
                    System.out.println("GAME OVER");
                }
                else if (!isInvincible && isTouching() && myScore.getScore() > 45) {
                    lifePoints--;
                    System.out.println("TOUCHÉ ! Vies restantes : " + lifePoints);
                    activateInvincibility();
                }
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isTouching() {
        int playerCenterX = X_START + (OVAL_WIDTH / 2);
        int ovalTopY = MAX_HEIGHT - myPosition.getPosition();
        int ovalBottomY = ovalTopY + OVAL_HEIGHT;
        int safeCeiling = ovalTopY + TOLERANCE;
        int safeFloor = ovalBottomY - TOLERANCE;

        Point p1 = null;
        Point p2 = null;

        try {
            for (int i = 0; i < myLine.size() - 1; i++) {
                Point a = myLine.getPoint(i);
                Point b = myLine.getPoint(i + 1);
                if (a.x <= playerCenterX && b.x >= playerCenterX) {
                    p1 = a;
                    p2 = b;
                    break;
                }
            }
        } catch (Exception e) { return false; }

        if (p1 == null || p2 == null) return false;

        double slope = (double)(p2.y - p1.y) / (p2.x - p1.x);
        double lineYAtPlayerX = p1.y + slope * (playerCenterX - p1.x);

        if (lineYAtPlayerX < safeCeiling || lineYAtPlayerX > safeFloor) {
            return true;
        }
        return false;
    }

    private void activateInvincibility() {
        isInvincible = true;
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                isInvincible = false;
                System.out.println("Fin de l'invincibilité.");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }).start();
    }
}