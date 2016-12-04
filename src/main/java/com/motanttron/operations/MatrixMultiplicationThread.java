package com.motanttron.operations;

import com.motanttron.Matrix;

/**
 * Thread class used to make a multiplication operation given 3 distinct coordinates in a BI-Dimensional space.
 * It computes the required value in a separate thread.
 */
public class MatrixMultiplicationThread extends Thread {
    private final Matrix firstMatrix;
    private final Matrix secMatrix;
    private final int i;
    private final int j;
    private final int k;
    private final Matrix resultMatrix;

    public MatrixMultiplicationThread(Matrix m1, Matrix m2, int i, int j, int k, Matrix result) {
        this.firstMatrix = m1;
        this.secMatrix = m2;
        this.i = i;
        this.j = j;
        this.k = k;
        this.resultMatrix = result;
    }

    @Override
    public void run() {
        final int value = firstMatrix.get(i, k) * secMatrix.get(k, j);
        resultMatrix.set(i, j, resultMatrix.get(i, j) + value);
    }
}
