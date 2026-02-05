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
        Line l = new Line();
        Score s = new Score();

        // 2. Vue (On crée le Menu ici)
        Menu m = new Menu();
        Display d = new Display(p, l, s, m);

        // 3. Fenêtre
        myWindow.add(d);
        myWindow.pack();
        myWindow.setVisible(true);
        myWindow.setResizable(false);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 4. Moteurs
        new Refresh(d);
        new LineMove(l);
        new Gravity(p);
        Collision c = new Collision(p, l, s);

        // 5. Contrôleur (On lui passe le menu)
        new ClickResponse(d, p, l, s, c, m);
    }
}