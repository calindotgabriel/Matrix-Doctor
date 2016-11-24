package com.motanttron;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by calin on 24.11.2016.
 */
public class BuilderTest {
    @Test
    public void build() {
        Matrix m = new MatrixBuilder().rows(5).cols(5).build();
    }
}
