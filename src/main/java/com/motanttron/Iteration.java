package com.motanttron;

/**
 * Created by calin on 02.12.2016.
 */
public class Iteration {
    int start;
    int end;

    public Iteration(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Iteration other = (Iteration) obj;
        return this.start == other.start && this.end == other.end;
    }

    @Override
    public String toString() {
        return String.format("from %d to %d", start, end);
    }
}
