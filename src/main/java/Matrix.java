import static sun.misc.PostVMInitHook.run;

/**
 * Matrix
 */
class Matrix {

    private final int[][] mData;

    Matrix(int[][] data) {
        this.mData = data;
    }

    /**
     * Creates a empty matrix with given rows and cols.
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        this.mData = new int[rows][cols];
    }

    private int getRows() {
        return mData.length;
    }

    private int getCols() {
        return mData[0].length;
    }

    private void set(int x, int y, int value) {
        mData[x][y] = value;
    }

    /**
     * Returns the eleme
     * @param x row position
     * @param y column position
     */
    public int get(int x, int y) {
        return mData[x][y];
    }

    /**
     * Adds this matrix and another in parallel.
     * A new thread is created for every addition operation.
     * @param other the other matrix
     * @return obtained {@link Matrix}
     */
    public Matrix plus(Matrix other) {
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                new MatrixAdditionThread(this, other, i, j, result).run();
            }
        }
        return result;
    }

    private class MatrixAdditionThread extends Thread {
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
}
