package com.motanttron;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 24.11.2016.
 */
public class StringFileMatrixTest {

    @Test
    public void fromFile() throws IOException {
        String m1 = Utils.safeStringFromFile("m1.txt");
        Matrix readMatrix = MatrixBuilder.fromString(m1);
        final Matrix empty5by5 = new MatrixBuilder().rows(5).cols(5).build();
        assertEquals(empty5by5, readMatrix);
    }
}
