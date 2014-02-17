package com.jtdev.dotrush.managers;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.entities.Enemy;
import com.jtdev.dotrush.entities.Player;
import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.utils.Tuple;

public class GameManager {
    private List<Enemy> enemyList;
    private Player player;
    private Logger logger;

    private Camera camera;
    private InputManager inputManager;

    private boolean started;

    public GameManager(Camera cam) {
        player = new Player();
        enemyList = new ArrayList<Enemy>();

        camera = cam;
        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        logger = new Logger(this);

        int i;
        for(i = 0; i < Constants.ENEMY_NUMBER; i++) {
            float max = player.getRadius() + Constants.ENEMY_DMAX_RADIUS,
                  min = player.getRadius() - Constants.ENEMY_DMIN_RADIUS;

            Enemy enemy = new Enemy(player.getScore(), min, max);
            enemyList.add(enemy);
        }

        started = false;
    }

    public int update(float delta) {
        if(inputManager.active)
            started = true;

        if(!started)
            return 0;

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
                enemyList.remove(i--);
            }

            /* collision with player */
            if(enemy.collides(player)) {
                if(player.getRadius() >= enemy.getRadius()) {
                    enemyList.remove(i--);
                    player.incScore();
                } else {
                    return 1;
                }
            }
        }

        while(enemyList.size() < Constants.ENEMY_NUMBER) {
            float max = player.getRadius() + Constants.ENEMY_DMAX_RADIUS,
                    min = player.getRadius() - Constants.ENEMY_DMIN_RADIUS;

            Enemy enemy = new Enemy(player.getScore(), min, max);
            enemyList.add(enemy);
        }

        return 0;
    }

    public void draw() {
        for(Enemy enemy: enemyList) {
            enemy.draw(camera);
        }

        player.draw(camera);
    }

    public void pause() {
        started = true;
    }

    public void resume() {
        started = false;
    }
}
