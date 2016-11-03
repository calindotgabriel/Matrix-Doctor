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
        final Matrix m45 = MockFactory.getEmptyMatrix(4, 5);
        m45.show();
        final Matrix m26 = MockFactory.getEmptyMatrix(2, 6);
        assertTrue(m45.get(0, 0) == 0);
    }

    @Test
    public void getter() throws Exception {
        final Matrix m = MockFactory.getDataMatrix();
        m.show();
        assertEquals("First elem does not match", 1, m.get(0, 0));
        assertEquals("Last elem does not match", 6, m.get(2, 1));
    }






}