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

            if (GAME_RUNNING) {
                myLine.MaJ();
                myBonusItems.update(); // Les bonus bougent avec la ligne
            }
            try {
                Thread.sleep(DELAY_20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}