package Model;

import static Model.Constants.*;

public class Position {

    private double height = START_HEIGHT;
    private double currentSpeed = 0;

    public int getPosition(){
        return (int) height;
    }

    // Remise à zéro
    public void reset() {
        height = START_HEIGHT;
        currentSpeed = 0;
    }

    public void jump(){
        currentSpeed = JUMP_STRENGTH;
    }

    public void applyPhysics(){
        height += currentSpeed;
        currentSpeed -= GRAVITY;

        if (height > MAX_HEIGHT) {
            height = MAX_HEIGHT;
            currentSpeed = 0;
        } else if (height < MIN_HEIGHT) {
            height = MIN_HEIGHT;
            currentSpeed = 0;
        }
    }
}