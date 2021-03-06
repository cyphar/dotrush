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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.jtdev.dotrush.GDXConstants;
import com.jtdev.dotrush.managers.IScoreManager;
import com.jtdev.dotrush.managers.InputManager;
import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.utils.Button;
import com.jtdev.dotrush.utils.Logger;

public class EndScreen implements Screen {
    private Logger logger;
    private ScreenManager screenManager;
    private InputManager inputManager;

    private SpriteBatch spriteBatch;
    private BitmapFont font;

    private Button music, sfx, play, menu;
    private float texty, textx;
    private String text;

    public EndScreen(ScreenManager screenManager, int score) {
        screenManager.getMain().adManager.setVisibility(true);

        logger = new Logger(this);
        this.screenManager = screenManager;
        inputManager = screenManager.getMain().inputManager;

        IScoreManager scoreManager = screenManager.getMain().scoreManager;

        boolean ishighscore = scoreManager.isHighScore(score);
        int highscore = scoreManager.getHighScore();
        if(ishighscore)
            highscore = scoreManager.updHighScore(score);

        if(highscore < 0)
            text = String.format(GDXConstants.END_TEXT_NOHIGHSCORE, score);
        else if(ishighscore)
            text = String.format(GDXConstants.END_TEXT_HIGHSCORE, score);
        else
            text = String.format(GDXConstants.END_TEXT, score, highscore);

        spriteBatch = new SpriteBatch();

        TextureRegion musicImage = screenManager.getMain().playmusic ? screenManager.getMain().muteMusicImage : screenManager.getMain().unmuteMusicImage;
        music = new Button(GDXConstants.BUTTON_MUSIC_X, GDXConstants.BUTTON_MUSIC_Y, musicImage);

        TextureRegion sfxImage = screenManager.getMain().playsfx ? screenManager.getMain().muteSfxImage : screenManager.getMain().unmuteSfxImage;
        sfx = new Button(GDXConstants.BUTTON_SFX_X, GDXConstants.BUTTON_SFX_Y, sfxImage);

        play = new Button(GDXConstants.END_BUTTON_PLAY_X, GDXConstants.END_BUTTON_PLAY_Y, screenManager.getMain().playImage);
        menu = new Button(GDXConstants.END_BUTTON_MENU_X, GDXConstants.END_BUTTON_MENU_Y, screenManager.getMain().menuImage);

        music.setScale(GDXConstants.BUTTON_MUSIC_SCALE_X, GDXConstants.BUTTON_MUSIC_SCALE_Y);
        sfx.setScale(GDXConstants.BUTTON_SFX_SCALE_X, GDXConstants.BUTTON_SFX_SCALE_Y);
        play.setScale(GDXConstants.END_BUTTON_PLAY_SCALE_X, GDXConstants.END_BUTTON_PLAY_SCALE_Y);
        menu.setScale(GDXConstants.END_BUTTON_MENU_SCALE_X, GDXConstants.END_BUTTON_MENU_SCALE_Y);

        texty = GDXConstants.END_TEXT_OFFSET_Y + GDXConstants.VIRTUAL_SCREEN_HEIGHT / 2;
        textx = GDXConstants.END_TEXT_OFFSET_X;

        font = screenManager.getMain().gamefont;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        if(music.justPressed(inputManager)) {
            if(screenManager.getMain().music.isPlaying()) {
                screenManager.getMain().music.stop();
                music.setImage(screenManager.getMain().unmuteMusicImage);
            } else {
                screenManager.getMain().music.play();
                music.setImage(screenManager.getMain().muteMusicImage);
            }

            screenManager.getMain().playmusic = screenManager.getMain().music.isPlaying();
        }

        if(sfx.justPressed(inputManager)) {
            if(screenManager.getMain().playsfx)
                sfx.setImage(screenManager.getMain().unmuteSfxImage);
            else
                sfx.setImage(screenManager.getMain().muteSfxImage);

            screenManager.getMain().playsfx = !screenManager.getMain().playsfx;
        }

        if(play.justPressed(inputManager))
            screenManager.setScreen(new GameScreen(screenManager));

        if(menu.justPressed(inputManager))
            screenManager.setScreen(new StartScreen(screenManager));

        spriteBatch.begin();
        music.draw(spriteBatch);
        sfx.draw(spriteBatch);
        play.draw(spriteBatch);
        menu.draw(spriteBatch);

        font.setColor(1, 1, 1, 1);
        font.setScale(GDXConstants.END_TEXT_SCALE);
        font.drawMultiLine(spriteBatch, text, textx, texty, GDXConstants.END_TEXT_WIDTH, BitmapFont.HAlignment.CENTER);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        screenManager.getMain().adManager.setVisibility(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        screenManager.getMain().adManager.setVisibility(true);
    }

    @Override
    public void dispose() {

    }
}
