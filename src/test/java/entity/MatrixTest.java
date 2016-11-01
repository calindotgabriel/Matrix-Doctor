package entity;

import exception.InvalidMatrixAdditionException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paracelsus on 31/10/2016.
 */
public class MatrixTest {
    @Test
    public void elemZeroAt() throws Exception {
        final Matrix m45 = emptyMatrix(4, 5);
        m45.show();
        final Matrix m26 = emptyMatrix(2, 6);
        assertTrue(m45.get(0, 0) == 0);
    }

    @Test
    public void elemAt() throws Exception {
        final Matrix m = getMockMatrix();
        m.show();
        assertTrue(m.get(0, 0) == 1);
        assertTrue(m.get(1, 1) == 4);
    }

    @Test(expected = InvalidMatrixAdditionException.class)
    public void plusFailsIfDifferentDimens() {
        final Matrix m1 = getMockMatrix();
        final Matrix m2 = emptyMatrix(1, 1);
        m1.plus(m2);
    }

    @Test
    public void plusIsCorrect() {
        final Matrix m1 = getMockMatrix();
        final Matrix m2 = getMockMatrix();
        final Matrix m3 = m1.plus(m2);
        assertTrue(m3.get(0, 0) == 2);
        assertEquals(12, m3.get(2, 1));
    }


    private Matrix emptyMatrix(int r, int c) {
        return new Matrix(r, c);
    }

    private Matrix getMockMatrix() {
        return new Matrix(new int[][]{
                {1, 2},
                {3, 4},
                {5, 6}});
    }

}