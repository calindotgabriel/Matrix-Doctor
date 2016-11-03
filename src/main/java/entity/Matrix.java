package entity;

import exception.InvalidMatrixAdditionException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by paracelsus on 27/10/2016.
 */
public class Matrix {
    private List<MatrixItem> items;

    /**
     * Creates a new matrix of determined size.
     * @param r number of _r
     * @param c number of _c
     */
    public Matrix(int r, int c) {
        items = new ArrayList<MatrixItem>(r + c);
    }

    public Matrix(int[][] _data) {
        items = new ArrayList<MatrixItem>(_data.length * _data[0].length);
        for (int i = 0; i < _data.length; i++) {
            for (int j = 0; j < _data[i].length; j++) {
                items.add(new MatrixItem(i, j, _data[i][j]));
            }
        }
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
        return items.get(r+c).data;
    }

    private void set(int r, int c, int data) {
        items.set(r+c, new MatrixItem(r, c, data));
    }




    public void show() {
        items.forEach(i -> System.out.print(i.data + " "));
    }



}
