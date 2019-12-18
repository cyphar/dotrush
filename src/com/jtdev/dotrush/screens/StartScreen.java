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

package com.jtdev.dotrush.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.entities.Enemy;
import com.jtdev.dotrush.entities.Player;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.utils.Button;
import com.jtdev.dotrush.utils.Logger;
import com.jtdev.dotrush.utils.Tuple;

public class StartScreen implements Screen {
    private Logger logger;
    private ScreenManager screenManager;

    private TextureRegion logo;
    private float logox, logoy;

    private BitmapFont font;
    private float textx, texty;

    private SpriteBatch spriteBatch;

    private Player pseudoPlayer;
    private List<Enemy> pseudoEnemyList;
    private ShapeRenderer shapeRenderer;
    private Camera camera;

    private Button music, sfx;

    public StartScreen(ScreenManager screenManager) {
        screenManager.getMain().adManager.setVisibility(false);

        Texture logoTexture = new Texture(Gdx.files.internal(GDXConstants.LOGO_IMAGE_PATH));
        logo = new TextureRegion(logoTexture, 0, 0, (int) GDXConstants.LOGO_IMAGE_WIDTH, (int) GDXConstants.LOGO_IMAGE_HEIGHT);
        font = screenManager.getMain().gamefont;
        spriteBatch = new SpriteBatch();

        logoy = GDXConstants.LOGO_OFFSET_Y + (GDXConstants.VIRTUAL_SCREEN_HEIGHT - GDXConstants.LOGO_HEIGHT) / 2;
        logox = GDXConstants.LOGO_OFFSET_X + (GDXConstants.VIRTUAL_SCREEN_WIDTH - GDXConstants.LOGO_WIDTH) / 2;

        texty = GDXConstants.MENU_TEXT_OFFSET_Y + GDXConstants.VIRTUAL_SCREEN_HEIGHT / 2;
        textx = GDXConstants.MENU_TEXT_OFFSET_X;

        camera = screenManager.getMain().camera;
        shapeRenderer = new ShapeRenderer();

        pseudoPlayer = new Player();
        pseudoEnemyList = new ArrayList<Enemy>();
        for(int i = 0; i < GDXConstants.MENU_ENEMY_NUMBER; i++) {
            Enemy enemy = new Enemy(0, GDXConstants.PLAYER_MIN_RADIUS + GDXConstants.MENU_ENEMY_DMIN_RADIUS, GDXConstants.PLAYER_MIN_RADIUS + GDXConstants.MENU_ENEMY_DMAX_RADIUS);
            pseudoEnemyList.add(enemy);
        }

        this.screenManager = screenManager;
        logger = new Logger(this);

        if(screenManager.getMain().playmusic)
            screenManager.getMain().music.play();

        TextureRegion musicImage = screenManager.getMain().playmusic ? screenManager.getMain().muteMusicImage : screenManager.getMain().unmuteMusicImage;
        music = new Button(GDXConstants.BUTTON_MUSIC_X, GDXConstants.BUTTON_MUSIC_Y, musicImage);
        music.setScale(GDXConstants.BUTTON_MUSIC_SCALE_X, GDXConstants.BUTTON_MUSIC_SCALE_Y);

        TextureRegion sfxImage = screenManager.getMain().playsfx ? screenManager.getMain().muteSfxImage : screenManager.getMain().unmuteSfxImage;
        sfx = new Button(GDXConstants.BUTTON_SFX_X, GDXConstants.BUTTON_SFX_Y, sfxImage);
        sfx.setScale(GDXConstants.BUTTON_SFX_SCALE_X, GDXConstants.BUTTON_SFX_SCALE_Y);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        boolean musicTouched = music.justPressed(screenManager.getMain().inputManager);
        boolean sfxTouched = sfx.justPressed(screenManager.getMain().inputManager);

        if(Gdx.input.justTouched() && !(musicTouched || sfxTouched)) {
            logger.log("switch from StartScreen to GameScreen");
            screenManager.setScreen(new GameScreen(screenManager));
        }

        if(musicTouched) {
            if(screenManager.getMain().music.isPlaying()) {
                screenManager.getMain().music.stop();
                music.setImage(screenManager.getMain().unmuteMusicImage);
            } else {
                screenManager.getMain().music.play();
                music.setImage(screenManager.getMain().muteMusicImage);
            }

            screenManager.getMain().playmusic = screenManager.getMain().music.isPlaying();
        }

        if(sfxTouched) {
            if(screenManager.getMain().playsfx)
                sfx.setImage(screenManager.getMain().unmuteSfxImage);
            else
                sfx.setImage(screenManager.getMain().muteSfxImage);

            screenManager.getMain().playsfx = !screenManager.getMain().playsfx;
        }

        for(int i = 0; i < pseudoEnemyList.size(); i++) {
            Enemy enemy = pseudoEnemyList.get(i);
            enemy.update(delta);

            Tuple<Float, Float> pos = enemy.getPos();

            float x = pos.x,
                  y = pos.y;

            /* outside of screen -- remove enemy */
            if(x + enemy.getRadius() < -camera.viewportWidth || x - enemy.getRadius() > camera.viewportWidth ||
               y + enemy.getRadius() < -camera.viewportHeight || y - enemy.getRadius() > camera.viewportHeight) {
                pseudoEnemyList.remove(i--);
            }
        }

        while(pseudoEnemyList.size() < GDXConstants.MENU_ENEMY_NUMBER) {
            Enemy enemy = new Enemy(0, GDXConstants.PLAYER_MIN_RADIUS + GDXConstants.MENU_ENEMY_DMIN_RADIUS, GDXConstants.PLAYER_MIN_RADIUS + GDXConstants.MENU_ENEMY_DMAX_RADIUS);
            pseudoEnemyList.add(enemy);
        }

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(Enemy enemy: pseudoEnemyList)
            enemy.draw(shapeRenderer);
        pseudoPlayer.draw(shapeRenderer);
        shapeRenderer.end();

        spriteBatch.begin();
        font.setColor(1, 1, 1, 1);
        font.setScale(GDXConstants.MENU_TEXT_SCALE);
        font.drawMultiLine(spriteBatch, GDXConstants.MENU_TEXT, textx, texty, GDXConstants.MENU_TEXT_WIDTH, BitmapFont.HAlignment.CENTER);

        font.setScale(GDXConstants.MENU_CREDITS_SCALE);
        font.drawMultiLine(spriteBatch, GDXConstants.MENU_CREDITS, GDXConstants.MENU_CREDITS_X, GDXConstants.MENU_CREDITS_Y);

        spriteBatch.draw(logo, logox, logoy, GDXConstants.LOGO_WIDTH, GDXConstants.LOGO_HEIGHT);
        music.draw(spriteBatch);
        sfx.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        screenManager.getMain().adManager.setVisibility(false);
    }

    @Override
    public void dispose() {

    }
}
