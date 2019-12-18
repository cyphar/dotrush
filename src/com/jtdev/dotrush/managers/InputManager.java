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

        return new Tuple<Integer, Integer>(clickx, clicky);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if(inverted)
            y = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        clickx = pointerx = x;
        clicky = pointery = y;

        active = true;
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if(inverted)
            y = GDXConstants.VIRTUAL_SCREEN_HEIGHT - y;

        pointerx = x;
        pointery = y;

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

    public void reset() {
        dx = dy = 0;
        clickx = clicky = -1;
    }
}
