package com.motanttron;

import org.junit.Test;

/**
 * Created by calin on 24.11.2016.
 */
public class BuilderTest {
    @Test
    public void build() {
        new MatrixBuilder().rows(5).cols(5);
    }
}
