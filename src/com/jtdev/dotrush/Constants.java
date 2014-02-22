package com.jtdev.dotrush;

import com.badlogic.gdx.Gdx;

public class Constants {
    /* screen */
    public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /* music */
    public static String GAME_MUSIC_PATH = "audio/soundtrack-loop.ogg";
    public static float GAME_MUSIC_VOLUME = 0.05f;

    /* font */
    public static String GAME_FONT_PATH = "fonts/comfortaa.fnt";
    public static float GAME_FONT_SCALE = -0.25f;

    /* score */
    public static int SCORE_Y = SCREEN_HEIGHT - 10;
    public static int SCORE_X = 10;

    /* splash screen */
    public static int SPLASH_SCREEN_TIME = 2000;
    public static String SPLASH_IMAGE_PATH = "images/splashscreen.png";

    public static float SPLASH_WIDTH = 1820;
    public static float SPLASH_HEIGHT = 1024;
    public static float SPLASH_RATIO = SPLASH_HEIGHT / SPLASH_WIDTH;

    /* enemies */
    public static int ENEMY_NUMBER = 150;
    public static float ENEMY_MIN_VELOCITY = 80;
    public static float ENEMY_MAX_VELOCITY = 160;

    public static float ENEMY_DMIN_RADIUS = 45; /* 45 less than player */
    public static float ENEMY_DMAX_RADIUS = 35; /* 35 more than player */
    public static float ENEMY_MIN_RADIUS = 10;

    /* player */
    public static float PLAYER_MIN_RADIUS = 25;
    public static float PLAYER_TOUCH_RADIUS = 80;

    /* debugging */
    public static boolean DEBUG_LOG = true;
}
