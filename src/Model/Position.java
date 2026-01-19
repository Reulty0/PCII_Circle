package Model;

public class Position {
    public static final int JUMP_HEIGHT = 10;
    public static final int START_HEIGHT = 150;
    public static final int MAX_HEIGHT = 377;
    public static final int MIN_HEIGHT = 100;
    private int height = START_HEIGHT;

    public int getPosition(){
        return height;
    }

    /** Jump the position up by JUMP_HEIGHT units, but not above MIN_HEIGHT */
    public void jump(){
        if (height+JUMP_HEIGHT <= MAX_HEIGHT) height +=JUMP_HEIGHT;
    }

    public void move(int delt){
        height+=delt;
    }
}
