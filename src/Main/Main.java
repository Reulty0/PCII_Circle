package Main;

import Control.*;
import Model.*;
import View.*;
import View.Menu;
import javax.swing.*;

public class Main {
    public static void main(String [] args) {
        JFrame myWindow = new JFrame("Projet KetchApp Circle");


        Position p = new Position(); // Circle and it's movement
        BonusItems bi = new BonusItems(); // Bonus items management
        Line l = new Line(bi); // Line management, needs "bi" to generate bonus on the line
        Score s = new Score(); // Score management

        Collision c = new Collision(p, l, s, bi); // Collision management
        BonusElement b = new BonusElement(); //Generate visual elements

        Menu m = new Menu(); //Restart and Quit Menu
        Display d = new Display(p, l, s, m, c, b, bi); // Display, it manages all the visual elements

        // Window configuration
        myWindow.add(d); // We add the Display to the JFrame, so we can see everything
        myWindow.pack(); // Adjust the window size to fit the Display
        myWindow.setVisible(true); // Make the window visible
        myWindow.setResizable(false); // Prevent resizing to maintain the aspect ratio
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the window is closed

        //Threads
        new Refresh(d); // Refresh the display
        new LineMove(l, bi); // Move the line and generate bonus items on it
        new Gravity(p); // Apply gravity to the circle

        new ClickResponse(d, p, l, s, c, m, b, bi); // Contol : Handle clicks for jumping and menu interactions
    }
}