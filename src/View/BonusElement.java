package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import static Model.Constants.*;

public class BonusElement extends Thread {

    private ArrayList<Star> stars;
    private ArrayList<SpaceObject> spaceObjects;

    // Visual assets for bonus elements
    private Image starImg;
    private Image cometImg;


    private Random rand = new Random();

    public BonusElement() {
        try {
            // Charge all images at once, and handle exceptions if any are missing
            starImg = new ImageIcon("media/star.png").getImage();
            cometImg = new ImageIcon("media/comet.png").getImage();
        } catch (Exception e) {
            System.out.println("Erreur images bonus : " + e.getMessage());
        }

        stars = new ArrayList<>();
        spaceObjects = new ArrayList<>();

        initStars();
        this.start();
    }

    /** Initialisation of stars with random positions and speeds */
    private void initStars() {
        stars.clear();
        for (int i = 0; i < 50; i++) { // Number of stars
            stars.add(new Star(rand.nextInt(DISPLAY_WIDTH), rand.nextInt(DISPLAY_HEIGHT)));
        }
    }

    // Method to reset all bonus elements
    public void reset() {
        spaceObjects.clear();
    }

    @Override
    public void run() {
        while (true) {
            if (GAME_RUNNING && !GAME_OVER) { // Only update if the game is active
                updateStars();
                updateSpaceObjects();
                spawnManager();
            }
            try {
                Thread.sleep(DELAY_20);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    /** Update the position of stars, and reset them to the right edge if they go off-screen */
    private void updateStars() {
        for (Star s : stars) {
            s.x -= s.speed;
            if (s.x < 0) {
                s.x = DISPLAY_WIDTH;
                s.y = rand.nextInt(DISPLAY_HEIGHT);
            }
        }
    }

    /** Update the position of space objects, and remove them if they go too far off-screen */
    private void updateSpaceObjects() {
        Iterator<SpaceObject> iter = spaceObjects.iterator(); // Use iterator to safely remove objects while iterating
        while (iter.hasNext()) { // Loop through space objects
            SpaceObject obj = iter.next();
            obj.x += obj.dx;
            obj.y += obj.dy;

            // Remove objects that are far off-screen to free up memory
            if (obj.x < -200 || obj.y > DISPLAY_HEIGHT + 200 || obj.y < -200) {
                iter.remove();
            }
        }
    }

    /** Spawn new space objects based on defined probabilities */
    private void spawnManager() {
        int r = rand.nextInt(COMET_RATIO); // Generate a random number to decide what to spawn

        if (r < PROB_COMET) {
            int startY = rand.nextInt(DISPLAY_HEIGHT / 2); // Start in the upper half of the screen for better visibility
            spaceObjects.add(new SpaceObject(DISPLAY_WIDTH, startY, COMET_SPEED_X, COMET_SPEED_Y, cometImg, 80, 40)); // Spawn a comet with fixed speed and size
        }
    }

    /** Draw all bonus elements (stars and space objects) on the screen
     * 1. Stars
     * 2. Comets
     **/
    public void draw(Graphics2D g2) {
        // 1. Stars
        for (Star s : stars) {
            if (starImg != null)
                g2.drawImage(starImg, s.x, s.y, STAR_SIZE, STAR_SIZE, null);
            else { // Fallback: draw a simple white dot if the star image is missing
                g2.setColor(Color.WHITE); g2.fillOval(s.x, s.y, 3, 3);
            }
        }

        // 2. Space Objects (Comets)
        AffineTransform oldTransform = g2.getTransform();

        for (SpaceObject obj : spaceObjects) {
            if (obj.img != null) {
                double centerX = obj.x + obj.width / 2.0;
                double centerY = obj.y + obj.height / 2.0;

                double angle = Math.atan2(obj.dy, obj.dx); // Calculate the angle of movement based on velocity
                angle += Math.PI; // Rotate 180 degrees to point the front of the comet in the direction of movement

                g2.rotate(angle, centerX, centerY);
                g2.drawImage(obj.img, (int)obj.x, (int)obj.y, obj.width, obj.height, null);
                g2.setTransform(oldTransform);

            } else { // Fallback: draw a simple red oval if the comet image is missing
                g2.setColor(Color.RED);
                g2.fillOval((int)obj.x, (int)obj.y, obj.width, obj.height);
            }
        }
    }

    // --- CLASSES INTERNES ---
    private class Star {
        int x, y, speed;
        public Star(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = rand.nextInt(STAR_SPEED_MAX - STAR_SPEED_MIN + 1) + STAR_SPEED_MIN;
        }
    }

    private class SpaceObject {
        double x, y, dx, dy;
        Image img;
        int width, height;
        // Suppression du boolean isComet

        public SpaceObject(double x, double y, double dx, double dy, Image img, int w, int h) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.img = img;
            this.width = w;
            this.height = h;
        }
    }
}