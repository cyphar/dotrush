package com.jtdev.dotrush.db;

public interface IScoreDatabaseManager {
    public int getHighScore() throws Exception;
    public void addScore(int score) throws Exception;
}
