package Model;

public class Gravity extends Thread {
    public static final int DELAY = 100;
    public static final int GRAVITY_FORCE = 1;

    private Position myPosition;

    public Gravity(Position p){
        myPosition = p;
        this.start();
    }

    @Override
    public void run(){
        while (true){
            /** Move the position down by 1 unit if it is not at the maximum height */
            if (myPosition.getPosition() - GRAVITY_FORCE >= Position.MIN_HEIGHT) myPosition.move( -GRAVITY_FORCE);
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}