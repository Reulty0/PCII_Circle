package Model;

import static Model.Constants.*;

public class Position {

    private double height = START_HEIGHT;
    private double currentSpeed = 0;

    public int getPosition(){
        return (int) height;
    }

    /** Resets the position to the starting height and speed. */
    public void reset() {
        height = START_HEIGHT;
        currentSpeed = 0;
    }

    /** Makes the player jump by setting the current speed to the jump strength. */
    public void jump(){
        currentSpeed = JUMP_STRENGTH;
    }

    /** Applies gravity to the current speed and updates the height accordingly. */
    public void applyPhysics(){
        height += currentSpeed;
        currentSpeed -= GRAVITY;

        /* Ensure the height does not exceed the maximum or minimum limits. If it does, reset the height to the limit and set the speed to zero. */
        if (height > MAX_HEIGHT) {
            height = MAX_HEIGHT; // Prevents the player from moving above the maximum height.
            currentSpeed = 0; // Resets the speed to zero to stop further upward movement.
        } else if (height < MIN_HEIGHT) { // Prevents the player from moving below the minimum height (ground level).
            height = MIN_HEIGHT; // Resets the height to the minimum height (ground level).
            currentSpeed = 0; // Resets the speed to zero to stop further downward movement.
        }
    }
}