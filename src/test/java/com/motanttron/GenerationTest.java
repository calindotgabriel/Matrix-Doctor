package com.motanttron;

import com.motanttron.MatrixBuilder;
import org.junit.Test;

/**
 * Created by calin on 24.11.2016.
 */
public class GenerationTest {
    @Test
    public void testGenerate() {
        final Matrix matrix = MatrixGenerator.generateRandom(6, 8);
        System.out.println(matrix);
    }
}
