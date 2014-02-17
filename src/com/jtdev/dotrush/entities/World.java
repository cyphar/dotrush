package com.jtdev.dotrush.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.DotRush;
import com.jtdev.dotrush.managers.GameManager;
import com.jtdev.dotrush.screens.EndScreen;
import com.jtdev.dotrush.utils.Logger;

public class World{
    private GameManager manager;
    private Logger logger;
    private DotRush main;

    private int score;

    public World(DotRush caller) {
        manager = new GameManager(new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        logger = new Logger(this);
        main = caller;
    }

    public void render(float delta) {
        if(manager.update(delta) != 0)
            main.setScreen(new EndScreen(main, manager.getScore()));

        manager.draw();
    }

    public void pause() {
        manager.pause();
    }

    public void resume() {
        manager.resume();
    }
}
