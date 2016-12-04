package com.motanttron;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 03.12.2016.
 */
public class BalancedCutTest {

    @Test
    public void test() {
        int rows = 5;
        int cols = 5;
        final Matrix m1 = MatrixGenerator.generateRandom(rows, cols);
        final Matrix m2 = MatrixGenerator.generateRandom(rows, cols);
        final Matrix m3 = new Matrix(rows, cols);
        int noThreads = 2;

        BalancedCutter cutter = new BalancedCutter(new BalancedCutter.BalancedCutterArguments()
                .rows(rows)
                .cols(cols)
                .threads(noThreads));
        List<Section> sections = cutter.cut();

        assertEquals(new Section(new Position(0, 0), new Position(2, 2)), sections.get(0));
        assertEquals(new Section(new Position(2, 3), new Position(4, 4)), sections.get(1));
    /*
        final ExecutorService executor = Executors.newFixedThreadPool(noThreads);
        for (Section s : sections) {
            executor.submit(new MultiMultiplicationThread(s.from, s.to, m1, m2, m3))
        }
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

}
