package Model;

import static Model.Constants.*;

public class LineMove extends Thread {
    private Line myLine;
    private BonusItems myBonusItems; // Nouveau

    public LineMove(Line l, BonusItems b){
        myLine = l;
        myBonusItems = b;
        this.start();
    }

    @Override
    public void run(){
        while (true){
            /** While the game is running, update the line and bonus items every 20ms */
            if (GAME_RUNNING) {
                myLine.MaJ(); // Update the line
                myBonusItems.update(); // Update the bonus items
            }
            try {
                Thread.sleep(DELAY_20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}