package Model;

import static Model.Constants.*;

public class Gravity extends Thread {
    private Position myPosition;

    public Gravity(Position p){
        myPosition = p;
        this.start();
    }

    @Override
    public void run(){
        while (true){

            if (GAME_RUNNING) {
                myPosition.applyPhysics();
            }

            try {
                Thread.sleep(DELAY_20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}