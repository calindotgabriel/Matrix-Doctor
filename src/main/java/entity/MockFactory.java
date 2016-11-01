package entity;

import entity.Matrix;

/**
 * Created by paracelsus on 01/11/2016.
 */
public class MockFactory {
    public static Matrix getEmptyMatrix(int r, int c) {
        return new Matrix(r, c);
    }

    static Matrix getDataMatrix() {
        return new Matrix(new int[][]{
                {1, 2},
                {3, 4},
                {5, 6}});
    }
}
