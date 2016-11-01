package entity;

import exception.InvalidMatrixAdditionException;

/**
 * Created by paracelsus on 27/10/2016.
 */
public class Matrix {
    private int[][] _data;
    private int r; // no. of rows
    private int c; // no. of columns

    /**
     * Creates a new matrix of determined size.
     * @param r number of rows
     * @param c number of columns
     */
    public Matrix(int r, int c) {
        _data = new int[r][c];
        this.r = r;
        this.c = c;
    }

    public Matrix(int[][] _data) {
        this._data = _data;
        this.r = _data.length;
        this.c = _data[0].length;
    }

    public int getRows() {
        return r;
    }

    public int getColumns() {
        return c;
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
        return _data[r][c];
    }

    private void set(int r, int c, int data) {
        _data[r][c] = data;
    }

    public Matrix plus(Matrix other) throws InvalidMatrixAdditionException {
        verifyPreconditions(this, other);
        Matrix m3 = new Matrix(r, c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                m3.set(i, j, this.get(i, j) + other.get(i, j));
            }
        }
        return m3;
    }

    public Matrix parallelPlus(Matrix other, int threads) {
        verifyPreconditions(this, other);
//        assignZones()
    }

    public void show() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(_data[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private void verifyPreconditions(Matrix m1, Matrix m2) {
        if (m2.getRows() != m1.getRows() || m2.getColumns() != m1.getColumns())
            throw new InvalidMatrixAdditionException(m2, m1);
    }
}
