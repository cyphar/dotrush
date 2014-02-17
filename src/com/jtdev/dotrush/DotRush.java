package com.jtdev.dotrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.jtdev.dotrush.screens.SplashScreen;
import com.jtdev.dotrush.utils.Logger;

public class DotRush extends Game {
    private Logger logger;

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());

        setScreen(new SplashScreen(this));
    }
}
