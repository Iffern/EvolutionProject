package main;

public class Grass implements IMapElement {
    Vector2D position;
    Integer plantEnergy;

    public Grass(Vector2D position, Integer plantEnergy){
        this.position=position;
        this.plantEnergy=plantEnergy;
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public String toString(){
        return "* ";
    }
}
