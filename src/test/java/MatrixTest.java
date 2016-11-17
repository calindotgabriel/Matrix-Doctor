import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void plus() throws Exception {
        Matrix m1 = new Matrix(new int[][] {
                {1, 2},
                {3, 4}
        });
        Matrix m2 = new Matrix(new int[][] {
                {-1, -2},
                {-3, -4}
        });
        final Matrix m3 = m1.plus(m2);

        assertEquals(0, m3.get(0, 0));
        assertEquals(0, m3.get(0, 1));
        assertEquals(0, m3.get(1, 0));
        assertEquals(0, m3.get(1, 1));
    }

}