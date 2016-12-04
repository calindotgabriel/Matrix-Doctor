package com.motanttron;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by calin on 28.11.2016.
 */
public class MatrixGenerator {

    public static Matrix generateRandom(int rows, int cols) {
        final Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                final int randomInt = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
                m.set(i, j, randomInt);
            }
        }
        return m;
    }

    public static Matrix generateIncremental(int rows, int cols) {
        final Matrix m = new Matrix(rows, cols);
        int from = 0;
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                m.set(i, j, from++);
            }
        }
        return m;
    }

    public static Matrix generateEmpty(int rows, int cols) {
        return new Matrix(rows, cols);
    }
}
