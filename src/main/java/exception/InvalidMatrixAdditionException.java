package exception;

import entity.Matrix;

/**
 * Created by paracelsus on 27/10/2016.
 */
public class InvalidMatrixAdditionException extends RuntimeException {
    public InvalidMatrixAdditionException(Matrix m1, Matrix m2) {
    }
}
