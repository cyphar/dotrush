package com.jtdev.circlerush.managers;

import com.badlogic.gdx.InputProcessor;
import com.jtdev.circlerush.Constants;

public class InputManager implements InputProcessor {
    public int pointerx, pointery;
    public boolean active;

    public InputManager() {
        pointerx = 0;
        pointery = 0;
        active = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        pointerx = x - Constants.SCREEN_WIDTH / 2;
        pointery = -(y - Constants.SCREEN_HEIGHT / 2);

        active = true;
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        pointerx = x - Constants.SCREEN_WIDTH / 2;
        pointery = -(y - Constants.SCREEN_HEIGHT / 2);

        active = false;
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        pointerx = x - Constants.SCREEN_WIDTH / 2;
        pointery = -(y - Constants.SCREEN_HEIGHT / 2);
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        pointerx = x - Constants.SCREEN_WIDTH / 2;
        pointery = -(y - Constants.SCREEN_HEIGHT / 2);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}