package Control;

import Model.Position;
import View.Display;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static Model.Constants.*;

public class ClickResponse implements MouseListener {

    private Display myDisplay;
    private Position myPosition;

    public ClickResponse(Display d, Position p){
        myDisplay = d;
        myPosition = p;
        d.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e){

        if (!GAME_RUNNING) {
            GAME_RUNNING = true;
        }

        myPosition.jump();
    }

    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
}