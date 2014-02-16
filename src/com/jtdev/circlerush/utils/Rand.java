package com.jtdev.circlerush.utils;

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
