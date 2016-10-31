package entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paracelsus on 31/10/2016.
 */
public class MatrixTest {
    @Test
    public void elemZeroAt() throws Exception {
        final Matrix m45 = new Matrix(4, 5);
        m45.show();
        final Matrix m26 = new Matrix(2, 6);
        assertTrue(m45.elemAt(0, 0) == 0);
    }

    @Test
    public void elemAt() throws Exception {
        final Matrix m = new Matrix(new int[][]{{1, 2}, {3, 4}, {5, 6}});
        m.show();
        assertTrue(m.elemAt(0, 0) == 1);
        assertTrue(m.elemAt(1, 2) == 6);
    }

}