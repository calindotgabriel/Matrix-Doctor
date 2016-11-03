package entity;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class Position {
    private int _r; // no. of rows
    private int _c; // no. of columns

    public Position(int r, int c) {
        this._r = r;
        this._c = c;
    }

    public int getRow() {
        return _r;
    }

    public int getColumn() {
        return _c;
    }

    public void show() {
        System.out.format("P {%d} {%d} \n", getRow(), getColumn());
    }
}
