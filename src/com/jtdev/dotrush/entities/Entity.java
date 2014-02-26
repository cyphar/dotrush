/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jtdev.dotrush.utils.Tuple;

public interface Entity {
    public void update(float delta);
    public void draw(ShapeRenderer shapeRenderer);

    public boolean collides(Entity other);

    public float getRadius();
    public void setRadius(float radius);

    public Tuple<Float, Float> getPos();
    public void setPos(float x, float y);

    public void setColour(Color colour);
    public Color getColour();
}
