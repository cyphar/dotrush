package com.jtdev.dotrush.managers;

import com.jtdev.dotrush.db.IScoreDatabaseManager;

public class ScoreManager implements IScoreManager {
    private int highscore;
    private IScoreDatabaseManager dbManager;

    public ScoreManager(IScoreDatabaseManager dbManager) throws Exception {
        highscore = 0;
        this.dbManager = dbManager;
    }

    @Override
    public boolean isHighScore(int score) throws Exception {
        return score > dbManager.getHighScore();
    }

    @Override
    public int getHighScore() throws Exception {
        return dbManager.getHighScore();
    }

    @Override
    public int updHighScore(int score) throws Exception {
        dbManager.addScore(score);
        return score;
    }
}
