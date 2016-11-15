package entity;

import entity.Matrix;

/**
 * Created by calin on 15.11.2016.
 */
public class Preconditions {
    /**
     * Verifies if two matrices can be added.
     * @param m1
     * @param m2
     */
    public static boolean canAdd(Matrix m1, Matrix m2) {
        return (m2.getRows() == m1.getRows()) || (m2.getColumns() == m1.getColumns());
    }
}
