import entity.Matrix;
import entity.MatrixAddition;
import entity.MockFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class TestMatrixAddition {
    @Test
    public void addition() {
        final Matrix m1 = MockFactory.getDataMatrix();
        final Matrix m2 = MockFactory.getDataMatrix();
        Matrix result = MatrixAddition.add(m1, m2);
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                assertEquals(m1.get(i, j) + m2.get(i, j), result.get(i,j));
            }
        }
    }
}
