package com.jtdev.dotrush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.managers.GameManager;
import com.jtdev.dotrush.screens.EndScreen;
import com.jtdev.dotrush.utils.Logger;

public class World {
    private Logger logger;

    private ScreenManager screenManager;
    private GameManager gameManager;

    private int score;

    public World(ScreenManager screenManager) {
        Camera camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        gameManager = new GameManager(screenManager, camera);

        logger = new Logger(this);
        this.screenManager = screenManager;
    }

    public void render(float delta) {
        if(gameManager.update(delta) != 0)
            screenManager.setScreen(new EndScreen(screenManager, gameManager.getScore()));

        gameManager.draw();
    }

    public void pause() {
        gameManager.pause();
    }

    public void resume() {
        gameManager.resume();
    }
}
