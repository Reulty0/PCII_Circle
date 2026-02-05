package Model;

public class Score extends Thread {
    private int score = 0;

    public Score() {
        this.start();
    }

    public void reset() {
        score = 0;
    }

    @Override
    public void run() {
        while (true) {
            if (Constants.GAME_RUNNING && !Constants.GAME_OVER) {
                score++;
                // System.out.println("Score: " + score); // Commenté pour éviter le lag
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getScore() {
        return score;
    }
}