package Model;

public final class Constants {

    /** --- GAME --- */
    public static boolean GAME_RUNNING = false;
    public static final int MAX_PVs = 5; // Player life points (hearts)

    private Constants() {}

    /** --- DISPLAY --- */
    public static final int DISPLAY_HEIGHT = 377;
    public static final int DISPLAY_WIDTH = 610;

    public static final int OVAL_HEIGHT = 100;
    public static final int OVAL_WIDTH = 50;

   /** Starting X position of the player */
    public static final int X_START = (DISPLAY_WIDTH - OVAL_WIDTH) / 3;

    /** --- POSITIONS & PHYSICS --- */

    public static final int START_HEIGHT = DISPLAY_HEIGHT - ((DISPLAY_HEIGHT / 2) - (OVAL_HEIGHT / 2));

    public static final int MAX_HEIGHT = 377; // Physical ceiling (top of the screen)
    public static final int MIN_HEIGHT = OVAL_HEIGHT;  // Physical floor (bottom of the screen)

    public static final double JUMP_STRENGTH = 3.0; // Jump impulse strength (initial upward speed)
    public static final double GRAVITY = 0.2;       // Gravity strength (acceleration downwards)

    public static final int TOLERANCE = 8; // Tolerance for collision detection (to avoid pixel-perfect collisions)

    /** --- TIMERS --- */
    public static final int REFRESH_DELAY = 50; // For the display (20 FPS)
    public static final int DELAY_20 = 20;      // For the Physics (50 FPS)

    /** --- LINE --- */
    public static final int MAX_DELTA_HEIGHT = 50;
    public static final int MIN_LINE_HEIGHT = 50;
    public static final int MAX_LINE_HEIGHT = 300;
    public static final int DELTA_X = 70; // Gap between line points
}