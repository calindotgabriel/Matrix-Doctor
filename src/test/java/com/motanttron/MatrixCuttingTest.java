package com.motanttron;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 28.11.2016.
 */
public class MatrixCuttingTest {
    @Test
    public void testCutting() {
        final Matrix matrix = new Matrix(new int[][]{
                {0, 2, 3, 4},
                {5, 6, 7, 8}});

        final Matrix cut = matrix.cut(0, 0, 1, 1);
        final Matrix expected = new Matrix(new int[][]{
                {0, 2},
                {5, 6}});
        assertEquals(expected, cut);
    }
}
