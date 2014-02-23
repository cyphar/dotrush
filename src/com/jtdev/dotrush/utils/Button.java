package com.jtdev.dotrush.utils;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.jtdev.dotrush.Constants;
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
