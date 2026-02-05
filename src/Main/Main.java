package Main;

import Control.*;
import Model.*;
import View.*;
import javax.swing.*;

public class Main {
    public static void main(String [] args) {
        JFrame myWindow = new JFrame("Projet KetchApp Circle");


        Position p = new Position();
        Line l = new Line();
        Score s = new Score();


        Display d = new Display(p,l,s);


        myWindow.add(d);
        myWindow.pack();
        myWindow.setVisible(true);
        myWindow.setResizable(false);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        new Refresh(d);
        new LineMove(l);
        new Gravity(p);
        new Collision(p, l);


        ClickResponse r = new ClickResponse(d,p);
    }
}