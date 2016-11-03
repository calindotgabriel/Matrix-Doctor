package entity;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class SectorAssigner {
    private final Matrix _m;
    private final HashMap<Integer, Sector> _map;

    public SectorAssigner(Matrix m) {
        this._m = m;
        this._map = new HashMap<Integer, Sector>();
        _map.put(1, new Sector(new Position(0, 0),
                               new Position(_m.getRows(), m.getColumns())));
        _map.put(2, new Sector(new Position(0, 0),
                               new Position(_m.getRows(), m.getColumns())));

    }

    public Sector assign(int noThreads) {
        return _map.get(noThreads);
    }
}
