package com.jtdev.circlerush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.jtdev.circlerush.Constants;
import com.jtdev.circlerush.managers.GameManager;
import com.jtdev.circlerush.utils.Logger;

public class World{
    private GameManager manager;
    private Logger logger;

    public World() {
        manager = new GameManager(new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        logger = new Logger(this);
    }

    public void render(float delta) {
        logger.log("rendering world after " + delta);

        manager.update(delta);
        manager.draw();
    }

    public void pause() {
        logger.log("pausing world");

        manager.pause();
    }

    public void resume() {
        logger.log("resuming world");

        manager.resume();
    }
}
