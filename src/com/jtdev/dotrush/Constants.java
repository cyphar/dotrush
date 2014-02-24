package com.jtdev.dotrush;

import com.badlogic.gdx.Gdx;

public class Constants {
    /* screen */
    public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    /* music */
    public static String GAME_MUSIC_PATH = "audio/soundtrack-loop.ogg";
    public static float GAME_MUSIC_VOLUME = 0.25f;

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

    /* buttons */
    public static String BUTTON_IMAGE_PATH = "images/buttons.png";
    public static int BUTTON_WIDTH = 48;
    public static int BUTTON_HEIGHT = 48;

    public static int BUTTON_PLAY_IMGX = 0;
    public static int BUTTON_PLAY_IMGY = 0;
    public static int BUTTON_MENU_IMGX = 0;
    public static int BUTTON_MENU_IMGY = 48;
    public static int BUTTON_MUTE_IMGX = 48;
    public static int BUTTON_MUTE_IMGY = 0;
    public static int BUTTON_UNMUTE_IMGX = 48;
    public static int BUTTON_UNMUTE_IMGY = 48;

    public static float BUTTON_MUSIC_SCALE = 1.2f;
    public static float BUTTON_MUSIC_OFF_X = -5;
    public static float BUTTON_MUSIC_OFF_Y = -5;
    public static float BUTTON_MUSIC_X = SCREEN_WIDTH - (BUTTON_WIDTH * BUTTON_MUSIC_SCALE) + BUTTON_MUSIC_OFF_X;
    public static float BUTTON_MUSIC_Y = SCREEN_HEIGHT - (BUTTON_HEIGHT * BUTTON_MUSIC_SCALE) + BUTTON_MUSIC_OFF_Y;

    /* end screen */
    public static String END_TEXT = "You got dotrush'd!\nYour score: %d";
    public static float END_TEXT_SCALE = 1.2f;
    public static int END_TEXT_WIDTH = SCREEN_WIDTH;
    public static float END_TEXT_OFFSET_X = 0;
    public static float END_TEXT_OFFSET_Y = SCREEN_WIDTH / 6;

    public static float END_BUTTON_PLAY_SCALE = 1.5f;
    public static float END_BUTTON_PLAY_OFF_X = SCREEN_WIDTH / 128;
    public static float END_BUTTON_PLAY_OFF_Y = -SCREEN_HEIGHT / 12;
    public static float END_BUTTON_PLAY_X = ((SCREEN_WIDTH + (BUTTON_WIDTH * END_BUTTON_PLAY_SCALE)) / 2) + END_BUTTON_PLAY_OFF_X;
    public static float END_BUTTON_PLAY_Y = ((SCREEN_HEIGHT - (BUTTON_HEIGHT * END_BUTTON_PLAY_SCALE)) / 2) + END_BUTTON_PLAY_OFF_Y;

    public static float END_BUTTON_MENU_SCALE = 1.5f;
    public static float END_BUTTON_MENU_OFF_X = -SCREEN_WIDTH / 128;
    public static float END_BUTTON_MENU_OFF_Y = -SCREEN_HEIGHT / 12;
    public static float END_BUTTON_MENU_X = ((SCREEN_WIDTH - (BUTTON_WIDTH * END_BUTTON_MENU_SCALE)) / 2) + END_BUTTON_MENU_OFF_X;
    public static float END_BUTTON_MENU_Y = ((SCREEN_HEIGHT - (BUTTON_HEIGHT * END_BUTTON_MENU_SCALE)) / 2) + END_BUTTON_MENU_OFF_Y;

    /* enemies */
    public static int ENEMY_NUMBER = 150;
    public static float ENEMY_MIN_VELOCITY = 80;
    public static float ENEMY_MAX_VELOCITY = 160;
    public static int ENEMY_POS_PADDING = Constants.SCREEN_WIDTH / 2;

    public static float ENEMY_DMIN_RADIUS = 45; /* 45 less than player */
    public static float ENEMY_DMAX_RADIUS = 35; /* 35 more than player */
    public static float ENEMY_MIN_RADIUS = 10;

    /* player */
    public static float PLAYER_MIN_RADIUS = 25;
    public static float PLAYER_TOUCH_RADIUS = 80;

    /* debugging */
    public static boolean DEBUG_LOG = true;
}
