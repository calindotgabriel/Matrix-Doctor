package com.motanttron;

/**
 * Created by calin on 03.12.2016.
 */
class Section {
    Position from;
    Position to;

    public Section(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Section{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
