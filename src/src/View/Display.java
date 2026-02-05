package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import static Model.Constants.*;

public class Display extends JPanel {
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private View.Menu myMenu;

    public Display(Position p, Line l, Score s, View.Menu m){
        setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        myPosition = p;
        myLine = l;
        myScore = s;
        myMenu = m;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. DESSIN DU JEU (Fond)
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(7));
        g2.setColor(Color.ORANGE);
        g2.drawOval(X_START, MAX_HEIGHT - myPosition.getPosition(), OVAL_WIDTH, OVAL_HEIGHT);

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        for (int i = 0; i < myLine.size() - 1; i++) {
            g2.drawLine(myLine.getPoint(i).x, myLine.getPoint(i).y,
                    myLine.getPoint(i + 1).x, myLine.getPoint(i + 1).y);
        }

        // 2. SCORE EN JEU
        g2.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2.setColor(Color.DARK_GRAY);
        String scoreText = "Score : " + myScore.getScore();
        int xScore = DISPLAY_WIDTH - g2.getFontMetrics().stringWidth(scoreText) - 20;
        g2.drawString(scoreText, xScore, 30);

        // 3. ECRANS SPECIFIQUES
        if (GAME_OVER) {
            // On délègue le dessin au Menu
            myMenu.draw(g2, myScore.getScore());
        }
        else if (!GAME_RUNNING) {
            // Ecran titre simple
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.setColor(Color.BLACK);
            String msg = "Click to Start!";
            int msgWidth = g2.getFontMetrics().stringWidth(msg);
            g2.drawString(msg, (DISPLAY_WIDTH - msgWidth) / 2, DISPLAY_HEIGHT / 2 - 60);
        }
    }
}