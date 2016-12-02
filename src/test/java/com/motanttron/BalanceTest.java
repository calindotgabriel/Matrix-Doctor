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
        final List<Iteration> iterations = getIterations(24, 2);
        assertEquals(iteration(0, 12), iterations.get(0));
        assertEquals(iteration(12, 24), iterations.get(1));
    }

    @Test
    public void test24by4() {
        final List<Iteration> iterations = getIterations(24, 4);
        assertEquals(iteration(0, 6), iterations.get(0));
        assertEquals(iteration(6, 12), iterations.get(1));
        assertEquals(iteration(12, 18), iterations.get(2));
        assertEquals(iteration(18, 24), iterations.get(3));
    }

    private List<Iteration> getIterations(int noOps, int noThreads) {
        int r = noOps / noThreads;
//        System.out.printf("ratio = %d\n", r);
        List<Iteration> iterations = new ArrayList<>();
        int firstStart = 0;
        int firstEnd = firstStart + r;
//        int lastEnd = noOps;
        for (int i = 0; i < noThreads; i++) {
            iterations.add(new Iteration(firstStart, firstEnd));
            firstStart = firstEnd;
            firstEnd = firstStart + r;
        }
        return iterations;
    }

    private Iteration iteration(int s, int e) {
        return new Iteration(s, e);
    }

    class Iteration {
        int start;
        int end;

        public Iteration(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            Iteration other = (Iteration) obj;
            return this.start == other.start && this.end == other.end;
        }

        @Override
        public String toString() {
            return String.format("from %d to %d", start, end);
        }
    }
}