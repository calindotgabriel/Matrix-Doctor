package com.motanttron;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by calin on 03.12.2016.
 */
public class Test101 {

    @Test
    public void test() {
//        compute(7, 2);
        compute(12, 12, 3);
    }

    private void compute(int rows, int cols, int threads) {
        System.out.printf("For %d rows and %d threads\n", rows, threads);
        final Matrix m = MatrixGenerator.generateIncremental(rows, cols);
        m.showIndices();
        final Stack<Position> stack = buildBreakPointStack(rows, cols, threads);
        System.out.println();
        System.out.println(Arrays.toString(stack.toArray()));

    }

    private Stack<Position> buildBreakPointStack(int rows, int cols, int threads) {
        final Stack<Position> q = new Stack<>();

        int ratio = rows / threads;
        int r = rows % threads;

        int point = rows - ratio;
        while (point > 0) {
            q.add(new Position(point, cols - 1));
            point = point - ratio;
            if (point == r) break;
        }
        return q;
    }

}
