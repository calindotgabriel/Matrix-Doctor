package entity;

import com.sun.javaws.exceptions.InvalidArgumentException;
import exception.InvalidMatrixAdditionException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        this.c = _data.length;
        this.r = _data[0].length;
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
    public int elemAt(int r, int c) {
        return _data[r][c];
    }

    public Matrix plus(Matrix other) throws InvalidMatrixAdditionException {
        if (this.getRows() != other.getRows() ||
            this.getColumns() != other.getColumns())
            throw new InvalidMatrixAdditionException(this, other);
        throw new NotImplementedException();
    }

    public void show() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c ; j++) {
                System.out.print(_data[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
