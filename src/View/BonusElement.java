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

    private Image starImg;
    private Image cometImg;
    private Image meteorImg;

    private Random rand = new Random();

    public BonusElement() {
        try {
            starImg = new ImageIcon("media/star.png").getImage();
            cometImg = new ImageIcon("media/comet.png").getImage();
            meteorImg = new ImageIcon("media/meteor.gif").getImage();
        } catch (Exception e) {
            System.out.println("Erreur images bonus : " + e.getMessage());
        }

        stars = new ArrayList<>();
        spaceObjects = new ArrayList<>();

        initStars();
        this.start();
    }

    private void initStars() {
        stars.clear();
        for (int i = 0; i < 50; i++) {
            stars.add(new Star(rand.nextInt(DISPLAY_WIDTH), rand.nextInt(DISPLAY_HEIGHT)));
        }
    }

    public void reset() {
        spaceObjects.clear();
    }

    @Override
    public void run() {
        while (true) {
            if (GAME_RUNNING && !GAME_OVER) {
                updateStars();
                updateSpaceObjects();
                spawnManager();
            }
            try {
                Thread.sleep(DELAY_20);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    private void updateStars() {
        for (Star s : stars) {
            s.x -= s.speed;
            if (s.x < 0) {
                s.x = DISPLAY_WIDTH;
                s.y = rand.nextInt(DISPLAY_HEIGHT);
            }
        }
    }

    private void updateSpaceObjects() {
        Iterator<SpaceObject> iter = spaceObjects.iterator();
        while (iter.hasNext()) {
            SpaceObject obj = iter.next();
            obj.x += obj.dx;
            obj.y += obj.dy;

            if (obj.x < -200 || obj.y > DISPLAY_HEIGHT + 200 || obj.y < -200) {
                iter.remove();
            }
        }
    }

    private void spawnManager() {
        int r = rand.nextInt(100);

        if (r < PROB_COMET) {
            int startY = rand.nextInt(DISPLAY_HEIGHT / 2);
            spaceObjects.add(new SpaceObject(DISPLAY_WIDTH, startY, COMET_SPEED_X, COMET_SPEED_Y, cometImg, 80, 40, true));
        }
        else if (r < PROB_METEOR) {
            int startY = rand.nextInt(DISPLAY_HEIGHT);
            int dy = rand.nextInt(6) - 3;
            spaceObjects.add(new SpaceObject(DISPLAY_WIDTH, startY, METEOR_SPEED_X, dy, meteorImg, 50, 50, false));
        }
    }

    public void draw(Graphics2D g2) {
        // 1. Etoiles
        for (Star s : stars) {
            if (starImg != null)
                g2.drawImage(starImg, s.x, s.y, STAR_SIZE, STAR_SIZE, null);
            else {
                g2.setColor(Color.WHITE); g2.fillOval(s.x, s.y, 3, 3);
            }
        }

        // 2. Objets spatiaux (AVEC CORRECTION ROTATION)
        AffineTransform oldTransform = g2.getTransform();

        for (SpaceObject obj : spaceObjects) {
            if (obj.img != null) {
                double centerX = obj.x + obj.width / 2.0;
                double centerY = obj.y + obj.height / 2.0;
                double angle = Math.atan2(obj.dy, obj.dx);

                // --- CORRECTION DES ANGLES ---
                if (obj.isComet) {
                    // La comète allait en marche arrière -> On ajoute 180° (PI)
                    angle += Math.PI;
                } else {
                    // Le météore pointe vers le bas -> On retire 90° (PI/2)
                    angle -= Math.PI / 2.0;
                }
                // -----------------------------

                g2.rotate(angle, centerX, centerY);
                g2.drawImage(obj.img, (int)obj.x, (int)obj.y, obj.width, obj.height, null);
                g2.setTransform(oldTransform);

            } else {
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
        boolean isComet;

        public SpaceObject(double x, double y, double dx, double dy, Image img, int w, int h, boolean isComet) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.img = img;
            this.width = w;
            this.height = h;
            this.isComet = isComet;
        }
    }
}