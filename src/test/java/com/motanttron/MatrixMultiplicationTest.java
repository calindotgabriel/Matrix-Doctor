package com.motanttron;

import com.motanttron.Matrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixMultiplicationTest {

    public static final Matrix MULTI_M1 = new Matrix(new int[][]{
            {1, 2},
            {3, 4}
    });
    public static final Matrix MULTI_M2 = new Matrix(new int[][]{
            {4, 5},
            {6, 7}
    });

    @Test
    public void multiply() {
        final Matrix expected = new Matrix(new int[][]{
                {16, 19},
                {36, 43}
        });
        assertEquals(expected, MULTI_M1.multiply(MULTI_M2));
    }
}