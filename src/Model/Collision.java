package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList; // Import nécessaire
import static Model.Constants.*;

public class Collision extends Thread {
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private BonusItems myBonusItems;
    private int lifePoints; // Number of HP the player has


    public Collision(Position p, Line l, Score s, BonusItems b) {
        myPosition = p;
        myLine = l;
        myScore = s;
        myBonusItems = b;
        lifePoints = MAX_PVs;
        this.start();
    }

    public int getLifePoints() { return lifePoints; }

    /**  Reset the player's life points to the maximum and remove invincibility. Called when restarting the game.  */
    public void reset() {
        lifePoints = MAX_PVs;
        IS_INVINCIBLE = false;
    }

    @Override
    public void run() {
        while (true) {
            if (GAME_RUNNING && !GAME_OVER) {
                /** Handle game logic in this order:
                   1. Check if player is dead (HP <= 0)
                   2. Check if player is out of line (and not invincible)
                   3. Check for bonus collisions
                */
                if (lifePoints <= 0) { // 1. Game Over condition
                    GAME_RUNNING = false;
                    GAME_OVER = true;
                    //System.out.println("GAME OVER");
                }
                // 2. Check line collision only if not invincible and score is above 45 (to give players a grace period at the start)
                else if (!IS_INVINCIBLE && isTouchingLine() && myScore.getScore() > 45) {
                    lifePoints--;
                    //System.out.println("Out Of Line ! HP : " + lifePoints);
                    activateInvincibility(); // Give the player 1 second of invincibility after taking damage to prevent instant death from multiple collisions
                }

                // 3. Bonus collision check (can happen even if invincible, since it's a reward)
                checkBonusCollision();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    /** Checks if the player's oval collides with any bonus item. If so, increases HP (if not full) and removes the bonus. */
    private void checkBonusCollision() {
        // Player's hitbox as a rectangle (approximating the oval)
        int pX = X_START;
        int pY = MAX_HEIGHT - myPosition.getPosition();
        Rectangle playerRect = new Rectangle(pX, pY, OVAL_WIDTH, OVAL_HEIGHT);

        // Make a copy of the bonuses list to avoid ConcurrentModificationException if we remove a bonus while iterating
        ArrayList<Point> bonuses = new ArrayList<>(myBonusItems.getBonuses());

        for (Point bonusPt : bonuses) {
            // Bonus hitbox as a rectangle
            int bX = bonusPt.x - (BONUS_SIZE / 2);
            int bY = bonusPt.y - (BONUS_SIZE / 2);
            Rectangle bonusRect = new Rectangle(bX, bY, BONUS_SIZE, BONUS_SIZE);

            if (playerRect.intersects(bonusRect)) {
                // Intersection detected, player gets the bonus

                // Heals the player by 1 HP if not already at max
                if (lifePoints < MAX_PVs) {
                    lifePoints++;
                    //System.out.println("BONUS ! +1 HP : " + lifePoints);
                }

                // Disable the bonus so it can't be collected again
                myBonusItems.removeBonus(bonusPt);
                break; // Assuming one bonus can be collected at a time
            }
        }
    }

    private boolean isTouchingLine() {
        /** To determine if the player is out of line, we need to:
            1. Find the segment of the line directly under the player's horizontal position.
            2. Calculate the Y value of that line segment at the player's X position.
            3. Check if that Y value is within a certain tolerance of the player's oval (since the player can be slightly above or below the line and still be considered "on" it).
        */
        int playerCenterX = X_START + (OVAL_WIDTH / 2); // The player's horizontal center position
        int ovalTopY = MAX_HEIGHT - myPosition.getPosition(); // The Y coordinate of the top of the player's oval
        int ovalBottomY = ovalTopY + OVAL_HEIGHT; // The Y coordinate of the bottom of the player's oval
        int safeCeiling = ovalTopY + TOLERANCE; // The highest Y value of the line that would still be considered "touching" the player
        int safeFloor = ovalBottomY - TOLERANCE; // The lowest Y value of the line that would still be considered "touching" the player

        Point p1 = null; Point p2 = null; // These will be the endpoints of the line segment directly under the player
        try {
            /** We loop through the line's points to find the segment that spans the player's horizontal position.
                We assume the line points are ordered from left to right. If the player's center X is between two points, we found our segment.
            */
            for (int i = 0; i < myLine.size() - 1; i++) {
                Point a = myLine.getPoint(i);
                Point b = myLine.getPoint(i + 1);
                if (a.x <= playerCenterX && b.x >= playerCenterX) {
                    p1 = a; p2 = b; break;
                }
            }
        } catch (Exception e) { return false; }

        if (p1 == null || p2 == null) return false;

        double slope = (double)(p2.y - p1.y) / (p2.x - p1.x); // Calculate the Y value of the line at the player's center X position using the point-slope form of a line
        double lineYAtPlayerX = p1.y + slope * (playerCenterX - p1.x); // This is the Y coordinate of the line directly under the player's center X position

        if (lineYAtPlayerX < safeCeiling || lineYAtPlayerX > safeFloor) return true; // If the line's Y value at the player's X position is outside the safe range defined by the player's oval and tolerance, then the player is considered out of line
        return false;
    }
    /** When the player takes damage from being out of line, we activate a brief period of invincibility to prevent multiple rapid hits.*/
    private void activateInvincibility() {
        new Thread(() -> {
            IS_INVINCIBLE = true; // Set invincibility flag
            try { Thread.sleep(1000); } catch (InterruptedException e) {} // Wait for 1 second (1000 milliseconds)
            IS_INVINCIBLE = false; // Remove invincibility after the duration
        }).start();
    }
}