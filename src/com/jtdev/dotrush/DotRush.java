package com.jtdev.dotrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.screens.SplashScreen;
import com.jtdev.dotrush.screens.StartScreen;
import com.jtdev.dotrush.utils.Logger;

public class DotRush extends Game {
    private Logger logger;
    private ScreenManager screenManager;

    public BitmapFont gamefont;
    public Music music;
    public boolean playmusic;

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());

        gamefont = new BitmapFont(Gdx.files.internal(Constants.GAME_FONT_PATH), false);

        screenManager = new ScreenManager(this);
        screenManager.setScreen(new SplashScreen(screenManager));

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
