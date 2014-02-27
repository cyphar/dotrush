/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
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
