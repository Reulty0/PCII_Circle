package Control;

import Model.*;
import View.BonusElement;
import View.Display;
import View.Menu;
import java.awt.event.*; // Import global pour KeyListener et MouseListener
import static Model.Constants.*;

// It implements both MouseListener and KeyListener to handle clicks and key presses in the same class
public class ClickResponse implements MouseListener, KeyListener {

    private Display myDisplay;
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private Collision myCollision;
    private Menu myMenu;
    private BonusElement myBonus;
    private BonusItems myBonusItems;


    public ClickResponse(Display d, Position p, Line l, Score s, Collision c, Menu m, BonusElement b, BonusItems bi) {
        myDisplay = d;
        myPosition = p;
        myLine = l;
        myScore = s;
        myCollision = c;
        myMenu = m;
        myBonus = b;
        myBonusItems = bi;


        d.addMouseListener(this); // It adds this class as a MouseListener to the Display
        d.addKeyListener(this); // It adds this class as a KeyListener to the Display

        // It adds ActionListeners to the Restart button in the Menu
        myMenu.getRestartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Restart clicked !");
                restartGame(); // It calls the restartGame method to reset the game state
                // /!\ Important : after (re)starting the game, we need to request focus on the Display to ensure it receives key events
                myDisplay.requestFocusInWindow();
            }
        });

        // It adds ActionListeners to the Quit button in the Menu
        myMenu.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    /** This method resets all the game elements to their initial state and hides the menu buttons. It is called when the Restart button is clicked. */
    private void restartGame() {
        myPosition.reset();
        myLine.reset();
        myScore.reset();
        myCollision.reset();
        myBonus.reset();
        myBonusItems.reset();

        GAME_OVER = false;
        GAME_RUNNING = false;
        myMenu.hideButtons();
    }

    /** Jump logic */
    private void performJump() {
        if (!GAME_OVER) { // We only allow jumping if the game is not over
            if (!GAME_RUNNING) { // If the game is not running, we start it on the first jump
                GAME_RUNNING = true;
                myPosition.jump();
            } else { // If the game is already running, we just perform the jump
                myPosition.jump();
            }
        }
    }

    /** --- Mouse (MouseListener) ---*/
    /*** When the mouse is clicked we perform a jump.*/
    @Override
    public void mouseClicked(MouseEvent e){
        performJump();
    }
    // More responsive if we perform the jump on mousePressed, but it can cause issues if the user clicks and holds the mouse button
    @Override
    public void mousePressed(MouseEvent e){
        // performJump();
    }

    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}

    /** --- KeyPad (KeyListener) ---*/
    @Override
    public void keyPressed(KeyEvent e) {
        // We check if the space bar is pressed to perform a jump
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            performJump();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}