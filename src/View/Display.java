package View;

import Model.Line;
import Model.Position;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    public Position myPosition;
    public Line myLine;
    /** Dimensions of the display */
    public static final int HEIGHT = 377;
    public static final int WIDTH = 610;
    /** Dimensions of the oval */
    public static final int OVAL_HEIGHT = 100;
    public static final int OVAL_WIDTH = 50;
    /** X coordinate of the oval */
    public static final int X_START = 188;

    public Display(Position p, Line l){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        myPosition = p;
        myLine = l;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawOval(X_START, Position.MAX_HEIGHT-myPosition.getPosition(), OVAL_WIDTH, OVAL_HEIGHT);
        for (int i = 0; i < myLine.size()-1; i++)
        g.drawLine(myLine.getPoint(i).x, myLine.getPoint(i).y, myLine.getPoint(i+1).x, myLine.getPoint(i+1).y);
    }
}
