package Model;

public final class Constants {

    /** --- GAME STATES --- */
    public static boolean GAME_RUNNING = false;
    public static boolean GAME_OVER = false;
    public static boolean IS_INVINCIBLE = false;
    public static final int MAX_PVs = 5;

    private Constants() {}

    /** --- DECOR / BONUS CONFIGURATION --- */
    public static int PROB_METEOR = 1;
    public static int PROB_COMET = 1;

    // NOUVEAU : Configuration du Bonus PV
    public static final int PROB_BONUS = 10; // 5% de chance d'apparaître sur un point
    public static final int BONUS_SIZE = 35; // Taille de l'image bonus

    // ETOILES
    public static final int STAR_SIZE = 7;
    public static final int STAR_SPEED_MIN = 1;
    public static final int STAR_SPEED_MAX = 3;

    // COMETES & METEORES
    public static final double COMET_SPEED_X = -50.0;
    public static final double COMET_SPEED_Y = 2.0;
    public static final double METEOR_SPEED_X = -8.0;

    /** --- DISPLAY --- */
    public static final int DISPLAY_HEIGHT = 543;
    public static final int DISPLAY_WIDTH = 879;
    public static final int OVAL_HEIGHT = 120;
    public static final int OVAL_WIDTH = 70;
    public static final int X_START = (DISPLAY_WIDTH - OVAL_WIDTH) / 3;

    /** --- MENU BUTTONS --- */
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_X = (DISPLAY_WIDTH - BUTTON_WIDTH) / 2;
    public static final int RESTART_BTN_Y = DISPLAY_HEIGHT / 2;
    public static final int QUIT_BTN_Y = RESTART_BTN_Y + 70;

    /** --- PHYSICS --- */
    public static final int START_HEIGHT = DISPLAY_HEIGHT - ((DISPLAY_HEIGHT / 2) - (OVAL_HEIGHT / 2));
    public static final int MAX_HEIGHT = DISPLAY_HEIGHT;
    public static final int MIN_HEIGHT = OVAL_HEIGHT;
    public static final double JUMP_STRENGTH = 6.0;
    public static final double GRAVITY = 0.4;
    public static final int TOLERANCE = 8;

    /** --- TIMERS --- */
    public static final int REFRESH_DELAY = 50;
    public static final int DELAY_20 = 20;

    /** --- LINE --- */
    public static final int MAX_DELTA_HEIGHT = 50;
    public static final int MIN_LINE_HEIGHT = OVAL_HEIGHT/2;
    public static final int MAX_LINE_HEIGHT = DISPLAY_HEIGHT - OVAL_HEIGHT/2;
    public static final int DELTA_X = 70;
}