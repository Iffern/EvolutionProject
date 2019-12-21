package main;

public class Grass implements IMapElement {
    Vector2D position;
    Double plantEnergy;

    public Grass(Vector2D position, Double plantEnergy){
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
