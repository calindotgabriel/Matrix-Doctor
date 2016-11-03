package entity;

import exception.InvalidMatrixAdditionException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by paracelsus on 27/10/2016.
 */
public class Matrix {
    private int[][] _data;
    private int _r; // no. of _r
    private int _c; // no. of _c
    private Position start;
    private Position end;

    /**
     * Creates a new matrix of determined size.
     * @param r number of _r
     * @param c number of _c
     */
    public Matrix(int r, int c) {
        _data = new int[r][c];
        this._r = r;
        this._c = c;
    }

    public Matrix(int[][] _data) {
        this._data = _data;
        this._r = _data.length;
        this._c = _data[0].length;
        this.start = new Position(0, 0);
        this.end = new Position(_r, _c);
    }

    public int getRows() {
        return _r;
    }

    public int getColumns() {
        return _c;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
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
        Matrix m3 = new Matrix(_r, _c);
        for (int i = 0; i < _r; i++) {
            for (int j = 0; j < _c; j++) {
                m3.set(i, j, this.get(i, j) + other.get(i, j));
            }
        }
        return m3;
    }

    public Matrix parallelPlus(Matrix other, int threads) {
        verifyPreconditions(this, other);
        throw new NotImplementedException();
    }

    public void show() {
        for (int i = 0; i < _r; i++) {
            for (int j = 0; j < _c; j++) {
                System.out.print(_data[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private void verifyPreconditions(Matrix m1, Matrix m2) {
        if (m2.getRows() != m1.getRows() || m2.getColumns() != m1.getColumns())
            throw new InvalidMatrixAdditionException(m2, m1);
    }

    public Matrix cut(Position p1, Position p2) {
        final int rows = p2.getRow() - p1.getRow() + 1;
        final int cols = p2.getColumn() - p1.getColumn() + 1;
        final Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.format("(%d)(%d) \n", i, j);
                matrix.set(i, j, this.get(i + p1.getRow(),j));
            }
        }
        return matrix;
    }

    public int getFirst() {
        return get(0, 0);
    }

    public int getLast() {
        return get(_r - 1, _c - 1);
    }
}
