package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.utils.Logger;

public class SplashScreen implements Screen {
    private final ScreenManager screenManager;
    private Logger logger;

    private SpriteBatch spriteBatch;
    private TextureRegion image;

    private long startTime;

    public SplashScreen(ScreenManager screenManager) {
        screenManager.getMain().adManager.setVisibility(false);

        Texture texture = new Texture(Gdx.files.internal(Constants.SPLASH_IMAGE_PATH));
        image = new TextureRegion(texture, 0, 0, (int) Constants.SPLASH_WIDTH, (int) Constants.SPLASH_HEIGHT);
        spriteBatch = new SpriteBatch();

        logger = new Logger(this);
        this.screenManager = screenManager;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        float splashOffset = (Constants.SCREEN_HEIGHT - Constants.SPLASH_RATIO * Constants.SCREEN_WIDTH) / 2;
        if(splashOffset < 0)
            splashOffset = 0;

        spriteBatch.begin();
        spriteBatch.draw(image, 0, splashOffset, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT - 2 * splashOffset);
        spriteBatch.end();

        if(TimeUtils.millis() - startTime > Constants.SPLASH_SCREEN_TIME)
            screenManager.setScreen(new StartScreen(screenManager));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        startTime = TimeUtils.millis();
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void dispose() {
    }
}
