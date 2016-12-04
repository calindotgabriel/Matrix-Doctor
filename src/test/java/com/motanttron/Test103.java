package com.motanttron;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        final Matrix m1 = MatrixGenerator.generateIncremental(4, 4);
        final Matrix m2 = MatrixGenerator.generateIncremental(4, 4);
        final Matrix m3 = MatrixGenerator.generateEmpty(4, 4);
        m1.showIndices();
        Position p1 = new Position(0, 0);
        Position p2 = new Position(2, 0);
        final MatrixMultiAdditionThread t = new MatrixMultiAdditionThread(p1, p2, m1, m2, m3);
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
     */
    /**
     * Start from (0, 0), For next, add by x { ratio - 1 } and by y { cols - 1 }and you get the first section
     * Add to start the ratio by x and to next increment the x by ratio
     * For a 4 x 4 matrix and 2 threads it should look like:
     *  [ ([0,0], [1, 3]), ([2,0), (3,3)) ]
     *
     * (0, 0), (1, 3) ok (2, 0), (3, 3) ok
     * startPoint = 0, 0
     * endPoint = 1, 3
     * startPoint = 2, 0
     * endPoint = 3, 3
     * startPoint = 4, 0 STOP because 4 >= numberOfRows
     *
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
        final Matrix m = MatrixGenerator.generateIncremental(4, 4);
        int threads = 4;
        final int rows = m.getRows();
        final int cols = m.getCols();
        final int ratio = rows / threads; // 2

        List<Section> sections = new ArrayList<>();
        Position start = new Position(0, 0);
        Position end = new Position(ratio - 1, cols - 1);
        sections.add(new Section(start, end));

        for (int i = 0 ; i < threads - 1; i ++) {
            start = start.advanceX(ratio);
            end = end.advanceX(ratio);
            sections.add(new Section(start, end));
        }

        System.out.println();

    }
}
