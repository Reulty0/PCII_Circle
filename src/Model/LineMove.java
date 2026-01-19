package Model;

public class LineMove {
    public static final int LINE_SPEED = 5;
    private Line myLine;

    public LineMove(Line l){
        myLine = l;
        new Thread(() -> {
            while (true){
                myLine.MaJ();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
