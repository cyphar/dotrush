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

    /* score */
    public static String SCORE_TEXT = "Score: ";
    public static float SCORE_SCALE = 0.65f;
    public static int SCORE_Y = SCREEN_HEIGHT - 10;
    public static int SCORE_X = 10;

    /* splash screen */
    public static int SPLASH_SCREEN_TIME = 2000;
    public static String SPLASH_IMAGE_PATH = "images/splashscreen.png";

    public static float SPLASH_WIDTH = 1820;
    public static float SPLASH_HEIGHT = 1024;
    public static float SPLASH_RATIO = SPLASH_HEIGHT / SPLASH_WIDTH;

    /* start screen */
    public static String LOGO_IMAGE_PATH = "images/logo.png";
    public static float LOGO_IMAGE_WIDTH = 2460;
    public static float LOGO_IMAGE_HEIGHT = 550;

    public static float LOGO_OFFSET_X = 0;
    public static float LOGO_OFFSET_Y = SCREEN_WIDTH / 6;

    public static float LOGO_RATIO = (SCREEN_WIDTH / LOGO_IMAGE_WIDTH) * 0.55f;
    public static float LOGO_WIDTH = LOGO_IMAGE_WIDTH * LOGO_RATIO;
    public static float LOGO_HEIGHT = LOGO_IMAGE_HEIGHT * LOGO_RATIO;

    public static String MENU_TEXT = "You are the white dot.\nEat the smaller dots.\nAvoid the larger dots.\nTap to begin.";
    public static float MENU_TEXT_SCALE = 0.65f;
    public static int MENU_TEXT_WIDTH = SCREEN_WIDTH;
    public static float MENU_TEXT_OFFSET_X = 0;
    public static float MENU_TEXT_OFFSET_Y = -SCREEN_WIDTH / 12;

    public static String MENU_CREDITS = "Created by Cyphar\nMusic courtesy of radtunez.com";
    public static float MENU_CREDITS_SCALE = 0.5f;
    public static float MENU_CREDITS_X = 5;
    public static float MENU_CREDITS_Y = SCREEN_HEIGHT - 5;

    public static float MENU_ENEMY_DMIN_RADIUS = 105; /* 45 less than player */
    public static float MENU_ENEMY_DMAX_RADIUS = 75; /* 35 more than player */
    public static int MENU_ENEMY_NUMBER = 60;

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
