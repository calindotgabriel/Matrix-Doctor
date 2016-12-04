package com.motanttron;

/**
 * Created by calin on 04.12.2016.
 */
public class MatrixMultiAdditionThread implements Runnable{
    private Position p1;
    private Position p2;
    private Matrix m1;
    private Matrix m2;
    private Matrix m3;

    public MatrixMultiAdditionThread(Position p1, Position p2, Matrix m1, Matrix m2, Matrix m3) {
        // should verify arguments
        this.p1 = p1;
        this.p2 = p2;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    @Override
    public void run() {
        final long id = Thread.currentThread().getId();
        for (int i = p1.x; i <= p2.x; i++) {
            for (int j = p1.y; j <= p2.y; j++) {
                System.out.printf("Thread {%d} should add [%d,%d]\n", id, i, j);
                m3.set(i, j, m1.get(i, j) + m2.get(i, j));
            }
        }
    }
}
