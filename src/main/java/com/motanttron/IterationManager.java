package com.motanttron;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calin on 02.12.2016.
 */
public class IterationManager {
    public static List<Iteration> getIterations(int noOps, int noThreads) {
        float ratio = (float) noOps / noThreads;
        List<Iteration> iterations = new ArrayList<>();
//        System.out.printf("ratio = %f\n", ratio);
        int r = (int) ratio;
        int start = 0;
        int end = start + r;
        for (int i = 0; i < noThreads; i++) {
            iterations.add(new Iteration(start, end));
            start = end; // [ )
            end = start + r;
        }/*
        boolean commaRatio = ratio != (int) ratio;
        if (commaRatio) {
            final Iteration iteration = iterations.get(iterations.size() - 1);
            iteration.end = iteration.end + 1;
        }*/
        return iterations;
    }
}
