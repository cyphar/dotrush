/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
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
