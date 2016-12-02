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
        final List<Iteration> iterations = IterationManager.getIterations(24, 2);
        assertEquals(iteration(0, 12), iterations.get(0));
        assertEquals(iteration(12, 24), iterations.get(1));
    }

    @Test
    public void test16by2() {
        final List<Iteration> iterations = IterationManager.getIterations(16, 2);
        assertEquals(iteration(0, 8), iterations.get(0));
        assertEquals(iteration(8, 16), iterations.get(1));
    }

    @Test
    public void test24by4() {
        final List<Iteration> iterations = IterationManager.getIterations(24, 4);
        assertEquals(iteration(0, 6), iterations.get(0));
        assertEquals(iteration(6, 12), iterations.get(1));
        assertEquals(iteration(12, 18), iterations.get(2));
        assertEquals(iteration(18, 24), iterations.get(3));
    }

    @Test
    public void test24by3() {
        final List<Iteration> iterations = IterationManager.getIterations(24, 3);
        assertEquals(iteration(0, 8), iterations.get(0));
        assertEquals(iteration(8, 16), iterations.get(1));
        assertEquals(iteration(16, 24), iterations.get(2));
    }

    @Test
    public void test25by2() {
        final List<Iteration> iterations = IterationManager.getIterations(25, 2);
        assertEquals(iteration(0, 12), iterations.get(0));
        assertEquals(iteration(12, 25), iterations.get(1));
    }

    private Iteration iteration(int s, int e) {
        return new Iteration(s, e);
    }

}