package View;

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
            myDisplay.repaint(); //Refresh the display
            try {
                Thread.sleep(REFRESH_DELAY); //Wait for the specified delay before refreshing again
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
