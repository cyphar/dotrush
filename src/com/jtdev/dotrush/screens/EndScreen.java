package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Screen;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.utils.Logger;

public class EndScreen implements Screen {
    private Logger logger;
    private ScreenManager screenManager;

    private int score;

    public EndScreen(ScreenManager screenManager, int score) {
        this.screenManager = screenManager;
        this.score = score;

        logger = new Logger(this);
    }

    @Override
    public void render(float delta) {
        logger.log("switch from EndScreen(" + score + ") to GameScreen");
        screenManager.setScreen(new GameScreen(screenManager));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
