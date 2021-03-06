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

package com.jtdev.dotrush.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.utils.Tuple;
import com.jtdev.dotrush.utils.Rand;

public class Enemy implements Entity {
    private Logger logger;
    private float radius;
    private Color colour;

    private float velocity, angle;
    private float x, y;

    public Enemy(int score, float min, float max) {
        logger = new Logger(this);
        radius = Rand.getRange(min, max);
        velocity = Rand.getRange(GDXConstants.ENEMY_MIN_VELOCITY, GDXConstants.ENEMY_MAX_VELOCITY);
        colour = new Color(Rand.getRange(0.2f, 0.9f), Rand.getRange(0.2f, 0.9f), Rand.getRange(0.2f, 0.9f), 1f);

        if(radius < GDXConstants.ENEMY_MIN_RADIUS)
            radius = GDXConstants.ENEMY_MIN_RADIUS;

        /* calculate position outside of screen for enemy to start */
        int spawn = Rand.getRange(0, 3);
        int x, y;

        switch(spawn) {
            default:
            case 0: /* top */
                y = GDXConstants.VIRTUAL_SCREEN_HEIGHT + (int) radius + GDXConstants.ENEMY_MIN_PADDING;
                x = Rand.getRange(-GDXConstants.ENEMY_POS_PADDING, GDXConstants.VIRTUAL_SCREEN_WIDTH + GDXConstants.ENEMY_POS_PADDING);
                angle = -Rand.getRange(0f, 180f);
                break;
            case 1: /* bottom */
                y = -((int) radius  + GDXConstants.ENEMY_MIN_PADDING);
                x = Rand.getRange(-GDXConstants.ENEMY_POS_PADDING, GDXConstants.VIRTUAL_SCREEN_WIDTH + GDXConstants.ENEMY_POS_PADDING);
                angle = Rand.getRange(0f, 180f);
                break;
            case 2: /* left */
                y = Rand.getRange(-GDXConstants.ENEMY_POS_PADDING, GDXConstants.VIRTUAL_SCREEN_HEIGHT + GDXConstants.ENEMY_POS_PADDING);
                x = -((int) radius + GDXConstants.ENEMY_MIN_PADDING);
                angle = -Rand.getRange(90f, 270f);
                break;
            case 3: /* right */
                y = Rand.getRange(-GDXConstants.ENEMY_POS_PADDING, GDXConstants.VIRTUAL_SCREEN_HEIGHT + GDXConstants.ENEMY_POS_PADDING);
                x = GDXConstants.VIRTUAL_SCREEN_WIDTH + (int) radius + GDXConstants.ENEMY_MIN_PADDING;
                angle = Rand.getRange(90f, 270f);
                break;
        }

        this.x = x;
        this.y = y;

        angle = (float) Math.toRadians(angle);
    }

    @Override
    public void update(float delta) {
        float dx = velocity * (float) Math.cos(angle) * delta;
        float dy = velocity * (float) Math.sin(angle) * delta;

        x += dx;
        y += dy;
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(colour);
        shapeRenderer.circle(x, y, radius);
    }

    @Override
    public Tuple<Float, Float> getPos() {
        return new Tuple<Float, Float>(this.x, this.y);
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
