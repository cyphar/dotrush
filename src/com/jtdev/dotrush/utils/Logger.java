/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.utils;

import com.jtdev.dotrush.Constants;

public class Logger {
    private String name;

    public Logger(Object obj) {
        name = obj.getClass().getSimpleName();
    }

    public void log(String message) {
        if(Constants.DEBUG_LOG)
            System.out.println("[DEBUG] " + name + ": " + message);
    }

    public void warn(String message) {
        if(Constants.DEBUG_LOG)
            System.out.println("[WARN] " + name + ": " + message);
    }
}
