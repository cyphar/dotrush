package com.jtdev.circlerush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.jtdev.circlerush.screens.SplashScreen;
import com.jtdev.circlerush.utils.Logger;

public class Main extends Game {
    private Logger logger;

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());

        setScreen(new SplashScreen(this));
    }
}
