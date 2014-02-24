package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.entities.World;

public class GameScreen implements Screen {
    private final ScreenManager screenManager;
    private Logger logger;

    private World world;

    public GameScreen(ScreenManager screenManager) {
        screenManager.getMain().adManager.setVisibility(false);

        world = new World(screenManager);
        logger = new Logger(this);

        this.screenManager = screenManager;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        world.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        world.pause();
    }

    @Override
    public void resume() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void dispose() {

    }
}
