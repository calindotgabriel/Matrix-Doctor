package com;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A program that can perform some matrix operations, such as matrix
 * addition, matrix subtraction, scalar multiplication, matrix product, etc.
 * This matrix program can also perform Gaussian Elimination, forward and 
 * backward substitution to solve linear system.
 * To use it:
 *      1. Create an instance of Matrix with default constructor
 *      2. Call setMatrix(String) to set the matrix. The given string must have
 *         the format like {{2,1,1},{3,2,1},{ 2,1,2}}. Notice each row is
 *         enclosed by {} and separate by comma, and the whole matrix is
 *         enclosed by {}.
 *      3. The matrix is set by now, can call any methods to perform the
 *         function you want.
 *         
 * Example of use:
 *      Matrix A = new Matrix();
 *      A.setMatrix("{{2,1,1},{3,2,1},{ 2,1,2}}");
 *      A.printMatrix();
 *      A = A.getInverse();
 *      A.printMatrix();
 *      
 */
public class Matrix {

    private BigDecimal[][] A;       // matrix A
    private Matrix A1;              // inverse of matrix A
    private Matrix E;               // upper-triangular form of matrix A
        
    private static final int PRECISION = 7;     // decimal precision
    
    /**
     * Default constructor.
     */
    public Matrix() {}
    
    /**
     * Constructor that set (this) matrix as the given matrix.
     * @param m the given matrix to be set in (this) matrix
     */
    public Matrix(Matrix m) {
        setMatrix(m.toString());
    }
    
    /**
     * Constructor that set the specified matrix into this. The given string 
     * must have the format like {{2.3, 1.6}, {5/2, 4}}.
     * @param matrix the matrix to be set
     */
    public Matrix(String matrix) {
        setMatrix(matrix);
    }
    
    /**
     * Set the size of matrix by the specified number of rows and columns.
     * @param row number of rows for the matrix
     * @param col number of columns for the matrix
     */
    public void setSize(int row, int col) {
        A = new BigDecimal[row][col];
    }
    
    /**
     * Set the matrix by the given string. The given string must have the
     * format like {{2.3, 1.6}, {5/2, 4}}. Return true if successfully set the
     * matrix with the given string, false otherwise.
     * @param matrix the matrix to be set
     * @return true if successfully set the matrix with the given string, false
     *         otherwise
     */
    public boolean setMatrix(String matrix) {
        // A list to store elements of matrix
        ArrayList<BigDecimal[]> m = new ArrayList<BigDecimal[]>();
        
        // Remove all '{' and all the leading and tailing spaces
        matrix = matrix.replaceAll("\\{", "").trim();
        matrix = " " + matrix;
        
        int colCount = 0;
        while (matrix.length() > 1) {
            int index = matrix.indexOf('}');
            String row = matrix.substring(1, index).trim();
            matrix = matrix.substring(++index).trim();  // expect leading with ,
            String[] elements = row.split("[,\\s]+");
            
            // Only set colCount once
            colCount = colCount == 0 ? elements.length : colCount;
            
            // False because found unequal number of columns in a row
            // i.e {{1,2,3},{1,2}}
            if (colCount != elements.length)
                return false;
            
            // Store the row into the matrix
            BigDecimal[] r = new BigDecimal[colCount];
            for (int j = 0; j < colCount; j++) {
                String number = elements[j];
                BigDecimal value;
                
                if (number.contains("/")) {
                    // Divide the number if it contains a divide sign
                    BigDecimal numerator = new BigDecimal
                            (number.substring(0, number.indexOf('/')));
                    
                    BigDecimal denominator = new BigDecimal
                            (number.substring(number.indexOf('/')+1));
                    
                    value = numerator.divide
                            (denominator, PRECISION, BigDecimal.ROUND_HALF_UP);
                    
                } else      // No divide sign, simply set the value
                    value = new BigDecimal(number);
                
                r[j] = value;
            }
            
            m.add(r);
        }
        
        // Transfer the values from ArrayList into the class matrix
        this.A = new BigDecimal[m.size()][colCount];
        for (int i = 0; i < A.length; i++)
            A[i] = m.get(i);
        
        return true;
    }
    
    /**
     * Set the specified value to the matrix at the entry (row, column).
     * @param r the row of the entry in the matrix
     * @param c the column of the entry in the matrix
     * @param value the value to be set to the entry (row, column)
     */
    public void setEntry(int r, int c, BigDecimal value) {
        A[r][c] = value;
    }
    
    /**
     * Add a scalar multiple of row1 of the matrix to the row2 of the matrix.
     * The specified row1 and row2 must be positive numbers and greater than 0
     * but not greater than the number of rows of the matrix. 0 represent the
     * first row or first column of the matrix. This method will return true if 
     * row1 is successfully added to row2, false otherwise.
     * @param row1 the row of the matrix to get scalar multiplied and add to 
     * 			   row2
     * @param row2 the row of the matrix to get added to by row1
     * @param scalar the scalar multiple of row1
     * @return This method will return true if row1 is successfully added to 
     * 		   row2, false otherwise.
     */
    public boolean addRowToRow(int row1, int row2, BigDecimal scalar) {
        
        if (row1 < 0 || row2 < 0 || row1 > A.length || row2 > A.length)
            return false;
        
        // Add scalar multiple times the row1 to the row2 of the matrix
        for (int c = 0; c < A[row1].length; c++) {
            BigDecimal v = A[row1][c].multiply(scalar);
            A[row2][c] = v.add(A[row2][c]);
        }
        
        return true;
    }
    
    /**
     * Subtract a scalar multiple of row1 of the matrix to the row2 of the 
     * matrix. The specified row1 and row2 must be positive numbers and greater 
     * than 0 but not greater than the number of rows of the matrix. 0 represent 
     * the first row or first column of the matrix. This method will return true 
     * if row1 is successfully subtracted row2, false otherwise.
     * @param row1 the row of the matrix to get scalar multiplied and subtract
     * 			   to row2
     * @param row2 the row of the matrix to get subtracted to by row1
     * @param scalar the scalar multiple of row1
     * @return This method will return true if row1 is successfully subtracted
     * 		   row2, false otherwise.
     */
    public boolean subRowToRow(int row1, int row2, BigDecimal scalar) {
        
        if (row1 < 0 || row2 < 0 || row1 > A.length || row2 > A.length)
            return false;

        // Subtract a scalar multiple times the row1 to the row2 of the matrix
        for (int c = 0; c < A[row1].length; c++) {
            BigDecimal v = A[row1][c].multiply(scalar);
            A[row2][c] = v.subtract(A[row2][c]);
        }

        return true;
    }
    
    /**
	 * Add a scalar multiple of col1 of the matrix to the col2 of the matrix.
     * The specified col1 and col2 must be positive numbers and greater than 0
     * but not greater than the number of columns of the matrix. 0 represent the
     * first row or first column of the matrix. This method will return true if 
     * col1 is successfully added to col2, false otherwise.
     * @param col1 the column of the matrix to get scalar multiplied and
     * 	           add to col2
     * @param col2 the column of the matrix to get added by col1
     * @param scalar the scalar multiple of col1
     * @return true if col1 is successfully added to col2, false otherwise
     */
    public boolean addColToCol(int col1, int col2, BigDecimal scalar) {
        
        if (col1 < 0 || col2 < 0 || col1 > A[0].length || col2 > A[0].length)
            return false;

        // Add a scalar multiple times the col1 to the col2 of the matrix
        for (int r = 0; r < A.length; r++) {
            BigDecimal v = A[r][col1].multiply(scalar);
            A[r][col2] = v.add(A[r][col2]);
        }

        return true;
    }
    
    /**
	 * Subtract a scalar multiple of col1 of the matrix to the col2 of the 
	 * matrix. The specified col1 and col2 must be positive numbers and greater 
	 * than 0 but not greater than the number of columns of the matrix. 0 
	 * represent the first row or first column of the matrix. This method will 
	 * return true if col1 is successfully subtracted to col2, false otherwise.
     * @param col1 the column of the matrix to get scalar multiplied and
     *             subtract col2
     * @param col2 the column of the matrix to get subtracted by col1
     * @param scalar the scalar multiple of col1
     * @return true if col1 is successfully subtract col2, false otherwise
     */
    public boolean subColToCol(int col1, int col2, BigDecimal scalar) {
        
        if (col1 < 0 || col2 < 0 || col1 > A.length || col2 > A.length)
            return false;

        // Subtract a scalar multiple times the col1 to the col2 of the matrix
        for (int r = 0; r < A.length; r++) {
            BigDecimal v = A[r][col1].multiply(scalar);
            A[r][col2] = v.subtract(A[r][col2]);
        }

        return true;
    }
    
    /**
     * Divide the specified row by the given value. This mean each column in 
     * the specified row will be divided by the given value. The given row
     * must be greater than 0 but less than the number of row of the matrix.
     * 0 represent the first row or first column of the matrix. 
     * @param row the row to get divided
     * @param value the value that divide each entry in the specified row
     * @return true if all the values in the entries of the specified row get
     *         divided by the given value, false otherwise.
     */
    public boolean divideRow(int row, BigDecimal value) {
        
        if (row < 0 || row > A.length)
            return false;

        // divide each column on the specified row with the given value
        for (int i = 0; i < A[row].length; i++)
            A[row][i] =
            A[row][i].divide(value, PRECISION, BigDecimal.ROUND_HALF_UP);

        return true;
    }
    
    /**
     * Divide the specified column by the given value. This mean each row in 
     * the specified column will be divided by the given value. The given column
     * must be greater than 0 but less than the number of column of the matrix.
     * 0 represent the first row or first column of the matrix. 
     * @param col the column to get divided
     * @param value the value that divide each entry in the specified column
     * @return true if all the values in the entries of the specified column get
     *         divided by the given value, false otherwise.
     */
    public boolean divideColumn(int col, BigDecimal value) {
        
        if (col < 0 || col > A[0].length)
            return false;
        
        // divide each row on the specified column with the given value
        for (int i = 0; i < A.length; i++)
            A[i][col] = 
            A[i][col].divide(value, PRECISION, BigDecimal.ROUND_HALF_UP);
        
        return true;
    }
    
    /**
     * Multiply the matrix by the specified scalar.
     * @param scalar the number to multiply the matrix
     */
    public void multiplyByScalar(BigDecimal scalar) {
        // Multiply each entries by the scalar
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[i].length; j++)
                A[i][j] = A[i][j].multiply(scalar);
    }

    /**
     * Add this matrix to the specified matrix and return the resulted matrix.
     * This matrix must have the same size with the given matrix. The sum of
     * the new matrix, C, will have the same size as the given matrix with 
     * entries C(i,j) = this(i, j) + mB(i, j).
     * @param mB the other matrix to get added to by this matrix
     * @return the resulting matrix with the size of mB and entries (i, j) = 
     *         this(i, j) + mB(i, j). Return null if this matrix has a different
     *         size with the specified matrix.
     */
    public Matrix addMatrix(Matrix mB) {

        // can't be added because of different size
        if (this.getRowCount() != mB.getRowCount() ||
            this.getColCount() != mB.getColCount())
            return null;
        
        // mC = mA + mB
        Matrix mC = new Matrix();
        mC.setSize(this.getRowCount(), this.getColCount());
        
        // mC(i, j) = mA(i, j) + mB(i, j)
        for (int i = 0; i < mC.getRowCount(); i++) {
            for (int j = 0; j < mC.getColCount(); j++) {
                BigDecimal v = this.getEntry(i, j).add(mB.getEntry(i, j));
                mC.setEntry(i, j, v);
            }
        }
        
        return mC;
    }
    
    /**
     * Subtract this matrix to the specified matrix and return the resulted 
     * matrix. This matrix must have the same size with the given matrix. 
     * The difference of the new matrix, C, will have the same size as the 
     * given matrix with entries C(i,j) = this(i, j) - mB(i, j).
     * @param mB the other matrix to get subtracted by this matrix
     * @return the resulting matrix with the size of mB and entries (i, j) = 
     *         this(i, j) - mB(i, j). Return null if this matrix has a different
     *         size with the specified matrix.
     */
    public Matrix subtractMatrix(Matrix mB) {
            
        // can't be subtracted because of different size
        if (this.getRowCount() != mB.getRowCount() ||
            this.getColCount() != mB.getColCount())
            return null;
        
        // mC = mA - mB
        Matrix mC = new Matrix();
        mC.setSize(this.getRowCount(), this.getColCount());
        
        // mC(i, j) = mA(i, j) - mB(i, j)
        for (int i = 0; i < mC.getRowCount(); i++) {
            for (int j = 0; j < mC.getColCount(); j++) {
                BigDecimal v = this.getEntry(i, j).subtract(mB.getEntry(i, j));
                mC.setEntry(i, j, v);
            }
        }
        
        return mC;
    }
    
    /**
     * Multiply this matrix to the specified matrix, mB. The columns of this
     * matrix must be equal to the number of rows of the specified matrix. This
     * method will return a new matrix, C, where C = AB.
     * The entry C(i, j) is obtained by the product of ith row of this matrix
     * and jth column of mB.
     * @param mB the other matrix to get multiplied by this matrix
     * @return the resulting matrix. Return null if the number of columns of 
     *         this matrix is not equal to the number of rows of mB
     */
    public Matrix multiplyMatrix(Matrix mB) {
        
        // can't be multiplied because number of columns of this matrix is not 
        // equal to the number of rows of mB
        if (this.getColCount() != mB.getRowCount())
            return null;
        
        // mC = A * mB
        Matrix mC = new Matrix();
        mC.setSize(this.getRowCount(), mB.getColCount());
        
        for (int i = 0; i < mC.getRowCount(); i++) {
            for (int j = 0; j < mC.getColCount(); j++) {
                
                BigDecimal sum = BigDecimal.ZERO;
                
                // mC[i][j] = sum of (mA[i][k] * mB[k][j])
                for (int k = 0; k < mB.getRowCount(); k++)
                    sum = sum.add(this.getEntry(i, k).multiply(mB.getEntry(k, j)));
                
                mC.setEntry(i, j, sum);
            }
        }
        
        return mC;
    }
    
    /**
     * Get a sub-matrix from the original matrix with the specified size m x n. 
     * The specified size m x n should not be less than 1 or greater than the 
     * size of the original matrix. The sub-matrix entry (1, 1) is equal to
     * the original entry (1, 1).
     * @param m the number of rows of the sub-matrix
     * @param n the number of columns of the sub-matrix
     * @return a sub-matrix with the specified size m x n, return null if
     *         the m and n is less than 1 or greater than the size of the
     *         original matrix
     */
    public Matrix getSubmatrix(int m, int n) {
        
        if (m < 1 || n < 1 || m > A.length || n > A[0].length)
            return null;
        
        Matrix sub = new Matrix();
        sub.setSize(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                sub.setEntry(i, j, A[i][j]);
        
        return sub;
    }
    
    /**
     * Get the transpose of the matrix. If the size of the matrix is m x n,
     * then the transpose of the matrix will have the size of n x m, and the
     * entry of the matrix (i, j) is equals to the entry of the transpose 
     * matrix (j, i).
     * @return the transpose of the matrix
     */
    public Matrix getTranspose() {
        
        Matrix t = new Matrix();
        t.setSize(A[0].length, A.length);
        
        for (int i = 0; i < t.getRowCount(); i++) 
            for (int j = 0; j < t.getColCount(); j++) 
                t.setEntry(i, j, A[j][i]);
        
        return t;
    }
    
    /**
     * Get the trace of the matrix. The matrix must be square, where its size
     * is n x n. The trace is the sum of all diagonal elements of the matrix. 
     * For example, Trace(A) = a(1,1) + a(2,2) + ... + a(n,n). Return the trace
     * of the matrix if it is a square matrix, otherwise return null.
     * @return the trace (sum of all diagonal elements of the matrix) if the
     *         matrix is square, otherwise return null
     */
    public BigDecimal getTrace() {
        
        if (!isSquareMatrix())
            return null;
        
        BigDecimal sum = BigDecimal.ZERO;

        for (int d = 0; d < A.length; d++)
            sum = sum.add(A[d][d]);
        
        return sum;
    }
    
    /**
     * Get the inverse of the matrix. Return null if the matrix is a singular
     * matrix, which means the matrix does not have an inverse.
     * @return the inverse of the matrix. Return null if the matrix is a
     *         singular matrix, which means the matrix does not have an inverse
     */
    public Matrix getInverse() {
        
        if (!isSquareMatrix())
            return null;
        
        // start the A1 as the identity matrix
        A1 = new Matrix();
        A1.setSize(getRowCount(), getColCount());
        for (int r = 0; r < A1.getRowCount(); r++)
            for (int c = 0; c < A1.getColCount(); c++)
                A1.setEntry(r, c, r == c ? BigDecimal.ONE : BigDecimal.ZERO);
        
        // Get the matrix to an upper-triangular form
        Matrix upper = getUpperTriangular();
        
        // convert upper-triangle form to Identity matrix
        // starts from bottom of the matrix to the top, and only scan the
        // diagonal entries
        if (A1 != null) {
            for (int d = A.length-1; d >= 0; d--) {
                BigDecimal currentValue = upper.getEntry(d, d);
                
                // divide itself to make diagonal 1
                upper.divideRow(d, currentValue);  
                
                // do the same to the inverse matrix
                A1.divideRow(d, currentValue);     

                // convert everything above the current diagonal point to 0
                for (int r = d - 1; r >= 0; r--) {
                    BigDecimal numerator = 
                            upper.getEntry(r, d).multiply(new BigDecimal(-1));
                    BigDecimal denominator = upper.getEntry(d, d);

                    BigDecimal value = numerator.divide
                            (denominator, PRECISION, BigDecimal.ROUND_HALF_UP);

                    upper.addRowToRow(d, r, value);
                    A1.addRowToRow(d, r, value);
                }
            } // end for-loop
        }
        
        return new Matrix(A1);
    }
    
    /**
     * Get the upper-triangular form of the matrix by using Gaussian 
     * Elimination.
     * @return the upper-triangular form of the matrix
     */
    public Matrix getUpperTriangular() {
        
        if (E == null) {
            E = new Matrix();
            E.setSize(getRowCount(), getColCount());
            
            // Initialize E as an identity matrix
            for (int i = 0; i < E.getRowCount(); i++)
                for (int j = 0; j < E.getColCount(); j++)
                    E.setEntry(i, j, i == j ? BigDecimal.ONE : BigDecimal.ZERO);
        }
        
        // make a copy of A and store in temp
        Matrix upper = copy(this);
        
        // convert matrix to an upper-triangle form
        for (int d = 0; d < upper.getRowCount(); d++) {
            // if diagonal is 0, swap with next other row that is not 0
            int nextRow = d;
            while (upper.getEntry(d, d).
                        compareTo(BigDecimal.ZERO) == 0 ||
                   upper.getEntry(d, d).
                       compareTo(new BigDecimal("0E-7")) == 0) {  
                // end of matrix row, and still can't find an available row
                // that is not 0, so the matrix is singular, no inverse
                if (++nextRow >= upper.getRowCount()) {
                    A1 = null;
                    break;
                }
                
                // skip if the next pivot point is not 0
                if (upper.getEntry(nextRow, d).
                        compareTo(BigDecimal.ZERO) != 0 ||
                    upper.getEntry(nextRow, d).
                        compareTo(new BigDecimal("0E-7")) != 0) {

                    upper.swapRows(d, nextRow);
                    // If there is inverse, then swap the matrix inverse as well
                    if (A1 != null) A1.swapRows(d, nextRow);
                }
            }
            
            // finished adjusting and swapping rows, so start to compute the
            // matrix A to an upper triangular form
            if (d == upper.getRowCount()-1)
                // reached last diagonal entry, we are done, quit for-loop
                break;
            
            else {
                // convert everything below the current diagonal point to 0
                for (int r = d + 1; r < upper.getRowCount(); r++) {
                    
                    BigDecimal numerator = 
                            upper.getEntry(r, d).multiply(new BigDecimal(-1));
                    
                    BigDecimal denominator = upper.getEntry(d, d);
                                        
                    BigDecimal value = numerator.divide
                            (denominator, PRECISION, BigDecimal.ROUND_HALF_UP);
                                                     
                    E.setEntry(r, d, value);           // store to E
                    
                    upper.addRowToRow(d, r, value);
                    // If there is inverse, then swap the matrix inverse as well
                    if (A1 != null) A1.addRowToRow(d, r, value);
                }
            }   // end if-statement
        } // end outer for-loop
        return upper;
    }
    
    /**
     * Get the lower triangular matrix of the matrix. Each element (i, j) in 
     * the lower triangular matrix is equal to (-1)*(i, j) of element in the
     * elementary row operations matrix.
     * @return the lower triangular matrix
     */
    public Matrix getLowerTriangular() {
        
        Matrix E = getERO();
       
        // Just check the nonzero elements below the diagonal line of matrix E 
        for (int i = 1; i < E.getRowCount(); i++)
            for (int j = 0; i != j; j++) 
                E.setEntry(i, j, 
                        E.getEntry(i, j).multiply(new BigDecimal(-1)));
        
        // Now E has turned into lower triangular, so return E
        return E;
    }
    
    /**
     * Get the matrix E, where E = E(k)*E(k-1)*...*E(2)*E(1), and each E is
     * an elementary row operation of the matrix.
     * @return the elementary row operations matrix
     */
    public Matrix getERO() {
        
        if (E != null)
            return copy(E);
        
        // Get the elementary row operations matrix by performing upper
        // triangular calculation
        getUpperTriangular();
        
        return new Matrix(E);
    }
    
    /**
     * Get the rank of the matrix, where rank(A) <= min(m, n). The rank of the 
     * matrix is equal to the number of pivots of the upper triangular matrix.
     * @return the rank of the matrix, where rank(A) <= min(m, n)
     */
    public int getRank() {
        
        if (A.length == 0 || A[0].length == 0)
            return 0;
        
        int rank = 1;
        
        Matrix U = getUpperTriangular();
        for (int i = 1; i < U.getRowCount(); i++) {
            for (int j = i-1; j < U.getColCount(); j++) {
                // If reached the end of the column of the row but the entry
                // is 0, then we are done checking
                if (j == U.getColCount()-1 &&
                    (U.getEntry(i, j).compareTo(BigDecimal.ZERO) == 0 ||
                     U.getEntry(i, j).compareTo(new BigDecimal("0E-7")) == 0))
                    break;
                
                // If the entry is not equal to 0
                else if (U.getEntry(i, j).compareTo(BigDecimal.ZERO) != 0 ||
                         U.getEntry(i, j).compareTo(new BigDecimal("0E-7"))==0){
                    rank++;
                    j = U.getColCount();    // go to next row
                }
            }
        }
        return rank;
    }
    
    /**
     * Get the determinant of the matrix. The determinant only defined if the
     * matrix has a square size. The determinant is calculated by summing all
     * the elements of the upper triangular of the matrix.
     * @return the determinant of the matrix. Null if the matrix is not square.
     */
    public BigDecimal getDeterminant() {
        
        if (!isSquareMatrix())
            return null;
        
        // det = sum of diagonal elements in upper triangular matrix
        BigDecimal det = BigDecimal.ZERO;
        Matrix U = getUpperTriangular();
        for (int d = 0; d < U.getRowCount(); d++)
            det = det.add(U.getEntry(d, d));
        
        return det.stripTrailingZeros(); 
    }
    
    /**
     * Get the number of rows of the matrix.
     * @return the number of rows of the matrix
     */
    public int getRowCount() { 
        return A.length; 
    }
    
    /**
     * Get the number of columns of the matrix.
     * @return the number of columns of the matrix
     */
    public int getColCount() { 
        return A[0].length; 
    }
    
    /**
     * Get the entry at the specified row and column of the matrix.
     * @param r the row of the entry in the matrix
     * @param c the column of the entry in the matrix
     * @return the entry at the specified row and column of the matrix
     */
    public BigDecimal getEntry(int r, int c) { 
        return A[r][c]; 
    }
    
    /**
     * Flip the matrix horizontally and return the horizontal flip matrix.
     * @return the resulting matrix
     */
    public Matrix horizontalFlip() {
        
        Matrix hf = copy(this);
        
        // Swap columns
        for (int r = 0; r < hf.getRowCount(); r++) {
            for (int c = 0; c <= hf.getColCount()/2; c++) {
                int index = hf.getColCount() - c;
                swapColumns(c, index);
            }
        }
        return hf;
    }
    
    /**
     * Flip the matrix vertically and return the vertical flip matrix.
     * @return the resulting matrix
     */
    public Matrix verticalFlip() {
        
        Matrix vf = copy(this);
        
        // Swap rows
        for (int r = 0; r <= vf.getRowCount(); r++) {
            for (int c = 0; c < vf.getColCount(); c++) {
                int index = vf.getRowCount() - r;
                swapRows(c, index);
            }
        }
        return vf;
    }
    
    /**
     * Override the equals method. The matrices are considered equal if and
     * only if they have the same size, and each entry in matrix A (i, j) is
     * equals to the entry in matrix B (i, j).
     * @return true if both matrices are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        
        if (obj == null) return false;
        
        Matrix mB = (Matrix) obj;
        
        // if they are different in size, then they are not equal
        if ((this.getRowCount() != mB.getRowCount()) ||
            (this.getColCount() != mB.getColCount()))
            return false;
        
        // if found unmatched entry at row i and column j of both matrices A
        // and B, return false, because they are unequal
        for (int i = 0; i < this.getRowCount(); i++)
            for (int j = 0; j < this.getColCount(); j++) 
                if (!this.getEntry(i, j).equals(mB.getEntry(i, j)))
                    return false;
        
        // no different size or unmatched entry is found, so they are equal
        return true;
    }
    
    /**
     * Make a copy of the given matrix.
     * @param m the matrix to get copied
     * @return a copy of the given matrix
     */
    public Matrix copy(Matrix m) {
        
        Matrix newM = new Matrix();
        newM.setSize(m.getRowCount(), m.getColCount());
        
        // Copy each entry in the given matrix to the new matrix
        for (int i = 0; i < newM.getRowCount(); i++)
            for (int j = 0; j < newM.getColCount(); j++)
                newM.setEntry(i, j, m.getEntry(i, j));
        
        return newM;
    }
    
    /**
     * Swap the given row1 to row2 in the matrix. The row1 and row2 must be
     * greater than 0 but less than the number of rows of the matrix. 0 
     * represent the first row or first column of the matrix.
     * @param row1 one of the row in the matrix to get swapped
     * @param row2 another row to get swapped from row1
     * @return true if both given rows are successfully swapped, false
     *         otherwise
     */
    public boolean swapRows(int row1, int row2) {
        if (row1 < 0 || row2 < 0 || row1 > A.length || row2 > A.length)
            return false;

        BigDecimal[] tempRow = A[row1];
        A[row1] = A[row2];
        A[row2] = tempRow;
        return true;
    }
    
    /**
     * Swap the given col1 to col2 in the matrix. The col1 and col2 must be
     * greater than 0 but less than the number of columns of the matrix.
     * 0 represent the first row or first column of the matrix.
     * @param col1 one of the column in the matrix to get swapped
     * @param col2 another column to get swapped from col1
     * @return true if both given columns are successfully swapped, false
     *         otherwise
     */
    public boolean swapColumns(int col1, int col2) {
        if (col1 < 0 || col2 < 0 || col1 > A[0].length || col2 > A[0].length)
            return false;
        
        for (int r = 0; r < A.length; r++) {
            BigDecimal temp = A[r][col1];
            A[r][col1] = A[r][col2];
            A[r][col2] = temp;
        }
        return true;
    }
    
    /**
     * Determine if the matrix is symmetric or not. The matrix is considered
     * symmetric if and only if the matrix is equal to its transpose.
     * @return true if the matrix is symmetric, false otherwise
     */
    public boolean isSymmetric() {
        
        if (!isSquareMatrix())
            return false;
        
        // Create a temporary matrix to store transpose of A (AT)
        Matrix AT = getTranspose();
        
        // This will compare A and AT
        // The matrix is symmetric if A = AT
        return this.equals(AT);
    }
    
    /**
     * Return true if this matrix is singular, false if it is not. This matrix
     * is considered singular if the determinant is equal to zero.
     * @return true if this matrix is singular, false if it is not
     */
    public boolean isSingular() {
        BigDecimal det = getDeterminant();
        
        return det == null || det.compareTo(BigDecimal.ZERO) == 0 || 
                              det.compareTo(new BigDecimal("0E-7")) == 0;
    }
    
    /**
     * Determine if the matrix is a square matrix or not. A square matrix will
     * have the size of n x n.
     * @return true if the matrix is a square matrix, false otherwise
     */
    public boolean isSquareMatrix() {
        return A.length == A[0].length && A.length > 1;
    }
    
    /**
     * Check if the matrix is an orthogonal matrix. An orthogonal matrix is a 
     * square matrix with real entries whose columns and rows are orthogonal 
     * unit vectors, and the inverse of matrix is equal to its transpose,
     * A1 = AT.
     * @return true if the matrix is an orthogonal matrix, false otherwise
     */
    public boolean isOrthogonal() {
        Matrix A1 = getInverse();
        
        if (A1 == null)
            return false;
        
        Matrix AT = getTranspose();
        
        return A1.equals(AT);
    }
    
    /**
     * Determine if the matrix is a row vector matrix or not. A row vector
     * matrix will have the size of 1 x n
     * @return true if the matrix is a row vector matrix
     */
    public boolean isRowVector() {
        return A.length == 1 && A[0].length > 0;
    }
    
    /**
     * Determine if the matrix is a column vector matrix or not. A column
     * vector matrix will have the size of n x 1
     * @return true if the matrix is a column matrix, false otherwise
     */
    public boolean isColumnVector() {
        return A[0].length == 1 && A.length > 0;
    }
    
    /**
     * Return the matrix in the string of the format like {{2.3,1.6},5/2,4}}
     * @return the matrix in the string of the format like {{2.3,1.6},{5/2,4}}
     */
    public String toString() {
        
        StringBuilder str = new StringBuilder("{");
        
        for (int i = 0; i < A.length; i++) {
            StringBuilder row = new StringBuilder("{");
            for (int j = 0; j < A[i].length; j++) {
                row.append(A[i][j]).append(j == A[i].length-1 ? "" : ",");
            }
            row.append("}").append(i == A.length-1 ? "" : ",");
            str.append(row);
        }
        
        str.append("}");
        return str.toString();
    }
    
    /**
     * Print the matrix on the console.
     */
    public void printMatrix() {
        for (int i = 0; i < A.length; i++, System.out.println())
            for (int j = 0; j < A[0].length; j++) {
                String value = A[i][j].stripTrailingZeros().toPlainString();
                BigDecimal v = new BigDecimal(value);
                String d = v.compareTo(BigDecimal.ZERO) == 0 ? "0" : value;
                System.out.print(d+" ");
            }
    }
}
