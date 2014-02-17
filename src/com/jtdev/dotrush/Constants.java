package com.jtdev.dotrush;

import com.badlogic.gdx.Gdx;

public class Constants {
    /* screen */
    public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /* splash screen */
    public static int SPLASH_SCREEN_TIME = 2000;
    public static String SPLASH_IMAGE_PATH = "img/splashscreen.png";

    public static float SPLASH_WIDTH = 1820;
    public static float SPLASH_HEIGHT = 1024;
    public static float SPLASH_RATIO = SPLASH_HEIGHT / SPLASH_WIDTH;

    /* enemies */
    public static int ENEMY_NUMBER = 100;
    public static float ENEMY_MIN_VELOCITY = 40;
    public static float ENEMY_MAX_VELOCITY = 100;

    public static float ENEMY_DMIN_RADIUS = 45; /* 45 less than player */
    public static float ENEMY_DMAX_RADIUS = 35; /* 35 more than player */
    public static float ENEMY_MIN_RADIUS = 10;

    /* player */
    public static float PLAYER_MIN_RADIUS = 25;

    /* debugging */
    public static boolean DEBUG_LOG = true;
}
