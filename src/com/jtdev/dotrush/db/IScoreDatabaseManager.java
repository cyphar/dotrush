/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.db;

public interface IScoreDatabaseManager {
    public int getHighScore();
    public void addScore(int score);
}
