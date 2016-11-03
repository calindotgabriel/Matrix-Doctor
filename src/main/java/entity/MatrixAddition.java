package entity;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class MatrixAddition {
    public static Matrix add(Matrix m1, Matrix m2) {
        final int rows = m1.getRows();
        final int cols = m1.getColumns();
        Matrix m3 = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m3.set(i, j, m1.get(i, j) + m2.get(i, j));
            }
        }
        return m3;
    }
}
