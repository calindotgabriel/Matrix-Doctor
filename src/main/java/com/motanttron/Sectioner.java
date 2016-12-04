package com.motanttron;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calin on 04.12.2016.
 */
public class Sectioner {
    /**
     * For a given number of rows, columns and threads, this function cuts sections
     * as a start and an end
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
     * * Start from (0, 0), For next, add by x { ratio - 1 } and by y { cols - 1 }and you get the first section
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
     *
     * @return List of sections
     */
    public static List<Section> getSections(int rows, int cols, int threads) {
        List<Section> sections = new ArrayList<>();
        final int ratio = rows / threads;
        final int rest = rows % threads;

        Position start = new Position(0, 0);
        Position end = new Position(ratio - 1, cols - 1);
        sections.add(new Section(start, end)); // the first section

        for (int i = 0; i < threads - 1; i++) { // one section per thread
            start = start.advanceX(ratio);
            end = end.advanceX(ratio);
            sections.add(new Section(start, end));
        }
        if (rest != 0) {// load the last thread with what's left
            final Section lastSection = sections.get(sections.size() - 1);
            lastSection.to.x += rest;
        }

        return sections;
    }
}
