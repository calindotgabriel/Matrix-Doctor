package entity;

import exception.InvalidMatrixAdditionException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paracelsus on 31/10/2016.
 */
public class MatrixTest {
    @Test
    public void emptyConstruction() {
        final Matrix m45 = MockFactory.getEmptyMatrix(4, 5);
        final Matrix m26 = MockFactory.getEmptyMatrix(2, 6);
        assertEquals(0, m45.get(0, 0));
        assertEquals(0, m26.get(1, 1));
    }

    @Test
    public void dataConstruction() {
        final Matrix m = MockFactory.getDataMatrix();
        assertTrue(m.get(0, 0) == 1);
        assertTrue(m.get(1, 1) == 4);
    }

    @Test(expected = InvalidMatrixAdditionException.class)
    public void plusFailsIfDifferentDimens() {
        final Matrix m1 = MockFactory.getDataMatrix();
        final Matrix m2 = MockFactory.getEmptyMatrix(1, 1);
        m1.plus(m2);
    }

    @Test
    public void plusIsCorrect() {
        final Matrix m1 = MockFactory.getDataMatrix();
        final Matrix m2 = MockFactory.getDataMatrix();
        final Matrix m3 = m1.plus(m2);
        assertTrue(m3.get(0, 0) == 2);
        assertEquals(12, m3.get(2, 1));
    }

    @Test
    public void parallelPlusIsCorrect() {
        final Matrix m1 = MockFactory.getDataMatrix();
        final Matrix m2 = MockFactory.getDataMatrix();
        final Matrix m3 = m1.parallelPlus(m2, 1);
        assertTrue(m3.get(0, 0) == 2);
        assertEquals(12, m3.get(2, 1));
    }




}