import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalTest {
        private Animal dog;
        WorldMap testMap;
        MoveDirection[] path1 = new MoveDirection[] {MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.LEFT};
        MoveDirection[] path2 = new MoveDirection[] {MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.FORWARD};
        MoveDirection[] path3 = new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD, MoveDirection.RIGHT};
        MoveDirection[] path4 = new MoveDirection[] {MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD};
        @BeforeEach
        void init(){
            testMap=new WorldMap(10,10,0.25,2);
            dog=new Animal(testMap,50,1);
        }

    @Test
    void moveTest() {
        for(MoveDirection arg:path1) dog.move();
        assertEquals(dog.getOrientation(), MapDirection.EAST);
        assertEquals(dog.getPosition(),(new Vector2D(2,1)));
        for(MoveDirection arg:path2) dog.move();
        assertEquals(dog.getOrientation(), MapDirection.WEST);
        assertEquals(dog.getPosition(),(new Vector2D(1,0)));
        for(MoveDirection arg:path3) dog.move();
        assertEquals(dog.getOrientation(), MapDirection.WEST);
        assertEquals(dog.getPosition(),(new Vector2D(0,1)));
        for(MoveDirection arg:path2) dog.move();
        assertEquals(dog.getOrientation(), MapDirection.EAST);
        assertEquals(dog.getPosition(),(new Vector2D(1,2)));
    }

    @Test
    void moveTest2() {
        for(MoveDirection arg:path4) dog.move();
        assertEquals("Position: (2,4) Orientation: North",dog.toString());
        dog.move();
        for(MoveDirection arg:path4) dog.move();
        assertEquals("Position: (0,4) Orientation: West",dog.toString());
        dog.move();
        for(MoveDirection arg:path4) dog.move();
        assertEquals("Position: (0,0) Orientation: South",dog.toString());
        for(MoveDirection arg:path4) dog.move();
        assertEquals("Position: (0,0) Orientation: South",dog.toString());
        dog.move();
        for(MoveDirection arg:path4) dog.move();
        assertEquals("Position: (4,0) Orientation: East",dog.toString());
        for(MoveDirection arg:path4) dog.move();
        assertEquals("Position: (4,0) Orientation: East",dog.toString());
    }
}