/*
 * dotrush: a game where you get Dot Rush'd
 * Copyright (C) 2014 Aleksa Sarai <cyphar@cyphar.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
