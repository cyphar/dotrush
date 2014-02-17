package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import com.jtdev.dotrush.DotRush;
import com.jtdev.dotrush.Constants;
import com.jtdev.dotrush.utils.Logger;

public class SplashScreen implements Screen {
    private final DotRush main;
    private Logger logger;

    private SpriteBatch spriteBatch;
    private TextureRegion image;

    private long startTime;

    public SplashScreen(DotRush main) {
        Texture texture = new Texture(Gdx.files.internal(Constants.SPLASH_IMAGE_PATH));
        image = new TextureRegion(texture, 0, 0, (int) Constants.SPLASH_WIDTH, (int) Constants.SPLASH_HEIGHT);

        logger = new Logger(this);
        this.main = main;
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
            main.setScreen(new GameScreen(main));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        startTime = TimeUtils.millis();
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
