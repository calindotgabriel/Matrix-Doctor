package com.motanttron;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by calin on 03.12.2016.
 */
public class Test102 {

    @Test
    public void test() {
        final int rowsNcols = 6;
        final int threads = 2;


        final Matrix inc = MatrixGenerator.generateIncremental(rowsNcols, rowsNcols);
        final List<Iteration> its = IterationManager.getIterations(rowsNcols * rowsNcols, threads);

        List<Section> sections = new ArrayList<>();
        for (Iteration it : its) {
            final Position start = inc.findPosition(it.start);
            final Position end = inc.findPosition(it.end);
            final Section section = new Section(start, end);
            sections.add(section);
        }
        for (Section section : sections) {
            System.out.println(section);
        }
    }

}
