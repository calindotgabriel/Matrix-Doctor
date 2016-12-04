package com.motanttron.operations;

import com.motanttron.Matrix;

/**
 * Thread class used to make a multiplication operation given 3 distinct coordinates in a BI-Dimensional space.
 * It computes the required value in a separate thread.
 */
class MatrixSortingThread extends Thread {
    private final Matrix matrix;
    private final int i;
    private final int j;

    MatrixSortingThread(Matrix m1, int i, int j) {
        this.matrix = m1;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
    }
}
