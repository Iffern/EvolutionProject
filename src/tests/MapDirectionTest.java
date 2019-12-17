package tests;

import main.MapDirection;
import main.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void testToString() {
        assertEquals("North",MapDirection.NORTH.toString());
        assertEquals("South",MapDirection.SOUTH.toString());
        assertEquals("West",MapDirection.WEST.toString());
        assertEquals("East",MapDirection.EAST.toString());
    }

    @Test
    void next() {
        assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
        assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
    }

    @Test
    void previous() {
        assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
        assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
    }

    @Test
    void toUnitVector() {
        new Vector2D(-1,0).equals(MapDirection.WEST.toUnitVector());
        new Vector2D(0,1).equals(MapDirection.NORTH.toUnitVector());
        new Vector2D(1,0).equals(MapDirection.EAST.toUnitVector());
        new Vector2D(0,-1).equals(MapDirection.SOUTH.toUnitVector());
    }
}