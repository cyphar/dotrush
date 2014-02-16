package com.jtdev.circlerush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import com.jtdev.circlerush.utils.Logger;
import com.jtdev.circlerush.entities.World;
import com.jtdev.circlerush.Main;

public class GameScreen implements Screen {
    private World world;
    private Logger logger;
    private Main main;

    public GameScreen(Main main) {
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
        world = new World(main);
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
        main.setScreen(new SplashScreen(main));
    }

    @Override
    public void dispose() {

    }
}
