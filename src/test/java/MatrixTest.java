import org.junit.Test;

import java.io.Console;

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

    @Test
    public void multiply() {
        Matrix m1 = new Matrix(new int[][] {
                {1, 2},
                {3, 4}
        });
        Matrix m2 = new Matrix(new int[][] {
                {4, 5},
                {6, 7}
        });
        final Matrix m3 = m1.multiply(m2);

        System.out.println(m3);

        assertEquals(16, m3.get(0, 0));
        assertEquals(19, m3.get(0, 1));
        assertEquals(36, m3.get(1, 0));
        assertEquals(43, m3.get(1, 1));
    }


}