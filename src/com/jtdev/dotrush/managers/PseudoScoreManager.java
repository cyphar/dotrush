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

package com.jtdev.dotrush.managers;

import com.jtdev.dotrush.utils.Logger;
import java.sql.SQLException;

public class PseudoScoreManager implements IScoreManager {
    private Logger logger;

    public PseudoScoreManager() {
        logger = new Logger(this);
        logger.warn("*** PSEUDO ScoreManager in use ***");
    }

    @Override
    public boolean isHighScore(int score) {
        logger.warn("PSEUDO ScoreManager -- isHighScore(*) == false");
        return false;
    }

    @Override
    public int getHighScore() {
        logger.warn("PSEUDO ScoreManager -- getHighScore() == -1");
        return -1;
    }

    @Override
    public int updHighScore(int score) {
        logger.warn("PSEUDO ScoreManager -- updHighScore(*) == -1");
        return -1;
    }
}
