package com.jtdev.dotrush.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.jtdev.dotrush.Constants;
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
    private int score;

    private Button music, play, menu;
    private float texty, textx;

    public EndScreen(ScreenManager screenManager, int score) {
        this.screenManager = screenManager;
        this.score = score;
        inputManager = screenManager.getMain().inputManager;

        spriteBatch = new SpriteBatch();

        TextureRegion musicImage = screenManager.getMain().playmusic ? screenManager.getMain().muteImage : screenManager.getMain().unmuteImage;
        music = new Button(Constants.BUTTON_MUSIC_X, Constants.BUTTON_MUSIC_Y, Constants.BUTTON_MUSIC_SCALE, musicImage);
        play = new Button(Constants.END_BUTTON_PLAY_X, Constants.END_BUTTON_PLAY_Y, Constants.END_BUTTON_PLAY_SCALE, screenManager.getMain().playImage);
        menu = new Button(Constants.END_BUTTON_MENU_X, Constants.END_BUTTON_MENU_Y, Constants.END_BUTTON_MENU_SCALE, screenManager.getMain().menuImage);

        texty = Constants.END_TEXT_OFFSET_Y + Constants.SCREEN_HEIGHT / 2;
        textx = Constants.END_TEXT_OFFSET_X;

        font = screenManager.getMain().gamefont;
        logger = new Logger(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        if(music.justPressed(inputManager)) {
            logger.log("Music just pressed.");

            if(screenManager.getMain().music.isPlaying()) {
                screenManager.getMain().music.stop();
                music.setImage(screenManager.getMain().unmuteImage);
            } else {
                screenManager.getMain().music.play();
                music.setImage(screenManager.getMain().muteImage);
            }

            screenManager.getMain().playmusic = screenManager.getMain().music.isPlaying();
        }

        if(play.justPressed(inputManager)) {
            logger.log("Play just pressed.");
            screenManager.setScreen(new GameScreen(screenManager));
        }

        if(menu.justPressed(inputManager)) {
            logger.log("Menu just pressed.");
            screenManager.setScreen(new StartScreen(screenManager));
        }

        spriteBatch.begin();
        music.draw(spriteBatch);
        play.draw(spriteBatch);
        menu.draw(spriteBatch);

        String text = String.format(Constants.END_TEXT, score);
        font.setColor(1, 1, 1, 1);
        font.setScale(Constants.END_TEXT_SCALE);
        font.drawMultiLine(spriteBatch, text, textx, texty, Constants.END_TEXT_WIDTH, BitmapFont.HAlignment.CENTER);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
