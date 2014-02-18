package com.jtdev.dotrush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.DotRush;
import com.jtdev.dotrush.managers.GameManager;
import com.jtdev.dotrush.screens.EndScreen;
import com.jtdev.dotrush.utils.Logger;

public class World {
    private Logger logger;
    private DotRush main;

    private GameManager manager;
    private int score;

    public World(DotRush main) {
        Camera camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        manager = new GameManager(main, camera);

        logger = new Logger(this);
        this.main = main;
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
