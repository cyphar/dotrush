/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.managers;

import com.badlogic.gdx.InputProcessor;
import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.utils.Tuple;

public class InputManager implements InputProcessor {
    private int clickx, clicky;

    public int dx, dy;
    public int pointerx, pointery;
    public boolean active, inverted;

    public InputManager() {
        pointerx = pointery = 0;

        clickx = clicky = -1;
        dx = dy = 0;

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
        if(inverted)
            y = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        clickx = pointerx = x;
        clicky = pointery = y;
        dx = dy = 0;

        active = true;
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if(inverted)
            y = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        pointerx = x;
        pointery = y;
        dx = dy = 0;

        clickx = clicky = -1;
        active = false;
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        if(inverted)
            y = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        dx = x - pointerx;
        dy = y - pointery;

        pointerx = x;
        pointery = y;

        clickx = clicky = -1;
        active = true;
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        touchDragged(x, y, 0);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}