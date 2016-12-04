package com.motanttron;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 04.12.2016.
 * Should have small tests and big tests
 */
public class TestThreadedAddition {

    /**
     * This this I will consider a bottom-up approach
     * Starting with what the thread should get as params
     * and working from there to how it will be generated.
     */
    @Test
    public void testAddition() {
        final int rows = 50;
        final int cols = 50;
        final int threads = 3;
        final Matrix m1 = MatrixGenerator.generateIncremental(rows, cols);
        final Matrix m2 = MatrixGenerator.generateIncremental(rows, cols);
        final Matrix m3 = MatrixGenerator.generateEmpty(rows, cols);

        final List<Section> sections = Sectioner.getSections(rows, cols, threads);

        final ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (Section section : sections) {
            executor.submit(new MatrixMultiAdditionThread(section.from, section.to, m1, m2, m3));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Test
    public void testMultiplication() {
        final int rows = 50;
        final int cols = 50;
        final int threads = 3;
        final Matrix m1 = MatrixGenerator.generateIncremental(rows, cols);
        final Matrix m2 = MatrixGenerator.generateIncremental(rows, cols);
        final Matrix m3 = MatrixGenerator.generateEmpty(rows, cols);

        final List<Section> sections = Sectioner.getSections(rows, cols, threads);

        final ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (Section section : sections) {
            executor.submit(new MatrixMultiMultiplicationThread(section.from, section.to, m1, m2, m3));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        m3.show();
    }

    @Test
    public void test2() {
        final Matrix m = MatrixGenerator.generateIncremental(6, 6);
        m.showIndices();
        int threads = 2;
        System.out.println("threads = " + threads);

        final List<Section> sections = Sectioner.getSections(m.getRows(), m.getCols(), threads);

        for (Section section : sections) {
            System.out.println(section);
        }
        System.out.println();
    }

}
