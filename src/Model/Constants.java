package Model;

public final class Constants {

    /** --- GAME STATES --- */
    public static boolean GAME_RUNNING = false;
    public static boolean GAME_OVER = false;
    public static boolean IS_INVINCIBLE = false;
    public static final int MAX_PVs = 5;

    private Constants() {}

    /** --- DECOR / BONUS CONFIGURATION --- */
    // HP Configuration
    public static final int HP_RATIO = 100; // Type of % for the following probabilities
    public static final int PROB_BONUS = 10; // 10(Ratio) chance of spawning a bonus item on the line per new point generated
    public static final int BONUS_SIZE = 35;

    public static final int COMET_RATIO = 100; // Type of % for the following probabilities
    public static final int PROB_COMET = 1; // 1(Ratio) chance per frame to spawn a comet

    // Stars
    public static final int STAR_SIZE = 7;
    public static final int STAR_SPEED_MIN = 1;
    public static final int STAR_SPEED_MAX = 3;

    // Comet
    public static final double COMET_SPEED_X = -50.0;
    public static final double COMET_SPEED_Y = 2.0;

    /** --- DISPLAY --- */
    // Display dimensions
    public static final int DISPLAY_HEIGHT = 543;
    public static final int DISPLAY_WIDTH = 879;
    // Oval dimensions and position
    public static final int OVAL_HEIGHT = 120;
    public static final int OVAL_WIDTH = 70;
    public static final int X_START = (DISPLAY_WIDTH - OVAL_WIDTH) / 3;

    // Constants for drawing
    public static final int HEART_SIZE = 30;
    public static final int HEART_GAP = 5;

    /** --- MENU BUTTONS --- */
    // Button dimensions
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    // Button positions
    public static final int BUTTON_X = (DISPLAY_WIDTH - BUTTON_WIDTH) / 2; // Centered horizontally
    public static final int RESTART_BTN_Y = DISPLAY_HEIGHT / 2; // Restart button centered vertically
    public static final int QUIT_BTN_Y = RESTART_BTN_Y + 70; // Quit button below the restart button

    /** --- PHYSICS --- */
    // Height configuration for the oval (the circle)
    public static final int START_HEIGHT = DISPLAY_HEIGHT - ((DISPLAY_HEIGHT / 2) - (OVAL_HEIGHT / 2)); // Start at the middle of the screen
    public static final int MAX_HEIGHT = DISPLAY_HEIGHT; // Max the top of the screen
    public static final int MIN_HEIGHT = OVAL_HEIGHT; // Min the bottom of the screen (taking into account the oval height)
    // Jump and gravity configuration
    public static final double JUMP_STRENGTH = 6.0; // Initial upward velocity when jumping
    public static final double GRAVITY = 0.4; // Acceleration downwards applied each frame
    // Tolerance for collision detection (to make it more forgiving)
    public static final int TOLERANCE = 8;

    /** --- TIMERS --- */
    public static final int REFRESH_DELAY = 50;
    public static final int DELAY_20 = 20;

    /** --- LINE --- */
    public static final int MAX_DELTA_HEIGHT = 50; // Max vertical change between two consecutive points on the line
    public static final int MIN_LINE_HEIGHT = OVAL_HEIGHT/2; // Minimum height of the line (taking into account the oval height to avoid instant collision)
    public static final int MAX_LINE_HEIGHT = DISPLAY_HEIGHT - OVAL_HEIGHT/2; // Maximum height of the line (taking into account the oval height to avoid instant collision)
    public static final int DELTA_X = 70; // Horizontal distance between two consecutive points on the line
    public static final int LINE_SPEED = 3; // Speed at which the line moves to the left (in pixels per frame)
}