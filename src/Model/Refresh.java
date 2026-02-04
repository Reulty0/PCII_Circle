package Model;

import View.Display;

import static Model.Constants.*;

public class Refresh extends Thread {

    private Display myDisplay;

    public Refresh(Display d){
        myDisplay = d;
        this.start();
    }

    @Override
    public void run(){
        while (true){
            myDisplay.repaint();
            try {
                Thread.sleep(REFRESH_DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
