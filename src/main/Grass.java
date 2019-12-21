package main;

public class Grass implements IMapElement {
    private Vector2D position;
    private double plantEnergy;

    public Grass(Vector2D position, double plantEnergy){
        this.position=position;
        this.plantEnergy=plantEnergy;
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public double getPlantEnergy(){return this.plantEnergy;}

    public String toString(){
        return "* ";
    }
}
