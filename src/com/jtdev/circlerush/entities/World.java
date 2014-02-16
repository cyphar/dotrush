package com.jtdev.circlerush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.jtdev.circlerush.Constants;
import com.jtdev.circlerush.CircleRush;
import com.jtdev.circlerush.managers.GameManager;
import com.jtdev.circlerush.screens.GameScreen;
import com.jtdev.circlerush.utils.Logger;

public class World{
    private GameManager manager;
    private Logger logger;
    private CircleRush main;

    public World(CircleRush caller) {
        manager = new GameManager(new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        logger = new Logger(this);
        main = caller;
    }

    public void render(float delta) {
        if(manager.update(delta) != 0) {
            main.setScreen(new GameScreen(main));
        }

        manager.draw();
    }

    public void pause() {
        manager.pause();
    }

    public void resume() {
        manager.resume();
    }
}
