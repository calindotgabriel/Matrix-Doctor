package com.motanttron;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 24.11.2016.
 */
public class StringFileMatrixTest {

    @Test
    public void test2by2() throws IOException {
        process("m1.txt", new int[][]{
                {1, 2},
                {3, 4}
        });
    }

    @Test
    public void test3by3() throws IOException {
        process("m2.txt", new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        });
    }

    @Test
    public void test5by5() throws IOException {
        process("m3.txt", new int[][]{
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        });
    }

    private void process(String path, int[][] expected) {
        String m1 = Utils.safeStringFromFile(path);
        Matrix readMatrix = MatrixBuilder.fromString(m1);
        final Matrix expectedMatrix = new Matrix(expected);
        assertEquals(expectedMatrix, readMatrix);
    }
}
