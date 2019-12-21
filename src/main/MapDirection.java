package main;

import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public String toString() {
        if(this==NORTH) return "N ";
        if(this==SOUTH) return "S ";
        if(this==WEST) return "W ";
        if(this==EAST) return "E ";
        if(this==NORTHWEST) return "NW";
        if(this==SOUTHEAST) return "SE";
        if(this==NORTHEAST) return "NE";
        if(this==SOUTHWEST) return "SW";
        else return null;
    }
    public MapDirection next(){
        if(this==NORTH) return NORTHEAST;
        if(this==SOUTH) return SOUTHWEST;
        if(this==WEST) return NORTHWEST;
        if(this==EAST) return SOUTHEAST;
		if(this==NORTHWEST) return NORTH;
        if(this==SOUTHEAST) return SOUTH;
        if(this==NORTHEAST) return EAST;
        if(this==SOUTHWEST) return WEST;
        else return null;
    }

    public Vector2D toUnitVector(){
        if(this==NORTH) return new Vector2D(0,1);
        if(this==SOUTH) return new Vector2D(0,-1);
        if(this==WEST) return new Vector2D(-1,0);
        if(this==EAST) return new Vector2D(1,0);
        if(this==NORTHWEST) return new Vector2D(-1,1);
        if(this==NORTHEAST) return new Vector2D(1,1);
        if(this==SOUTHEAST) return new Vector2D(1,-1);
        if(this== SOUTHWEST) return new Vector2D(-1,-1);
        else return null;
    }

    public static MapDirection randomOrientation(){
        Random random=new Random();
        return MapDirection.values()[random.nextInt(8)];
    }
}
