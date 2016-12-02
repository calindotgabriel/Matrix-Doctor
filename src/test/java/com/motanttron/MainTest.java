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
//        compute(24, 6);
        float r = (float) 8.00;
        final boolean isIt = r == (int) r;
        System.out.println(isIt);

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


}