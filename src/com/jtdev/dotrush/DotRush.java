package com.jtdev.dotrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.screens.SplashScreen;
import com.jtdev.dotrush.utils.Logger;

public class DotRush extends Game {
    private Logger logger;
    private ScreenManager screenManager;

    public BitmapFont gamefont;
    public Music music;

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());

        gamefont = new BitmapFont(Gdx.files.internal(Constants.GAME_FONT_PATH), false);
        gamefont.scale(Constants.GAME_FONT_SCALE);

        screenManager = new ScreenManager(this);
        screenManager.setScreen(new SplashScreen(screenManager));

        /* music is http://radtunez.com/ */
        music = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAME_MUSIC_PATH));
        music.setLooping(true);
        music.setVolume(Constants.GAME_MUSIC_VOLUME);

        music.play();
        setScreen(screenManager);
    }
}
