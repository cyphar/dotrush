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

package com.jtdev.dotrush.managers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jtdev.dotrush.DotRush;
import com.jtdev.dotrush.GDXConstants;

public class ScreenManager implements Screen {
    private final DotRush main;
    private Screen screen;

    public ScreenManager(DotRush caller) {
        main = caller;
        screen = null;
    }

    public ScreenManager(DotRush caller, Screen start) {
        main = caller;
        screen = start;
    }

    public DotRush getMain() {
        return main;
    }

    public void setScreen(Screen next) {
        screen = next;
    }

    public com.badlogic.gdx.Screen getScreen() {
        return screen;
    }

    @Override
    public void render(float delta) {
        screen.render(delta);
        main.inputManager.reset();
    }

    @Override
    public void resize(int width, int height) {
        main.camera = new OrthographicCamera(GDXConstants.VIRTUAL_SCREEN_WIDTH, GDXConstants.VIRTUAL_SCREEN_HEIGHT);
        main.camera.translate(GDXConstants.VIRTUAL_SCREEN_WIDTH / 2, GDXConstants.VIRTUAL_SCREEN_HEIGHT / 2, 0);

        screen.resize(width, height);
    }

    @Override
    public void show() {
        screen.show();
    }

    @Override
    public void hide() {
        screen.hide();
    }

    @Override
    public void pause() {
        screen.pause();
    }

    @Override
    public void resume() {
        screen.resume();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }
}
