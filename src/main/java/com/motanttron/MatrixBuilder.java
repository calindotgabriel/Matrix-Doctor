package com.motanttron;

/**
 * Created by calin on 24.11.2016.
 */
public class MatrixBuilder {

    private final Matrix matrix;

    public MatrixBuilder() {
        this.matrix = new Matrix();
    }

    public MatrixBuilder rows(int rows) {
        matrix.setRows(rows);
        return this;
    }

    public MatrixBuilder cols(int cols) {
        matrix.setCols(cols);
        return this;
    }
}
