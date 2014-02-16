package com.jtdev.circlerush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import com.jtdev.circlerush.utils.Logger;
import com.jtdev.circlerush.entities.World;

public class GameScreen implements Screen {
    private World world;
    private Logger logger;
    private Object caller;

    public GameScreen(Object obj) {
        caller = obj;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        world.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        logger.log("tried to resize screen to " + width + "x" + height);
    }

    @Override
    public void show() {
        world = new World();
        logger = new Logger(this);
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
        world.resume();
    }

    @Override
    public void dispose() {

    }
}
