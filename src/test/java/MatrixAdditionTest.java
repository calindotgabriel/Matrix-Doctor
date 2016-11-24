import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixAdditionTest {

    public static final Matrix ADDITION_M1 = new Matrix(new int[][]{
            {1, 2},
            {3, 4}
    });
    public static final Matrix ADDITION_M2 = new Matrix(new int[][]{
            {-1, -2},
            {-3, -4}
    });

    @Test
    public void plus() throws Exception {
        Matrix expected = new Matrix(new int[][]{
                {0, 0},
                {0, 0}
        });
        assertEquals(expected, ADDITION_M1.plus(ADDITION_M2));
    }

}