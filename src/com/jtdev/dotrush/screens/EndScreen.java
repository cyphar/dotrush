package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Screen;
import com.jtdev.dotrush.DotRush;
import com.jtdev.dotrush.utils.Logger;

public class EndScreen implements Screen {
    private Logger logger;
    private DotRush main;

    private int score;

    public EndScreen(DotRush main, int score) {
        this.main = main;
        this.score = score;

        logger = new Logger(this);
    }

    @Override
    public void render(float delta) {
        logger.log("switch from EndScreen(" + score + ") to GameScreen");
        main.setScreen(new GameScreen(this.main));
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
