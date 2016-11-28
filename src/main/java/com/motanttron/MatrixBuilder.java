package com.motanttron;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by calin on 24.11.2016.
 */
public class MatrixBuilder {

    private final Matrix matrix;
    private int rows;
    private int cols;

    public MatrixBuilder() {
        this.matrix = new Matrix();
    }

    public MatrixBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    public MatrixBuilder cols(int cols) {
        this.cols = cols;
        return this;
    }

    public Matrix build() {
        return new Matrix(rows, cols);
    }

    public static Matrix fromString(String matrix) {
        String s = matrix.trim().replaceAll("\n", "").replaceAll("\r", "").replaceAll(" ", "");
        int rows = Character.getNumericValue(s.charAt(0));
        int cols = Character.getNumericValue(s.charAt(1));
        final Matrix m = new Matrix(rows, cols);
        int i = 2; // 0 and 1 are rows and cols
        int r = 0, c = 0;
        List<Integer> numbers = new ArrayList<>();
        while (i < s.length()) {
            final int number = Character.getNumericValue(s.charAt(i));
            numbers.add(number);
            i++;
        }
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                m.set(j, k, numbers.get(j+k));
            }
        }
        return m;
    }
}
