package com.jtdev.dotrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.jtdev.dotrush.screens.SplashScreen;
import com.jtdev.dotrush.utils.Logger;

public class DotRush extends Game {
    private Logger logger;

    public BitmapFont gamefont;

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());

        gamefont = new BitmapFont(Gdx.files.internal(Constants.GAME_FONT_PATH), false);
        gamefont.scale(Constants.GAME_FONT_SCALE);

        setScreen(new SplashScreen(this));
    }
}
