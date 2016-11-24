package com.motanttron.operations;

import com.motanttron.Matrix;

/**
 * Created by calin on 21.11.2016.
 */
public class MatrixAdditionThread extends Thread {
    private final Matrix firstMatrix;
    private final Matrix secMatrix;
    private final int i;
    private final int j;
    private final Matrix resMatrix;

    public MatrixAdditionThread(Matrix m1, Matrix m2, int x, int y, Matrix result) {
        this.firstMatrix = m1;
        this.secMatrix = m2;
        this.i = x;
        this.j = y;
        this.resMatrix = result;
    }

    @Override
    public void run() {
        final int value = firstMatrix.get(i, j) + secMatrix.get(i, j);
        resMatrix.set(i, j, value);
    }
}
