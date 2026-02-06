package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import static Model.Constants.*;

public class BonusItems {

    // Liste des points où se trouvent les bonus
    private ArrayList<Point> activeBonuses;
    private Random rand;

    public BonusItems() {
        activeBonuses = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Tente de faire apparaitre un bonus aux coordonnées données
     * Appelé par Line.java quand un nouveau point est créé
     */
    public void trySpawn(int x, int y) {
        // Tirage au sort (Rare)
        if (rand.nextInt(100) < PROB_BONUS) {
            activeBonuses.add(new Point(x, y));
        }
    }

    /**
     * Déplace les bonus vers la gauche (synchronisé avec la ligne)
     */
    public void update() {
        Iterator<Point> it = activeBonuses.iterator();
        while (it.hasNext()) {
            Point p = it.next();
            p.x -= 3; // Même vitesse que la ligne !

            // Suppression si hors écran
            if (p.x < -50) {
                it.remove();
            }
        }
    }

    public void reset() {
        activeBonuses.clear();
    }

    public ArrayList<Point> getBonuses() {
        return activeBonuses;
    }

    /**
     * Supprime un bonus spécifique (quand il est ramassé)
     */
    public void removeBonus(Point p) {
        activeBonuses.remove(p);
    }
}