package com.manojkhannakm.circles.util;

import java.util.Random;

/**
 * @author Manoj Khanna
 */

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    public static int nextInt(int max) {
        return RANDOM.nextInt(max + 1);
    }

    public static int nextInt(int min, int max) {
        return min + nextInt(max - min);
    }

    public static long nextLong(long max) {
        return Math.abs(RANDOM.nextLong()) % (max + 1);
    }

    public static long nextLong(long min, long max) {
        return min + nextLong(max - min);
    }

    public static float nextFloat(float max) {
        return max * RANDOM.nextFloat();
    }

    public static float nextFloat(float min, float max) {
        return min + nextFloat(max - min);
    }

    public static double nextDouble(double max) {
        return max * RANDOM.nextDouble();
    }

    public static double nextDouble(double min, double max) {
        return min + nextDouble(max - min);
    }

}
