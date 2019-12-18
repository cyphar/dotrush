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

import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.entities.World;

public class GameScreen implements Screen {
    private final ScreenManager screenManager;
    private Logger logger;

    private World world;

    public GameScreen(ScreenManager screenManager) {
        screenManager.getMain().adManager.setVisibility(false);

        world = new World(screenManager);
        logger = new Logger(this);

        this.screenManager = screenManager;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        world.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        world.pause();
    }

    @Override
    public void resume() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void dispose() {

    }
}
