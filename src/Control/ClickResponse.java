package Control;

import Model.*;
import View.Display;
import View.Menu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static Model.Constants.*;

public class ClickResponse implements MouseListener {

    private Display myDisplay;
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private Collision myCollision;
    private Menu myMenu;

    public ClickResponse(Display d, Position p, Line l, Score s, Collision c, Menu m){
        myDisplay = d;
        myPosition = p;
        myLine = l;
        myScore = s;
        myCollision = c;
        myMenu = m;
        d.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        // CAS 1 : Game Over
        if (GAME_OVER) {
            if (myMenu.isRestartClicked(x, y)) {
                System.out.println("Restarting...");
                // Reset de tout le monde
                myPosition.reset();
                myLine.reset();
                myScore.reset();
                myCollision.reset();

                GAME_OVER = false;
                GAME_RUNNING = false;
            }
            else if (myMenu.isQuitClicked(x, y)) {
                System.exit(0);
            }
        }
        // CAS 2 : Début du jeu
        else if (!GAME_RUNNING) {
            GAME_RUNNING = true;
            myPosition.jump();
        }
        // CAS 3 : Jeu en cours
        else {
            myPosition.jump();
        }
    }

    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
}