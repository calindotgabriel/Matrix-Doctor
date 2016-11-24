package com.motanttron;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }
}
