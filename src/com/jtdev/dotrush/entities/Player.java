package com.jtdev.dotrush.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.utils.Tuple;

public class Player implements Entity {
    private float radius;
    private float x, y;

    private Color colour;

    public Player() {
        x = GDXConstants.VIRTUAL_SCREEN_WIDTH / 2;
        y = GDXConstants.VIRTUAL_SCREEN_HEIGHT / 2;

        radius = GDXConstants.PLAYER_MIN_RADIUS;
        colour = Color.WHITE;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(colour);
        shapeRenderer.circle(x, y, radius);
    }

    public void updRadius(int score) {
        radius = GDXConstants.PLAYER_MIN_RADIUS + score;
    }

    public void updPos(float dx, float dy) {
        setPos(x + dx, y + dy);
    }

    @Override
    public Tuple<Float, Float> getPos() {
        return new Tuple<Float, Float>(x, y);
    }

    @Override
    public void setPos(float x, float y) {
        float touchRadius = radius + GDXConstants.PLAYER_TOUCH_RADIUS;

        if(Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) <= touchRadius) {
            this.x = x;
            this.y = y;
        }

        if(this.x - radius < 0)
            this.x = radius;
        else if(this.x + radius > GDXConstants.VIRTUAL_SCREEN_WIDTH)
            this.x = GDXConstants.VIRTUAL_SCREEN_WIDTH - radius;


        if(this.y - radius < 0)
            this.y = radius;
        else if(this.y + radius > GDXConstants.VIRTUAL_SCREEN_HEIGHT)
            this.y = GDXConstants.VIRTUAL_SCREEN_HEIGHT - radius;
    }

    @Override
    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public boolean collides(Entity other) {
        Tuple<Float, Float> opos = other.getPos();

        float ox = opos.x,
              oy = opos.y,
              oradius = other.getRadius();

        return Math.pow(ox - x, 2) + Math.pow(oy - y, 2) <= Math.pow(oradius + radius, 2);
    }
}
