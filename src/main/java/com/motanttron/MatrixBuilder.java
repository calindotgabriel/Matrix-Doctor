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



}
