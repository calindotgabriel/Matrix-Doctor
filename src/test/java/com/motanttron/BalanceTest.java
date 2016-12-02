package com.motanttron;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 28.11.2016.
 */
public class BalanceTest {

    @Test
    public void test24by2() {
        final Iterations iterations = getIterations(24, 2);
        assertEquals(iteration(0, 12), iterations.get(0));
        assertEquals(iteration(12, 24), iterations.get(1));
    }

    @Test
    public void test16by2() {
        final Iterations iterations = getIterations(16, 2);
        assertEquals(iteration(0, 8), iterations.get(0));
        assertEquals(iteration(8, 16), iterations.get(1));
    }

    @Test
    public void test24by4() {
        final Iterations iterations = getIterations(24, 4);
        assertEquals(iteration(0, 6), iterations.get(0));
        assertEquals(iteration(6, 12), iterations.get(1));
        assertEquals(iteration(12, 18), iterations.get(2));
        assertEquals(iteration(18, 24), iterations.get(3));
    }

    @Test
    public void test24by3() {
        final Iterations iterations = getIterations(24, 3);
        assertEquals(iteration(0, 8), iterations.get(0));
        assertEquals(iteration(8, 16), iterations.get(1));
        assertEquals(iteration(16, 24), iterations.get(2));
    }

    @Test
    public void test25by2() {
        final Iterations iterations = getIterations(25, 2);
        assertEquals(iteration(0, 12), iterations.get(0));
        assertEquals(iteration(12, 25), iterations.get(1));
    }

    @Test
    public void test27by2() {
        final Iterations iterations = getIterations(27, 2);
        assertEquals(iteration(0, 13), iterations.get(0));
        assertEquals(iteration(13, 27), iterations.get(1));
    }

    private Iterations getIterations(int noOps, int noThreads) {
        float ratio = (float) noOps / noThreads;
        System.out.printf("ratio = %f\n", ratio);
        int r = (int) ratio;
        Iterations iterations = new Iterations(ratio);
        int start = 0;
        int end = start + r;
        for (int i = 0; i < noThreads; i++) {
            iterations.add(start, end);
            start = end;
            end = start + r;
        }
        iterations.incrementLastIfNecessary();
        return iterations;
    }

    private Iteration iteration(int s, int e) {
        return new Iteration(s, e);
    }

    class Iterations {
        float ratio;
        List<Iteration> iterations;

        public Iterations(float ratio) {
            this.ratio = ratio;
            iterations = new ArrayList<>();
        }

        public Iteration get(int i) {
            return iterations.get(i);
        }

        public void add(int s, int e) {
            iterations.add(new Iteration(s, e));
        }

        public void incrementLastIfNecessary() {
            final boolean imparity = ratio != (int) ratio;
            if (imparity) {
                final Iteration lastIteration = get(iterations.size() - 1);
                lastIteration.end = lastIteration.end + 1;
            }
        }
    }

}