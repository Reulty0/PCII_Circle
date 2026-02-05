package Model;

import java.awt.Point;
import static Model.Constants.*;

public class Collision extends Thread {
    private Position myPosition;
    private Line myLine;
    private int lifePoints;
    private boolean isInvincible = false;

    public Collision(Position p, Line l){
        myPosition = p;
        myLine = l;
        lifePoints = MAX_PVs;
        this.start();
    }

    @Override
    public void run() {
        while (lifePoints > 0) {

            if (GAME_RUNNING && !isInvincible && isTouching()) {
                lifePoints--;
                System.out.println("TOUCHÉ ! Vies restantes : " + lifePoints);
                activateInvincibility();
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("GAME OVER !!!");
        System.exit(0);
    }

    private boolean isTouching() {
        int playerCenterX = X_START + (OVAL_WIDTH / 2);


        int ovalTopY = MAX_HEIGHT - myPosition.getPosition();
        int ovalBottomY = ovalTopY + OVAL_HEIGHT;


        int safeCeiling = ovalTopY + TOLERANCE;
        int safeFloor = ovalBottomY - TOLERANCE;

        // Trouver les points de la ligne autour du joueur
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

        // Interpolation pour trouver la hauteur exacte de la ligne
        double slope = (double)(p2.y - p1.y) / (p2.x - p1.x);
        double lineYAtPlayerX = p1.y + slope * (playerCenterX - p1.x);

        // Si la ligne sort de la zone de sécurité (trop haut ou trop bas)
        if (lineYAtPlayerX < safeCeiling || lineYAtPlayerX > safeFloor) {
            return true;
        }

        return false;
    }

    private void activateInvincibility() {
        isInvincible = true;
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 1 seconde de pause
                isInvincible = false;
                System.out.println("Fin de l'invincibilité.");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }).start();
    }
}