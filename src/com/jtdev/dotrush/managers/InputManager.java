package com.jtdev.dotrush.managers;

import com.badlogic.gdx.InputProcessor;
import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.utils.Tuple;

public class InputManager implements InputProcessor {
    private int clickx, clicky;

    public int pointerx, pointery;
    public boolean active, inverted;

    public InputManager() {
        pointerx = pointery = 0;
        clickx = clicky = -1;

        active = false;
        inverted = true;
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

    public Tuple<Integer, Integer> getTap() {
        if(clickx < 0 || clicky < 0)
            return null;

        Tuple<Integer, Integer> pos = new Tuple<Integer, Integer>(clickx, clicky);
        return pos;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        clickx = pointerx = x;
        clicky = pointery = y;

        if(inverted)
            clicky = pointery = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        active = true;
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        pointerx = x;
        pointery = y;

        if(inverted)
            pointery = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        clickx = clicky = -1;
        active = false;
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        pointerx = x;
        pointery = y;

        if(inverted)
            pointery = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        clickx = clicky = -1;
        active = true;
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        pointerx = x;
        pointery = y;

        if(inverted)
            pointery = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        clickx = clicky = -1;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}