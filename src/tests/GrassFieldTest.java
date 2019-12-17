import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    WorldMap testMap=new WorldMap(10,10,0.25,2);
    Animal dog= new Animal(testMap,50,1);
    Animal cat=new Animal(testMap,50,1);
    Animal rat=new Animal(testMap, 50,1);
    Animal cow= new Animal(testMap, 50,1);
    String [] firstPath= new String[]{"f","f","r","l","f","f","r","l"};
    String [] secondPath= new String[]{"r","l","f","f","f","f","l","b"};
    @BeforeEach
    void init(){
        testMap= new WorldMap(10,10,0.25,2);
    }

    @Test
    void run() {
        testMap.place(dog);
        testMap.place(cat);


        assertTrue(testMap.isOccupied(new Vector2D(1,2)) && testMap.isOccupied(new Vector2D(4,4)));

        Animal firstAnimal= (Animal) testMap.objectAt(new Vector2D(1,2));
        Animal secondAnimal= (Animal) testMap.objectAt(new Vector2D(4,4));

        assertEquals(MapDirection.SOUTH,firstAnimal.getOrientation());
        assertEquals(MapDirection.SOUTH,secondAnimal.getOrientation());


        assertTrue(testMap.isOccupied(new Vector2D(2,4)) && testMap.isOccupied(new Vector2D(2,2)));

        firstAnimal= (Animal) testMap.objectAt(new Vector2D(2,4));
        secondAnimal= (Animal) testMap.objectAt(new Vector2D(2,2));

        assertEquals(MapDirection.SOUTH,firstAnimal.getOrientation());
        assertEquals(MapDirection.EAST,secondAnimal.getOrientation());
    }

    @Test
    void isOccupied() {
        testMap.place(dog);
        testMap.place(rat);
        assertTrue(testMap.isOccupied(new Vector2D(3,3)));
        assertTrue(testMap.isOccupied(new Vector2D(5,0)));
    }

    @Test
    void objectAt() {
        testMap.place(cat);
        testMap.place(cow);
        assertEquals(testMap.objectAt(new Vector2D(2,1)).getClass(),rat.getClass());
        assertEquals(testMap.objectAt(new Vector2D(3,3)).getClass(),rat.getClass());
    }
}