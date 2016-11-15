package entity;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Used to represent a
 * mathematical bi-dimensional data structure.
 */
public class Matrix {
    private int[][] data;
    private int rows; // no. of rows
    private int cols; // no. of cols

    /**
     * Creates a new matrix of determined size.
     * @param r number of rows
     * @param c number of cols
     */
    public Matrix(int r, int c) {
        data = new int[r][c];
        this.rows = r;
        this.cols = c;
    }

    public Matrix(int[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return cols;
    }

    /**
     * Returns the element at given row and column.
     *         if reach out of bounds,
     *
     * @param r row no.
     * @param c column no.
     * @return the element
     */
    public int get(int r, int c) {
        return data[r][c];
    }

    public void set(int r, int c, int data) {
        this.data[r][c] = data;
    }

    public Matrix plus(Matrix other) {
        verifyPreconditions(this, other);
        throw new NotImplementedException();
    }

    private void verifyPreconditions(Matrix m1, Matrix m2) {
        if (m2.getRows() != m1.getRows() || m2.getColumns() != m1.getColumns())
            throw new InvalidMatrixAdditionException(m2, m1);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                builder.append(data[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }




    int getFirst() {
        return get(0, 0);
    }

    int getLast() {
        return get(rows - 1, cols - 1);
    }
}
