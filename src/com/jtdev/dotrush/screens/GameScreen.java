package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import com.jtdev.dotrush.DotRush;
import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.entities.World;

public class GameScreen implements Screen {
    private World world;
    private Logger logger;
    private DotRush main;

    public GameScreen(DotRush main) {
        world = new World(main);
        logger = new Logger(this);

        this.main = main;
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
        main.setScreen(new SplashScreen(main));
    }

    @Override
    public void dispose() {

    }
}
