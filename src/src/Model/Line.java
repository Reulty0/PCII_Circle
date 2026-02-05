package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static Model.Constants.*;

public class Line {
    private ArrayList<Point> points;

    public Line(){
        // On utilise la méthode reset pour l'initialisation aussi, ça évite de dupliquer le code
        points = new ArrayList<>();
        reset();
    }

    public void reset() {
        points.clear();
        int startY = DISPLAY_HEIGHT / 2;
        points.add(new Point(0, startY));

        for (int x = 10; x < DISPLAY_WIDTH + 2 * DELTA_X ; x += DELTA_X) {
            if (x < DISPLAY_WIDTH / 2) {
                points.add(new Point(x, startY));
            } else {
                points.add(new Point(x, startY)); // Tout plat au début
            }
        }
    }

    public Point getPoint(int i) {
        return points.get(i);
    }

    public void MaJ(){
        for (Point p : points) {
            p.x -= 3;
        }
        if (points.get(0).x < -DELTA_X) {
            AddPoint();
        }
    }

    public void AddPoint(){
        points.remove(0);
        Random rand = new Random();
        int lastX = points.get(points.size()-1).x;
        int lastY = points.get(points.size()-1).y;

        int newY = lastY + rand.nextInt(MAX_DELTA_HEIGHT) - MAX_DELTA_HEIGHT/2;
        newY = Math.max(MIN_LINE_HEIGHT, Math.min(MAX_LINE_HEIGHT, newY));
        points.add(new Point(lastX + DELTA_X, newY));
    }

    public int size(){
        return points.size();
    }
}