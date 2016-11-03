package entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class MatrixCuttingTest {

    @Test
    public void first() {
        final Matrix m = new Matrix(new int[][]{
                {1, 2},
                {3, 4},
                {5, 6}});
        final Position p1 = new Position(1, 0);
        final Position p2 = new Position(2, 1);

        testCutting(m, p1, p2);
    }

    @Test
    public void sec() {
        final Matrix m = new Matrix(new int[][]{
                {1, 2, 6},
                {3, 4, 5},
                {5, 6, 9},
                {5, 2, 4}
        });
        final Position p1 = new Position(1, 1);
        final Position p2 = new Position(2, 2);

        testCutting(m, p1, p2);
    }

    private void testCutting(Matrix m, Position p1, Position p2) {
        final Matrix m2 = m.cut(p1, p2);
        assertEquals("Wrong no. of rows", 2, m2.getRows());
        assertEquals("Wrong no. of columns", 2, m2.getColumns());

        m2.show();

        assertEquals("The first elem does not match", 3, m2.getFirst());
        assertEquals("The last elem does not match", 6, m2.getLast());
    }

}
