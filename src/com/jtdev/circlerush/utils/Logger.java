package com.jtdev.circlerush.utils;

import com.badlogic.gdx.Gdx;
import com.jtdev.circlerush.Constants;

public class Logger {
    private String name;

    public Logger(Object obj) {
        name = obj.getClass().getSimpleName() + "[DEBUG]";
    }

    public void log(String message) {
        if(Constants.DEBUG_LOG)
            Gdx.app.log(name, message);
    }
}
