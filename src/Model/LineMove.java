package Model;

import static Model.Constants.*;

public class LineMove extends Thread {
    private Line myLine;

    public LineMove(Line l){
        myLine = l;
        this.start();
    }

    @Override
    public void run(){
        while (true){

            if (GAME_RUNNING) {
                myLine.MaJ();
            }
            try {
                Thread.sleep(DELAY_20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}