package com.motanttron;

import com.motanttron.Matrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by calin on 21.11.2016.
 */
public class MatrixSortTest {

    @Test
    public void sort3() {
        Matrix m = new Matrix(new int[][] {
                {20, 9, 1},
                {5, 16, 6},
                {0, 3, 10}
        });
        m.sort();
        Matrix expected = new Matrix(new int[][] {
                {10, 6, 1},
                {3, 16, 9},
                {0, 5, 20}
        });
        assertEquals(expected, m);
    }


    @Test
    public void sort2() {
        Matrix m = new Matrix(new int[][] {
                {20, 9},
                {5, 16}
        });
        m.sort();
        Matrix expected = new Matrix(new int[][] {
                {16, 9},
                {5, 20}
        });
        assertEquals(expected, m);
    }
}
