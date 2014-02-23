package com.jtdev.dotrush.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.entities.Enemy;
import com.jtdev.dotrush.entities.Player;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.utils.Tuple;

public class StartScreen implements Screen {
    private Logger logger;
    private ScreenManager screenManager;

    private TextureRegion logo;
    private float logox, logoy;

    private BitmapFont font;
    private float textx, texty;

    private SpriteBatch spriteBatch;

    private Player pseudoPlayer;
    private List<Enemy> pseudoEnemyList;
    private ShapeRenderer shapeRenderer;
    private Camera camera;

    public StartScreen(ScreenManager screenManager) {
        Texture logoTexture = new Texture(Gdx.files.internal(Constants.LOGO_IMAGE_PATH));
        logo = new TextureRegion(logoTexture, 0, 0, (int) Constants.LOGO_IMAGE_WIDTH, (int) Constants.LOGO_IMAGE_HEIGHT);
        font = screenManager.getMain().gamefont;
        spriteBatch = new SpriteBatch();

        logoy = Constants.LOGO_OFFSET_Y + (Constants.SCREEN_HEIGHT - Constants.LOGO_HEIGHT) / 2;
        logox = Constants.LOGO_OFFSET_X + (Constants.SCREEN_WIDTH - Constants.LOGO_WIDTH) / 2;

        texty = Constants.MENU_TEXT_OFFSET_Y + Constants.SCREEN_HEIGHT / 2;
        textx = Constants.MENU_TEXT_OFFSET_X;

        camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        shapeRenderer = new ShapeRenderer();

        pseudoPlayer = new Player();
        pseudoEnemyList = new ArrayList<Enemy>();
        for(int i = 0; i < Constants.MENU_ENEMY_NUMBER; i++) {
            Enemy enemy = new Enemy(0, Constants.PLAYER_MIN_RADIUS + Constants.MENU_ENEMY_DMIN_RADIUS, Constants.PLAYER_MIN_RADIUS + Constants.MENU_ENEMY_DMAX_RADIUS);
            pseudoEnemyList.add(enemy);
        }

        this.screenManager = screenManager;
        logger = new Logger(this);

        if(screenManager.getMain().playmusic)
            screenManager.getMain().music.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.justTouched()) {
            logger.log("switch from StartScreen to GameScreen");
            screenManager.setScreen(new GameScreen(screenManager));
        }

        for(int i = 0; i < pseudoEnemyList.size(); i++) {
            Enemy enemy = pseudoEnemyList.get(i);
            enemy.update(delta);

            Tuple<Float, Float> pos = enemy.getPos();

            float x = pos.x,
                  y = pos.y;

            /* outside of screen -- remove enemy */
            if(x + enemy.getRadius() < -camera.viewportWidth || x - enemy.getRadius() > camera.viewportWidth ||
               y + enemy.getRadius() < -camera.viewportHeight || y - enemy.getRadius() > camera.viewportHeight) {
                pseudoEnemyList.remove(i--);
            }
        }

        while(pseudoEnemyList.size() < Constants.MENU_ENEMY_NUMBER) {
            Enemy enemy = new Enemy(0, Constants.PLAYER_MIN_RADIUS + Constants.MENU_ENEMY_DMIN_RADIUS, Constants.PLAYER_MIN_RADIUS + Constants.MENU_ENEMY_DMAX_RADIUS);
            pseudoEnemyList.add(enemy);
        }

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(Enemy enemy: pseudoEnemyList)
            enemy.draw(shapeRenderer);
        pseudoPlayer.draw(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        font.setColor(1, 1, 1, 1);
        font.setScale(Constants.MENU_TEXT_SCALE);
        font.drawMultiLine(spriteBatch, Constants.MENU_TEXT, textx, texty, Constants.MENU_TEXT_WIDTH, BitmapFont.HAlignment.CENTER);

        font.setScale(Constants.MENU_CREDITS_SCALE);
        font.drawMultiLine(spriteBatch, Constants.MENU_CREDITS, Constants.MENU_CREDITS_X, Constants.MENU_CREDITS_Y);

        spriteBatch.draw(logo, logox, logoy, Constants.LOGO_WIDTH, Constants.LOGO_HEIGHT);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
