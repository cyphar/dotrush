/*
 * dotrush: a game where you get Dot Rush'd
 * Copyright (C) 2014 Aleksa Sarai <cyphar@cyphar.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import com.jtdev.dotrush.GDXConstants;
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

        Texture texture = new Texture(Gdx.files.internal(GDXConstants.SPLASH_IMAGE_PATH));
        image = new TextureRegion(texture, 0, 0, (int) GDXConstants.SPLASH_WIDTH, (int) GDXConstants.SPLASH_HEIGHT);
        spriteBatch = new SpriteBatch();

        logger = new Logger(this);
        this.screenManager = screenManager;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        float splashOffset = (GDXConstants.VIRTUAL_SCREEN_HEIGHT - GDXConstants.SPLASH_RATIO * GDXConstants.VIRTUAL_SCREEN_WIDTH) / 2;
        if(splashOffset < 0)
            splashOffset = 0;

        spriteBatch.begin();
        spriteBatch.draw(image, 0, splashOffset, GDXConstants.VIRTUAL_SCREEN_WIDTH, GDXConstants.VIRTUAL_SCREEN_HEIGHT - 2 * splashOffset);
        spriteBatch.end();

        if(TimeUtils.millis() - startTime > GDXConstants.SPLASH_SCREEN_TIME)
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
