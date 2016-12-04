package com.motanttron;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 04.12.2016.
 */
public class Test103 {

    /**
     * This this I will consider a bottom-up approach
     * Starting with what the thread should get as params
     * and working from there to how it will be generated.
     */
    @Test
    public void test() {
        final int rows = 50;
        final int cols = 50;
        final int threads = 3;
        final Matrix m1 = MatrixGenerator.generateIncremental(rows, cols);
        final Matrix m2 = MatrixGenerator.generateIncremental(rows, cols);
        final Matrix m3 = MatrixGenerator.generateEmpty(rows, cols);
        m1.showIndices();
        final List<Section> sections = getSections(rows, cols, threads);

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

        m3.show();
    }

    /**
     * For a given number of threads, how do make a list of positions for every thread
     * Section has a start and an end position.
     * for (Section s : sections)
     * new MatrixMultiAdditionThread(s.start, s.end .... )
     * The number of threads should not be bigger than how many rows or columns, in my approach
     * For every thread there's a corresponding section for it to add.
     * For a 4 x 4 matrix and 2 threads it should look like:
     * [ ([0,0], [1, 3]), ([2,0), (3,3)) ]
     * t = 4 => [ [[0, 0], [0,3]], [[1,0], [1,3]] ...  ]
     * The algorithm works the following way:
     * Find the ratio between the  { number of rows } / { number of threads }
     * If no rest, the balancing is equal amongst threads.
     * Start from [0, 0] and then advance by the ratio on the rows.
     * For the first case, the next position will be [2, 0]
     * If rest, the value of rows % threads is actually how many rows are left "behind" at the end
     */
    /**
     * Start from (0, 0), For next, add by x { ratio - 1 } and by y { cols - 1 }and you get the first section
     * Add to start the ratio by x and to next increment the x by ratio
     * For a 4 x 4 matrix and 2 threads it should look like:
     * [ ([0,0], [1, 3]), ([2,0), (3,3)) ]
     * <p>
     * (0, 0), (1, 3) ok (2, 0), (3, 3) ok
     * startPoint = 0, 0
     * endPoint = 1, 3
     * startPoint = 2, 0
     * endPoint = 3, 3
     * startPoint = 4, 0 STOP because 4 >= numberOfRows
     * <p>
     * 6 x 6 matrix by 3 threads => r = 3
     * startPoint = 0, 0
     * endPoint = 1, 5
     * startPoint = 3, 0
     * endPoint = 5, 5
     * startPoint = 5, 0
     * endPoint =
     */
    @Test
    public void test2() {
        final Matrix m = MatrixGenerator.generateIncremental(6, 6);
        m.showIndices();
        int threads = 2;
        System.out.println("threads = " + threads);

        final List<Section> sections = getSections(m, threads);

        for (Section section : sections) {
            System.out.println(section);
        }
        System.out.println();
    }

    private List<Section> getSections(Matrix m, int threads) {
        final int rows = m.getRows();
        final int cols = m.getCols();
        return getSections(threads, rows, cols);
    }

    private List<Section> getSections(int rows, int cols, int threads) {
        final int ratio = rows / threads;
        System.out.println("ratio = " + ratio);
        final int rest = rows % threads;
        System.out.println("rest = " + rest);

        List<Section> sections = new ArrayList<>();
        Position start = new Position(0, 0);
        Position end = new Position(ratio - 1, cols - 1);
        sections.add(new Section(start, end));

        for (int i = 0; i < threads - 1; i++) {
            start = start.advanceX(ratio);
            end = end.advanceX(ratio);
            sections.add(new Section(start, end));
        }
        if (rest != 0) {
            final Section lastSection = sections.get(sections.size() - 1);
            lastSection.to.x += rest;
        }

        return sections;
    }
}
