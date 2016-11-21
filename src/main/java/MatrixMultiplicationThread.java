/**
 * Created by calin on 21.11.2016.
 */
class MatrixMultiplicationThread extends Thread{
    private final Matrix mFirstMatrix;
    private final Matrix mSecMatrix;
    private final int i;
    private final int j;
    private final int k;
    private final Matrix mResultMatrix;

    MatrixMultiplicationThread(Matrix m1, Matrix m2, int i, int j, int k, Matrix result) {
        this.mFirstMatrix = m1;
        this.mSecMatrix = m2;
        this.i = i;
        this.j = j;
        this.k = k;
        this.mResultMatrix = result;
    }

    @Override
    public void run() {
        final int value = mFirstMatrix.get(i, k) * mSecMatrix.get(k, j);
        mResultMatrix.set(i, j, mResultMatrix.get(i, j) + value);
    }
}
