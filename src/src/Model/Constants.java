package Model;

public final class Constants {

    /** --- GAME STATES --- */
    public static boolean GAME_RUNNING = false;
    public static boolean GAME_OVER = false;
    public static final int MAX_PVs = 3; // Nombre de vies

    private Constants() {}

    /** --- DISPLAY --- */
    public static final int DISPLAY_HEIGHT = 377;
    public static final int DISPLAY_WIDTH = 610;

    public static final int OVAL_HEIGHT = 100;
    public static final int OVAL_WIDTH = 50;

    /** Starting X position of the player */
    public static final int X_START = (DISPLAY_WIDTH - OVAL_WIDTH) / 3;

    /** --- MENU BUTTONS --- */
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_X = (DISPLAY_WIDTH - BUTTON_WIDTH) / 2;
    public static final int RESTART_BTN_Y = DISPLAY_HEIGHT / 2;
    public static final int QUIT_BTN_Y = RESTART_BTN_Y + 70;

    /** --- POSITIONS & PHYSICS --- */
    public static final int START_HEIGHT = DISPLAY_HEIGHT - ((DISPLAY_HEIGHT / 2) - (OVAL_HEIGHT / 2));
    public static final int MAX_HEIGHT = 377;
    public static final int MIN_HEIGHT = 50;

    public static final double JUMP_STRENGTH = 6.0;
    public static final double GRAVITY = 0.4;

    public static final int TOLERANCE = 8;

    /** --- TIMERS --- */
    public static final int REFRESH_DELAY = 50;
    public static final int DELAY_20 = 20;

    /** --- LINE --- */
    public static final int MAX_DELTA_HEIGHT = 50;
    public static final int MIN_LINE_HEIGHT = 50;
    public static final int MAX_LINE_HEIGHT = 300;
    public static final int DELTA_X = 70;
}