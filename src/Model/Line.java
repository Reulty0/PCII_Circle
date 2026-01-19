package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Line {
    private ArrayList<Point> points;
    public static final int MAX_DELTA_HEIGHT = 50;

    /** Constructor of Line, generates random points for the line */
    public Line(){
        points = new ArrayList<>();
        Random y = new Random();
        points.add(new Point(0, 150));
        for (int x = 10; x < 610; x+=30) points.add(new Point(x, (int) (points.get(x%10).getY() + y.nextInt(MAX_DELTA_HEIGHT))));
    }

    public Point getPoint(int i) {
        return points.get(i);
    }

    public void MaJ(){
        points.remove(0);
        Random y = new Random();
        int lastX = points.get(points.size()-1).x;
        int lastY = points.get(points.size()-1).y;
        points.add(new Point(lastX + 30, (int) (lastY + y.nextInt(MAX_DELTA_HEIGHT) - MAX_DELTA_HEIGHT/2)));

        for (Point p : points){
            p.x -= 30;
        }
    }

    public int size(){
        return points.size();
    }
}
