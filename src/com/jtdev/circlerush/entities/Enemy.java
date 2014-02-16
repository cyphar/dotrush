package com.jtdev.circlerush.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.jtdev.circlerush.Constants;
import com.jtdev.circlerush.utils.Logger;
import com.jtdev.circlerush.utils.Tuple;
import com.jtdev.circlerush.utils.Rand;

public class Enemy implements Entity {
    private Logger logger;
    private float radius;
    private Color colour;

    private float velocity, angle;
    private float x, y;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public Enemy(int score) {
        logger = new Logger(this);
        radius =  Rand.getRange(Constants.ENEMY_MIN_RADIUS, (score + 1) * Constants.ENEMY_RADIUS_WEIGHT * Constants.ENEMY_MAX_RADIUS);
        velocity = Rand.getRange(Constants.ENEMY_MIN_VELOCITY, Constants.ENEMY_MAX_VELOCITY);
        colour = new Color(Rand.getRange(0.2f, 0.9f), Rand.getRange(0.2f, 0.9f), Rand.getRange(0.2f, 0.9f), 1f);

        /* calculate position outside of screen for enemy to start */
        int spawn = Rand.getRange(0, 3);
        int x, y;

        switch(spawn) {
            default:
            case 0: /* top */
                y = Constants.SCREEN_HEIGHT + (int)radius;
                x = Rand.getRange(-Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH);
                angle = -Rand.getRange(0f, 180f);
                break;
            case 1: /* bottom */
                y = -(Constants.SCREEN_HEIGHT + (int)radius);
                x = Rand.getRange(-Constants.SCREEN_WIDTH, Constants.SCREEN_WIDTH);
                angle = Rand.getRange(0f, 180f);
                break;
            case 2: /* left */
                y = Rand.getRange(-Constants.SCREEN_HEIGHT, Constants.SCREEN_HEIGHT);
                x = -(Constants.SCREEN_WIDTH + (int)radius);
                angle = -Rand.getRange(90f, 270f);
                break;
            case 3: /* right */
                y = Rand.getRange(-Constants.SCREEN_HEIGHT, Constants.SCREEN_HEIGHT);
                x = Constants.SCREEN_WIDTH + (int)radius;
                angle = Rand.getRange(90f, 270f);
                break;
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {
        float dx = velocity * (float) Math.cos(Math.toRadians(angle)) * delta;
        float dy = velocity * (float) Math.sin(Math.toRadians(angle)) * delta;

        x += dx;
        y += dy;
    }

    @Override
    public void draw(Camera camera) {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(colour);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
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

    @Override
    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public Color getColour() {
        return colour;
    }
}
