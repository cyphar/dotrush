package com.jtdev.dotrush.managers;

import com.badlogic.gdx.Screen;
import com.jtdev.dotrush.DotRush;

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
    }

    @Override
    public void resize(int width, int height) {
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
