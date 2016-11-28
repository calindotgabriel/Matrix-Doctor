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

    /**
     * Reads a matrix from a string input.
     * @param matrix as a string.
     * FORMAT:
     *               {noRows}
     *               {noCols}
     *               {0,0} {0, 1} ... {0, noCols}
     *               {1,0} {1, 1} ... {1, noCols}
     *               ...........................
     *               ...........................
     *               {noRows, 0} .......... {noRows, noCols}
     */
    public static Matrix fromString(String matrix) {
        String s = matrix;
        String lines[] = s.split("\\r?\\n");
        final int rows = Integer.parseInt(lines[0]);
        final int cols = Integer.parseInt(lines[1]);
        int[][] data = new int[rows][cols];
        for (int i = 2; i < lines.length; i++) { // starting from 2 because first are reserved for rows and cols
            final String line = lines[i];
            final String[] arrayAsString = line.split(" ");
            int[] array = new int[arrayAsString.length];
            for (int j = 0; j < arrayAsString.length; j++) {
                final int e = Integer.parseInt(arrayAsString[j]);
                array[j] = e;
            }
            data[i - 2] = array;
        }
        return new Matrix(data);
    }
}
