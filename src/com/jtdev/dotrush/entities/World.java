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

import com.jtdev.dotrush.managers.ScreenManager;
import com.jtdev.dotrush.managers.GameManager;
import com.jtdev.dotrush.screens.EndScreen;
import com.jtdev.dotrush.utils.Logger;

public class World {
    private Logger logger;

    private ScreenManager screenManager;
    private GameManager gameManager;

    private int score;

    public World(ScreenManager screenManager) {
        gameManager = new GameManager(screenManager);

        logger = new Logger(this);
        this.screenManager = screenManager;
    }

    public void render(float delta) {
        if(gameManager.update(delta) != 0)
            screenManager.setScreen(new EndScreen(screenManager, gameManager.getScore()));
        else
            gameManager.draw();
    }

    public void pause() {
        gameManager.pause();
    }

    public void resume() {
        gameManager.resume();
    }
}
