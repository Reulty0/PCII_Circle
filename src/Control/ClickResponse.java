package Control;

import Model.Position;
import View.Display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickResponse implements MouseListener {

    private Display myDisplay;
    private Position myPosition;

    /** Constructor of ClickResponse
     * @param d the display to add the mouse listener to
     * @param p the position to modify on click
     */
    public ClickResponse(Display d, Position p){
        myDisplay = d;
        myPosition = p;
        d.addMouseListener(this);
    }

    @Override
    /** When the mouse is clicked, call a jump on the position */
    public void mouseClicked(MouseEvent e){
        myPosition.jump();
        System.out.println(myPosition.getPosition());
    }

    @Override
    public void mousePressed(MouseEvent e){
    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
