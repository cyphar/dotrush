package com.jtdev.circlerush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import com.jtdev.circlerush.Constants;
import com.jtdev.circlerush.Main;

public class SplashScreen implements Screen {
    private final Main main;
    private SpriteBatch spriteBatch;
    private TextureRegion image;

    public SplashScreen(Main main) {
        Texture texture = new Texture(Constants.SPLASH_IMAGE_PATH);
        image = new TextureRegion(texture, Constants.SPLASH_WIDTH, Constants.SPLASH_HEIGHT);

        this.main = main;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(image, 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        spriteBatch.end();

        /*if (TimeUtils.millis() - startTime > Constants.SPLASH_SCREEN_TIME) main.setScreen(new GameScreen());*/
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
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
