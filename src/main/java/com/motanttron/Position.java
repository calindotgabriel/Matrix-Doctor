package com.motanttron;

/**
 * Created by calin on 03.12.2016.
 */
class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position advance(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }


    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position advanceX(int by) {
        return new Position(x + by, y);
    }

    public Position advanceY(int by) {
        return new Position(x, y + by);
    }
}
