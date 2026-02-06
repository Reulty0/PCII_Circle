package Main;

import Control.*;
import Model.*;
import View.*;
import View.Menu;
import javax.swing.*;

public class Main {
    public static void main(String [] args) {
        JFrame myWindow = new JFrame("Projet KetchApp Circle");

        // 1. Modèles
        Position p = new Position();
        BonusItems bi = new BonusItems(); // Création du gestionnaire de bonus
        Line l = new Line(bi); // On donne le gestionnaire à la ligne pour le spawn
        Score s = new Score();

        // 2. Moteurs
        // On donne "bi" à Collision pour le ramassage
        Collision c = new Collision(p, l, s, bi);
        BonusElement b = new BonusElement();

        // 3. Vue
        Menu m = new Menu();
        // On donne "bi" au Display pour le dessin
        Display d = new Display(p, l, s, m, c, b, bi);

        // 4. Fenêtre
        myWindow.add(d);
        myWindow.pack();
        myWindow.setVisible(true);
        myWindow.setResizable(false);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 5. Threads
        new Refresh(d);
        // On donne "bi" à LineMove pour le déplacement synchronisé
        new LineMove(l, bi);
        new Gravity(p);

        // 6. Contrôleur
        new ClickResponse(d, p, l, s, c, m, b);
    }
}