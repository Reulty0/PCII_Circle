package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static Model.Constants.*;

public class Line {
    private ArrayList<Point> points;
    private BonusItems myBonusItems;


    public Line(BonusItems b){
        this.myBonusItems = b;
        points = new ArrayList<>();
        reset(); // Create the initial line
    }

    /** (re)create the line with initial points */
    public void reset() {
        points.clear();
        int startY = DISPLAY_HEIGHT / 2; // Start in the middle of the screen
        points.add(new Point(0, startY)); // Initial point at the left edge

        /* Add points across the width of the display, with a fixed horizontal spacing (DELTA_X).
           The vertical position of these points will be adjusted later in the MaJ() method to create the line's shape.
        */
        for (int x = 10; x < DISPLAY_WIDTH + 2 * DELTA_X ; x += DELTA_X) {
            points.add(new Point(x, startY));
        }
    }


    public Point getPoint(int i) {
        return points.get(i);
    }

    /** Update the line's position by moving all points to the left.
     * If the first point goes off-screen, remove it and add a new point at the end of the line with a random vertical position.
     **/
    public void MaJ(){
        for (Point p : points) {
            p.x -= LINE_SPEED;
        }
        if (points.get(0).x < -DELTA_X) {
            AddPoint();
        }
    }

    /** Add a new point at the end of the line with a random vertical position, and remove the first point if it goes off-screen.
     */
    public void AddPoint(){
        points.remove(0); // Remove the first point as it goes off-screen
        Random rand = new Random();
        // Get the last point's position to determine the new point's position
        int lastX = points.get(points.size()-1).x;
        int lastY = points.get(points.size()-1).y;

        int newY = lastY + rand.nextInt(MAX_DELTA_HEIGHT) - MAX_DELTA_HEIGHT/2; // Randomly adjust the new point's vertical position based on the last point's position
        newY = Math.max(MIN_LINE_HEIGHT, Math.min(MAX_LINE_HEIGHT, newY)); // Ensure the new point's vertical position is within the defined bounds

        // Add the new point at the end of the line
        points.add(new Point(lastX + DELTA_X, newY));

        /** --- Bonus Items --- **/
        /** When a new point is added, we can also attempt to spawn a bonus item at the new point's position.
         * The BonusItems class will handle the logic of whether to actually spawn an item based on its own internal rules (e.g., random chance, cooldowns, etc.).
         **/
        if (myBonusItems != null) {
            myBonusItems.trySpawn(lastX + DELTA_X, newY);
        }
    }

    // Get the number of points currently in the line
    public int size(){
        return points.size();
    }
}