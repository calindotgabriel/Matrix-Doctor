/**
 * Created by calin on 21.11.2016.
 */
class MatrixAdditionThread extends Thread {
    private final Matrix mFirstMatrix;
    private final Matrix mSecMatrix;
    private final int mPosX;
    private final int mPosY;
    private final Matrix mResultMatrix;

    MatrixAdditionThread(Matrix m1, Matrix m2, int x, int y, Matrix result) {
        this.mFirstMatrix = m1;
        this.mSecMatrix = m2;
        this.mPosX = x;
        this.mPosY = y;
        this.mResultMatrix = result;
    }

    @Override
    public void run() {
        final int value = mFirstMatrix.get(mPosX, mPosY) + mSecMatrix.get(mPosX, mPosY);
        mResultMatrix.set(mPosX, mPosY, value);
    }
}
