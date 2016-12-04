package com.motanttron;

/**
 * Created by calin on 04.12.2016.
 */
public class MatrixMultiAdditionThread {
    public MatrixMultiAdditionThread(Position p1, Position p2, Matrix m1, Matrix m2, Matrix m3) {
        // should verify arguments
        for (int i = p1.x; i <= p2.x; i++) {
            for (int j = p1.y; j <= p2.y; j++) {
                System.out.printf("Should add [%d,%d]\n", i, j);
            }
        }
    }
}
