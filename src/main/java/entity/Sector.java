package entity;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class Sector {

    private Position start;
    private Position end;

    public Sector(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
