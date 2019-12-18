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

package com.jtdev.dotrush.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.jtdev.dotrush.managers.InputManager;

public class Button {
    private Sprite sprite;

    public Button(float x, float y, TextureRegion image) {
        sprite = new Sprite(image);
        sprite.setPosition(x, y);
    }

    public Button(float x, float y, float scale, TextureRegion image) {
        sprite = new Sprite(image);
        sprite.setPosition(x, y);
        sprite.setScale(scale);
    }

    public void setImage(TextureRegion image) {
        sprite.setRegion(image);
    }

    public void setScale(float x, float y) {
        sprite.setScale(x, y);
    }

    public boolean justPressed(InputManager inputManager) {
        Tuple<Integer, Integer> pos = inputManager.getTap();

        if(pos == null)
            return false;

        return sprite.getBoundingRectangle().contains(pos.x, pos.y);
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }
}
