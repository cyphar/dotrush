package com.jtdev.circlerush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.jtdev.circlerush.utils.Tuple;

public class Player implements Entity {
    private int score;
    private float radius;
    private float x, y;

    private Color colour;
    private ShapeRenderer shapeRenderer;

    public Player() {
        x = y = 0.0f;
        score = 0;
        radius = 30f;

        colour = Color.WHITE;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(Camera camera) {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(colour);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
    }

    public int getScore() {
        return score;
    }

    @Override
    public Tuple<Float, Float> getPos() {
        Float x, y;

        x = this.x;
        y = this.y;

        return new Tuple<Float, Float>(x, y);
    }

    @Override
    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
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
