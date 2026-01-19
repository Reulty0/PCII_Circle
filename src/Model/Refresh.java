package Model;

import View.Display;

public class Refresh extends Thread {
    public static final int DELAY = 50;

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
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
