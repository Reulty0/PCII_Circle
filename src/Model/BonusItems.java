package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import static Model.Constants.*;

public class BonusItems {

    // List of active bonus items on the screen
    private ArrayList<Point> activeBonuses;
    private Random rand;

    public BonusItems() {
        activeBonuses = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Tries to spawn a bonus item at the given coordinates based on the defined probability.
     * @param x The x-coordinate where the bonus item should be spawned.
     * @param y The y-coordinate where the bonus item should be spawned
     */
    public void trySpawn(int x, int y) {

        if (rand.nextInt(HP_RATIO) < PROB_BONUS) { //If the random number is less than the probability, spawn a bonus
            activeBonuses.add(new Point(x, y));
        }
    }

    /**
     * Moves all active bonus items to the left and removes those that go off-screen.
     * This should be called in the game loop to ensure bonus items move in sync with the line.
     */
    public void update() {
        Iterator<Point> it = activeBonuses.iterator();
        while (it.hasNext()) {
            Point p = it.next(); // Get the current bonus item
            p.x -= LINE_SPEED; // Move the bonus item to the left by the speed of the line

            // Remove the bonus item if it goes off-screen (x < -50)
            if (p.x < -50) {
                it.remove();
            }
        }
    }
    /** Resets the bonus items by clearing the list of active bonuses. This can be called when restarting the game or when the player loses a life.*/
    public void reset() {
        activeBonuses.clear();
    }

    public ArrayList<Point> getBonuses() {
        return activeBonuses;
    }

    /**
     * Removes a bonus item from the active list when collected by the player.
     * @param p The point representing the bonus item to be removed.
     */
    public void removeBonus(Point p) {
        activeBonuses.remove(p);
    }
}