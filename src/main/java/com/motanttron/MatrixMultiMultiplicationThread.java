package com.motanttron;

/**
 * Created by calin on 04.12.2016.
 */
public class MatrixMultiMultiplicationThread implements Runnable {

    private Position p1;
    private Position p2;
    private Matrix m1;
    private Matrix m2;
    private Matrix m3;

    public MatrixMultiMultiplicationThread(Position p1, Position o2, Matrix m1, Matrix m2, Matrix m3) {
        this.p1 = p1;
        this.p2 = o2;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    @Override
    public void run() {
        for (int i = p1.x; i <= p2.x; i++)
            for (int j = p1.y; j <= p2.y; j++)
                for (int k = p1.y; k < p2.y; k++) {
                    final int value = m1.get(i, k) * m2.get(k, j);
                    m3.set(i, j, value);
                }
    }
}
