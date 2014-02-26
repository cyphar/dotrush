/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.managers;

public interface IScoreManager {
    public boolean isHighScore(int score) throws Exception;
    public int getHighScore() throws Exception;
    public int updHighScore(int score) throws Exception;
}
