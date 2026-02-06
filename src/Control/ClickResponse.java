package Control;

import Model.*;
import View.BonusElement;
import View.Display;
import View.Menu;
import java.awt.event.*; // Import global pour KeyListener et MouseListener
import static Model.Constants.*;

// On ajoute "implements KeyListener"
public class ClickResponse implements MouseListener, KeyListener {

    private Display myDisplay;
    private Position myPosition;
    private Line myLine;
    private Score myScore;
    private Collision myCollision;
    private Menu myMenu;
    private BonusElement myBonus;

    public ClickResponse(Display d, Position p, Line l, Score s, Collision c, Menu m, BonusElement b){
        myDisplay = d;
        myPosition = p;
        myLine = l;
        myScore = s;
        myCollision = c;
        myMenu = m;
        myBonus = b;

        d.addMouseListener(this);
        d.addKeyListener(this); // AJOUT : On écoute le clavier sur le Display

        myMenu.getRestartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Restart cliqué !");
                restartGame();
                // IMPORTANT : On redonne le focus au jeu après avoir cliqué sur le bouton
                myDisplay.requestFocusInWindow();
            }
        });

        myMenu.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void restartGame() {
        myPosition.reset();
        myLine.reset();
        myScore.reset();
        myCollision.reset();
        myBonus.reset();

        GAME_OVER = false;
        GAME_RUNNING = false;
        myMenu.hideButtons();
    }

    // --- GESTION DU SAUT (Logique commune) ---
    private void performJump() {
        if (!GAME_OVER) {
            if (!GAME_RUNNING) {
                GAME_RUNNING = true;
                myPosition.jump();
            } else {
                myPosition.jump();
            }
        }
    }

    // --- SOURIS ---
    @Override
    public void mouseClicked(MouseEvent e){
        performJump();
    }
    // Méthode plus réactive que clicked (optionnel mais conseillé)
    @Override
    public void mousePressed(MouseEvent e){
        // Si tu veux que le clic soit instantané comme le clavier, décommente la ligne ci-dessous
        // performJump();
    }

    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}

    // --- CLAVIER (KeyListener) ---
    @Override
    public void keyPressed(KeyEvent e) {
        // Si on appuie sur ESPACE
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            performJump();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}