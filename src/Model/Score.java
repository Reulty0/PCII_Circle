package Model;

public class Score extends Thread {
    private int score = 0;

    public Score() {
        this.start();
    }

    /** Put the score back to 0*/
    public void reset() {
        score = 0;
    }

    @Override
    public void run() {
        while (true) {
            if (Constants.GAME_RUNNING && !Constants.GAME_OVER) { // If the game is running and not over, increase the score
                score++;
                // System.out.println("Score: " + score); // Commenté pour éviter le lag
            }
            try {
                Thread.sleep(20); // Sleep for 20 milliseconds to control the score increment rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getScore() {
        return score;
    }
}