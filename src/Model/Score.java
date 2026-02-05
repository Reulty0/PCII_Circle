package Model;

public class Score extends Thread {
    private int score = 0;

    public Score() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            if (Constants.GAME_RUNNING) {
                score++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getScore() {
        return score;
    }

}
