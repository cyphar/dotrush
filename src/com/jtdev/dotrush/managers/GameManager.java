package com.jtdev.dotrush.managers;

import java.util.*;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.entities.Enemy;
import com.jtdev.dotrush.entities.Player;
import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.utils.Tuple;

public class GameManager {
    private List<Enemy> enemyList;
    private Player player;

    private Logger logger;
    private ScreenManager screenManager;

    private Camera camera;
    private InputManager inputManager;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private int score;
    private boolean started;

    public GameManager(ScreenManager screenManager) {
        player = new Player();
        enemyList = new ArrayList<Enemy>();

        camera = screenManager.getMain().camera;
        this.screenManager = screenManager;
        inputManager = screenManager.getMain().inputManager;

        logger = new Logger(this);

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        font = screenManager.getMain().gamefont;

        int i;
        for(i = 0; i < GDXConstants.ENEMY_NUMBER; i++) {
            float max = player.getRadius() + GDXConstants.ENEMY_DMAX_RADIUS,
                  min = player.getRadius() - GDXConstants.ENEMY_DMIN_RADIUS;

            Enemy enemy = new Enemy(score, min, max);
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
            if(x + enemy.getRadius() < -GDXConstants.ENEMY_POS_PADDING || x - enemy.getRadius() > GDXConstants.VIRTUAL_SCREEN_WIDTH + GDXConstants.ENEMY_POS_PADDING ||
               y + enemy.getRadius() < -GDXConstants.ENEMY_POS_PADDING || y - enemy.getRadius() > GDXConstants.VIRTUAL_SCREEN_HEIGHT + GDXConstants.ENEMY_POS_PADDING) {
                enemyList.remove(i--);
            }

            /* collision with player */
            if(enemy.collides(player)) {
                if(player.getRadius() < enemy.getRadius())
                    return 1;

                enemyList.remove(i--);
                player.updRadius(++score);
            }
        }

        while(enemyList.size() < GDXConstants.ENEMY_NUMBER) {
            float max = player.getRadius() + GDXConstants.ENEMY_DMAX_RADIUS,
                    min = player.getRadius() - GDXConstants.ENEMY_DMIN_RADIUS;

            Enemy enemy = new Enemy(score, min, max);
            enemyList.add(enemy);
        }

        return 0;
    }

    public void draw() {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(Enemy enemy: enemyList)
            enemy.draw(shapeRenderer);
        player.draw(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        font.setColor(1, 1, 1, 1);
        font.setScale(GDXConstants.SCORE_SCALE);
        font.draw(spriteBatch, GDXConstants.SCORE_TEXT + score, GDXConstants.SCORE_X, GDXConstants.SCORE_Y);
        spriteBatch.end();
    }

    public void pause() {
    }

    public void resume() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
