/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.managers;

import com.jtdev.dotrush.db.IScoreDatabaseManager;

public class ScoreManager implements IScoreManager {
    private int highscore;
    private IScoreDatabaseManager dbManager;

    public ScoreManager(IScoreDatabaseManager dbManager) {
        highscore = 0;
        this.dbManager = dbManager;
    }

    @Override
    public boolean isHighScore(int score) {
        return score > dbManager.getHighScore();
    }

    @Override
    public int getHighScore() {
        return dbManager.getHighScore();
    }

    @Override
    public int updHighScore(int score) {
        dbManager.addScore(score);
        return score;
    }
}
