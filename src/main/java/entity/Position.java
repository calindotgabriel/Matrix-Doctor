package entity;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class Position {
    private int rowNumber; // no. of rows
    private int columnNumber; // no. of columns

    public Position(int r, int c) {
        this.rowNumber = r;
        this.columnNumber = c;
    }

    public int getRow() {
        return rowNumber;
    }

    public int getColumn() {
        return columnNumber;
    }
}
