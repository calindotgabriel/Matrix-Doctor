package com.motanttron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by calin on 03.12.2016.
 */
public class BalancedCutter {

    private final BalancedCutterArguments args;

    public BalancedCutter(BalancedCutterArguments args) {
        this.args = args;
    }

    public List<Section> cut() {
        final List<Section> list = new ArrayList<>();
        final int rowRatio = args.rows / args.threads;
        final int colRatio = args.cols / args.threads;

        final Position start = new Position(0, 0);
        final Position end = new Position(args.rows, args.cols);

//        while (start.canAdvance(end))
//        final Position next = start.advance(rowRatio, colRatio);
        return list;
    }

    public static class BalancedCutterArguments {
        private int rows;
        private int cols;
        private int threads;

        public BalancedCutterArguments rows(int rows) {
            this.rows = rows;
            return this;
        }

        public BalancedCutterArguments cols(int cols) {
            this.cols = cols;
            return this;
        }

        public BalancedCutterArguments threads(int noThreads) {
            this.threads = noThreads;
            return this;
        }
    }
}
