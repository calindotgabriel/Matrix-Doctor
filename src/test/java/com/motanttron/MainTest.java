package com.motanttron;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calin on 28.11.2016.
 */
public class MainTest {
    @Test
    public void testProgram() {
        final MatrixDriver driver = new MatrixDriver();
        driver.setThreads(1);
        driver.runFirstScenario();
    }

    @Test
    public void testMath() {
        compute(24, 6);
    }

    private void compute(int noOps, int noThreads) {
        int r = noOps / noThreads;
        System.out.printf("ratio = %d\n", r);

        List<Iteration> iterations = new ArrayList<>();

        int firstStart = 0;
        int firstEnd = firstStart + r;
        int lastEnd = noOps;

        for(int i = 0 ; i < noThreads ; i++) {
            iterations.add(new Iteration(firstStart, firstEnd));
            firstStart = firstEnd ;
            firstEnd = firstStart + r;
        }

        for (Iteration iteration : iterations) {
            System.out.println(iteration);
        }
    }

    class Iteration {
        int start;
        int end;

        public Iteration(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("from %d to %d", start, end);
        }
    }
}