package com.jtdev.dotrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

import com.jtdev.dotrush.managers.InputManager;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.screens.SplashScreen;
import com.jtdev.dotrush.utils.Logger;

public class DotRush extends Game {
    private Logger logger;
    private ScreenManager screenManager;

    public InputManager inputManager;

    public BitmapFont gamefont;
    public Music music;
    public boolean playmusic;
    public Camera camera;

    public TextureRegion muteImage, unmuteImage, playImage, menuImage;

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());

        gamefont = new BitmapFont(Gdx.files.internal(Constants.GAME_FONT_PATH), false);

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.translate(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 0);
        camera.update();

        screenManager = new ScreenManager(this);
        screenManager.setScreen(new SplashScreen(screenManager));

        Texture buttonMap = new Texture(Gdx.files.internal(Constants.BUTTON_IMAGE_PATH));
        playImage   = new TextureRegion(buttonMap, Constants.BUTTON_PLAY_IMGX, Constants.BUTTON_PLAY_IMGY, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        menuImage   = new TextureRegion(buttonMap, Constants.BUTTON_MENU_IMGX, Constants.BUTTON_MENU_IMGY, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        muteImage   = new TextureRegion(buttonMap, Constants.BUTTON_MUTE_IMGX, Constants.BUTTON_MUTE_IMGY, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        unmuteImage = new TextureRegion(buttonMap, Constants.BUTTON_UNMUTE_IMGX, Constants.BUTTON_UNMUTE_IMGY, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);

        /* music is courtesy of http://radtunez.com/ */
        music = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAME_MUSIC_PATH));
        music.setLooping(true);
        music.setVolume(Constants.GAME_MUSIC_VOLUME);
        playmusic = true;

        setScreen(screenManager);
    }

    @Override
    public void resume() {
        music.stop();
        screenManager.setScreen(new SplashScreen(screenManager));
    }

}
