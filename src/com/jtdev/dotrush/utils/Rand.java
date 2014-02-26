/* Copyright (C) JT-Dev 2014
 * Created, authored and designed by Cyphar.
 * This project is *not* open source (unfortunately).
 */

package com.jtdev.dotrush.utils;

import java.lang.Math;

public class Rand {
    public static int getRange(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }

    public static float getRange(float min, float max) {
        return min + (float)(Math.random() * (max - min));
    }

    public static double getRange(double min, double max) {
        return min + Math.random() * (max - min);
    }

    public static boolean getBool() {
        return Rand.getRange(0, 1) == 1;
    }
}
