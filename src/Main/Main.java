package Main;

import Control.*;
import Model.*;
import View.*;

import javax.swing.*;

/** La classe principale de ce projet */
public class Main {
    /** La méthode de lancement du programme */
    public static void main(String [] args) {
        JFrame myWindow = new JFrame("Exercice 1");
        Position p = new Position();
        Line l = new Line();
        Display d = new Display(p,l);
        myWindow.add(d);
        new Refresh(d);
        new LineMove(l);
        new Gravity(p);
        ClickResponse r = new ClickResponse(d,p);
        myWindow.pack();
        myWindow.setVisible(true);
    }
}