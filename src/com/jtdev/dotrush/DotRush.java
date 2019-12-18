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

package com.jtdev.dotrush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

import com.jtdev.dotrush.managers.*;
import com.jtdev.dotrush.screens.SplashScreen;
import com.jtdev.dotrush.utils.Logger;

public class DotRush extends Game {
    private Logger logger;
    private ScreenManager screenManager;

    public InputManager inputManager;

    public BitmapFont gamefont;
    public Camera camera;

    public Music music;
    public boolean playmusic;

    public Sound growSound, deathSound;
    public boolean playsfx;


    public TextureRegion muteMusicImage, unmuteMusicImage,
                         muteSfxImage, unmuteSfxImage,
                         playImage, menuImage;
    public AdManager adManager;

    public IScoreManager scoreManager;

    public DotRush(AdManager adManager, IScoreManager scoreManager) {
        this.adManager = adManager;
        this.scoreManager = scoreManager;
    }

    @Override
    public void create() {
        logger = new Logger(this);
        logger.log("starting game in type: " + Gdx.app.getType());
        logger.log("game is in screen " + GDXConstants.SCREEN_WIDTH + "x" + GDXConstants.SCREEN_HEIGHT);

        gamefont = new BitmapFont(Gdx.files.internal(GDXConstants.GAME_FONT_PATH), false);

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        camera = new OrthographicCamera(GDXConstants.VIRTUAL_SCREEN_WIDTH, GDXConstants.VIRTUAL_SCREEN_HEIGHT);
        camera.translate(GDXConstants.VIRTUAL_SCREEN_WIDTH / 2, GDXConstants.VIRTUAL_SCREEN_HEIGHT / 2, 0);
        camera.update();

        screenManager = new ScreenManager(this);
        screenManager.setScreen(new SplashScreen(screenManager));

        Texture buttonMap = new Texture(Gdx.files.internal(GDXConstants.BUTTON_IMAGE_PATH));
        playImage   = new TextureRegion(buttonMap, GDXConstants.BUTTON_PLAY_IMGX, GDXConstants.BUTTON_PLAY_IMGY, GDXConstants.BUTTON_WIDTH, GDXConstants.BUTTON_HEIGHT);
        menuImage   = new TextureRegion(buttonMap, GDXConstants.BUTTON_MENU_IMGX, GDXConstants.BUTTON_MENU_IMGY, GDXConstants.BUTTON_WIDTH, GDXConstants.BUTTON_HEIGHT);

        muteMusicImage   = new TextureRegion(buttonMap, GDXConstants.BUTTON_MUTE_MUSIC_IMGX, GDXConstants.BUTTON_MUTE_MUSIC_IMGY, GDXConstants.BUTTON_WIDTH, GDXConstants.BUTTON_HEIGHT);
        unmuteMusicImage = new TextureRegion(buttonMap, GDXConstants.BUTTON_UNMUTE_MUSIC_IMGX, GDXConstants.BUTTON_UNMUTE_MUSIC_IMGY, GDXConstants.BUTTON_WIDTH, GDXConstants.BUTTON_HEIGHT);

        muteSfxImage   = new TextureRegion(buttonMap, GDXConstants.BUTTON_MUTE_SFX_IMGX, GDXConstants.BUTTON_MUTE_SFX_IMGY, GDXConstants.BUTTON_WIDTH, GDXConstants.BUTTON_HEIGHT);
        unmuteSfxImage = new TextureRegion(buttonMap, GDXConstants.BUTTON_UNMUTE_SFX_IMGX, GDXConstants.BUTTON_UNMUTE_SFX_IMGY, GDXConstants.BUTTON_WIDTH, GDXConstants.BUTTON_HEIGHT);

        /* music is courtesy of http://radtunez.com/ */
        music = Gdx.audio.newMusic(Gdx.files.internal(GDXConstants.GAME_MUSIC_PATH));
        music.setLooping(true);
        music.setVolume(GDXConstants.GAME_MUSIC_VOLUME);
        playmusic = true;

        /* sfx */
        growSound = Gdx.audio.newSound(Gdx.files.internal(GDXConstants.GROW_SOUND_PATH));
        deathSound = Gdx.audio.newSound(Gdx.files.internal(GDXConstants.DEATH_SOUND_PATH));
        playsfx = true;

        setScreen(screenManager);
    }

    @Override
    public void resume() {
        music.stop();
        screenManager.setScreen(new SplashScreen(screenManager));
    }

}
