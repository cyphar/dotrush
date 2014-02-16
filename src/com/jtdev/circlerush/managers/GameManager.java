package com.jtdev.circlerush.managers;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;

import com.jtdev.circlerush.Constants;
import com.jtdev.circlerush.entities.Enemy;
import com.jtdev.circlerush.entities.Player;
import com.jtdev.circlerush.utils.Logger;
import com.jtdev.circlerush.utils.Tuple;

public class GameManager {
    private List<Enemy> enemyList;
    private Player player;
    private Logger logger;

    private Camera camera;
    private InputManager inputManager;

    private boolean paused;

    public GameManager(Camera cam) {
        player = new Player();
        enemyList = new ArrayList<Enemy>();

        camera = cam;
        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        logger = new Logger(this);

        int i;
        for(i = 0; i < Constants.ENEMY_NUMBER; i++) {
            Enemy enemy = new Enemy(player.getScore());
            enemyList.add(enemy);
        }

        paused = false;
    }

    public void update(float delta) {
        if(inputManager.active)
            paused = false;
        else
            paused = true;

        if(paused)
            return;

        player.setPos(inputManager.pointerx, inputManager.pointery);
        player.update(delta);

        int i;
        for(i = 0; i < enemyList.size(); i++) {
            Enemy enemy = enemyList.get(i);
            enemy.update(delta);

            Tuple<Float, Float> pos = enemy.getPos();

            float x = pos.x,
                  y = pos.y;

            /* outside of screen -- remove enemy */
            if(x + enemy.getRadius() < -camera.viewportWidth || x - enemy.getRadius() > camera.viewportWidth ||
               y + enemy.getRadius() < -camera.viewportHeight || y - enemy.getRadius() > camera.viewportHeight) {
                enemyList.remove(i);
                i--;
            }

            /* collision with player */
            if(enemy.collides(player)) {
                enemy.setColour(Color.BLACK);
                /* TODO: check and delete enemy / end game */
            }
        }

        while(enemyList.size() < Constants.ENEMY_NUMBER) {
            Enemy enemy = new Enemy(player.getScore());
            enemyList.add(enemy);
        }

    }

    public void draw() {
        for(Enemy enemy: enemyList) {
            enemy.draw(camera);
        }

        player.draw(camera);
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }
}
