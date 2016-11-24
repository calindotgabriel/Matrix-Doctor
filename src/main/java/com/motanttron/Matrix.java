package com.motanttron;

import com.motanttron.operations.MatrixAdditionThread;
import com.motanttron.operations.MatrixMultiplicationThread;

import java.util.ArrayList;
import java.util.Collection;

/**
 * com.motanttron.Matrix
 */
public class Matrix {

    private int[][] data;
    private int rows;
    private int cols;

    public Matrix(int[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    /**
     * Creates a empty matrix with given rows and cols.
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        this.data = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix() {

    }

    private int getRows() {
        return rows;
    }

    private int getCols() {
        return cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Adds this matrix and another in parallel.
     * A new thread is created for every addition operation.
     *
     * @param other the other matrix
     * @return obtained {@link Matrix}
     */
    public Matrix plus(Matrix other) {
        final Collection<Thread> threads = new ArrayList<Thread>();
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                final MatrixAdditionThread thread = new MatrixAdditionThread(this, other, i, j, result);
                threads.add(thread);
            }
        }
        for (Thread t : threads) {
            t.run();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void set(int x, int y, int value) {
        data[x][y] = value;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Returns the eleme
     *
     * @param x row position
     * @param y column position
     */
    public int get(int x, int y) {
        return data[x][y];
    }

    /**
     * Multiplies this matrix and another in parallel.
     * A new thread is created for every addition operation.
     *
     * @param other the other matrix
     * @return obtained {@link Matrix}
     */
    public Matrix multiply(Matrix other) {
        final Collection<Thread> threads = new ArrayList<Thread>();
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                for (int k = 0; k < this.getCols(); k++) {
                    final MatrixMultiplicationThread thread = new MatrixMultiplicationThread(this, other, i, j, k, result);
                    threads.add(thread);
                }
            }
        }
        for (Thread t : threads) {
            t.run();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            for (int i = 0; i < getRows() - 1; i++) {
                for (int j = 0; j < getCols() - 1; j++) {
                    if (i == j || i > j || i < j) {
                        int current = get(i, j);
                        int nextInDiag = get(i + 1, j + 1);
                        if (current > nextInDiag) { // then we should sort
                            set(i, j, nextInDiag);
                            set(i + 1, j + 1, current);
                            sorted = false;
                        } else {
                            sorted = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        Matrix other = (Matrix) obj;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (this.get(i, j) != other.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(Constants.NEWLINE);
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                builder.append(get(i, j)).append(Constants.SPACE);
            }
            builder.append(Constants.NEWLINE);
        }
        return builder.toString();
    }

    // print its string representation
    public void show() {
        System.out.print(toString());
    }
}
