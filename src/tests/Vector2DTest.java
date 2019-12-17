import main.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    Vector2D vec1_1=new Vector2D(1,1);
    Vector2D vec1_1t=new Vector2D(1,1);
    Vector2D vec2_1=new Vector2D(2,1);
    Vector2D vec0_1=new Vector2D(0,1);
    Vector2D vec2_3=new Vector2D(2,3);
    Vector2D vec1_2=new Vector2D(1,2);
    Vector2D vec2_2=new Vector2D(2,2);
    Vector2D vec_null=null;

    @Test
    void testEquals(){
        assertTrue(vec1_1.equals(vec1_1));
        assertTrue(vec1_1.equals(vec1_1t));
        assertFalse(vec2_1.equals(vec2_3));
        assertFalse(vec1_2.equals(vec_null));
    }

    @Test
    void testToString() {
        assertEquals("(1,1)", vec1_1.toString());
        assertEquals("(2,1)", vec2_1.toString());
        assertEquals("(0,1)", vec0_1.toString());
        assertEquals("(2,3)",vec2_3.toString());
    }

    @Test
    void precedes() {
        assertTrue(vec1_1.precedes(vec2_3));
        assertFalse(vec2_3.precedes(vec0_1));
        assertFalse(vec2_1.precedes(vec0_1));
        assertFalse(vec1_1.precedes(vec_null));
        assertTrue(vec1_1.precedes(vec1_1));
    }

    @Test
    void follows() {
        assertTrue(vec2_3.follows(vec0_1));
        assertTrue(vec2_3.follows(vec2_1));
        assertFalse(vec0_1.follows(vec1_1));
        assertFalse(vec1_1.follows(vec_null));
        assertTrue(vec2_1.follows(vec2_1));
    }

    @Test
    void upperRight() {
        vec2_3.equals(vec1_1.upperRight(vec2_3));
        vec2_2.equals(vec2_1.upperRight(vec1_2));
        vec1_1.equals(vec1_1.upperRight(vec0_1));
        vec2_2.equals(vec2_2.upperRight(vec2_2));
    }

    @Test
    void lowerLeft() {
        vec1_1.equals(vec1_2.lowerLeft(vec2_1));
        vec2_1.equals(vec2_2.lowerLeft(vec2_1));
        vec0_1.equals(vec2_3.lowerLeft(vec0_1));
        vec1_2.equals(vec2_2.lowerLeft(vec1_2));
    }

    @Test
    void add() {
        vec1_2.equals(vec0_1.add(vec1_1));
        vec2_2.equals(vec2_1.add(vec0_1));
        vec2_3.equals(vec1_1.add(vec1_2));
        vec2_2.equals(vec1_1.add(vec1_1));
    }

    @Test
    void substract() {
        vec0_1.equals(vec1_2.subtract(vec1_1));
        vec0_1.equals(vec2_3.subtract(vec2_2));
        vec1_1.equals(vec2_2.subtract(vec1_1));
        vec1_1.equals(vec2_3.subtract(vec1_2));
    }


    @Test
    void opposite() {
        new Vector2D(-1,-2).equals(vec1_2.opposite());
        new Vector2D(-2,-3).equals(vec2_3.opposite());
        new Vector2D(0,1).equals(vec0_1.opposite());
    }

}