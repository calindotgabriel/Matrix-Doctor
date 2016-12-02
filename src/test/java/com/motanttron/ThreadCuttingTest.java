package com.motanttron;

import org.junit.Test;

/**
 * Created by calin on 28.11.2016.
 */
public class ThreadCuttingTest {

    @Test
    public void testSeparation() {
        int threads = 2;
        final Matrix matrix = new Matrix(new int[][]{
                {0, 2, 3, 4},
                {5, 6, 7, 8},
                {8, 3, 2, 1}});
        final int rows = matrix.getRows();
        final int cols = matrix.getCols();
        final int a = rows / threads;
        System.out.print(String.format("%d%d w. %d", rows, cols, a));
    }
}
