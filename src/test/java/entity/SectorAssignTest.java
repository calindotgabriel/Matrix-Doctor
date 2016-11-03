package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by paracelsus on 03/11/2016.
 */
public class SectorAssignTest {
    @Test
    public void oneThreadedSectorAssigning() {
        int noThreads = 1;
        Matrix m = MockFactory.getDataMatrix();
        SectorAssigner sectorAssigner = new SectorAssigner(m);
        Sector sector = sectorAssigner.assign(noThreads);
        Position start = sector.getStart();
        Position end = sector.getEnd();

        assertEquals(start.getRow(), 0);
        assertEquals(start.getColumn(), 0);
        assertEquals(end.getRow(), m.getRows());
        assertEquals(end.getColumn(), m.getColumns());
    }

    @Test
    public void doubleThreadedSectorAssigning() {
        int noThreads = 2;
        Matrix m = MockFactory.getDataMatrix();
        SectorAssigner sectorAssigner = new SectorAssigner(m);
        Sector sector = sectorAssigner.assign(noThreads);
        Position start = sector.getStart();
        Position end = sector.getEnd();

        assertEquals(start.getRow(), 0);
        assertEquals(start.getColumn(), 0);
        assertEquals(end.getRow(), m.getRows());
        assertEquals(end.getColumn(), m.getColumns());
    }
}
