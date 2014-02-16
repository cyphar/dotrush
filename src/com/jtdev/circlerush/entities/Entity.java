package com.jtdev.circlerush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.jtdev.circlerush.utils.Tuple;

public interface Entity {
    public void update(float delta);
    public void draw(Camera camera);

    public boolean collides(Entity other);

    public float getRadius();
    public void setRadius(float radius);

    public Tuple<Float, Float> getPos();
    public void setPos(float x, float y);

    public void setColour(Color colour);
    public Color getColour();
}
