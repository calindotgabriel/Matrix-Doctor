import java.util.ArrayList;
import java.util.Collection;

/**
 * Matrix
 */
class Matrix {

    public static final String SPACE = " ";
    public static final String NEWLINE = "\n";
    private final int[][] mData;

    Matrix(int[][] data) {
        this.mData = data;
    }

    /**
     * Creates a empty matrix with given rows and cols.
     * @param rows number of rows
     * @param cols number of columns
     */
    private Matrix(int rows, int cols) {
        this.mData = new int[rows][cols];
    }

    private int getRows() {
        return mData.length;
    }

    private int getCols() {
        return mData[0].length;
    }

    public void set(int x, int y, int value) {
        mData[x][y] = value;
    }

    /**
     * Returns the eleme
     * @param x row position
     * @param y column position
     */
    int get(int x, int y) {
        return mData[x][y];
    }

    /**
     * Adds this matrix and another in parallel.
     * A new thread is created for every addition operation.
     * @param other the other matrix
     * @return obtained {@link Matrix}
     */
    Matrix plus(Matrix other) {
        final Collection<Thread> threads = new ArrayList<Thread>();
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                final MatrixAdditionThread thread = new MatrixAdditionThread(this, other, i, j, result);
                threads.add(thread);
            }
        }
        for (Thread t : threads) {
            t.run();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    Matrix multiply(Matrix other) {
        final Collection<Thread> threads = new ArrayList<Thread>();
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                for (int k = 0; k < this.getCols(); k++) {
                    final MatrixMultiplicationThread thread = new MatrixMultiplicationThread(this, other, i, j, k, result);
                    threads.add(thread);
                }
            }
        }
        for (Thread t : threads) {
            t.run();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                builder.append(get(i, j)).append(SPACE);
            }
            builder.append(NEWLINE);
        }
        return builder.toString();
    }
}
