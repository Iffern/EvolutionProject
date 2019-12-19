package main;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2D position;
    private WorldMap map;
    private Genotype genotype;
	private Energy animalEnergy;
    private List<IPositionChangeObserver> listOfObservers=new ArrayList<>();

    public Animal(WorldMap map, Integer maxEnergy, Integer moveEnergy){
        this.orientation=MapDirection.randomOrientation();
        this.position= map.randomPosition();
        this.map=map;
        this.genotype=new Genotype();
        this.animalEnergy=new Energy(maxEnergy,moveEnergy);
    }

    public Animal(WorldMap map,Vector2D initialPosition,Energy startEnergy, Animal mother, Animal father){
        this.orientation=MapDirection.randomOrientation();
        this.map=map;
        this.position=initialPosition;
        this.genotype=new Genotype(mother,father);
        this.animalEnergy=startEnergy;
    }

    public String toString(){
        return this.orientation.toString();
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public Genotype getGenotype() {return this.genotype;}

    public IWorldMap getMap(){return this.map;}

    public Energy getAnimalEnergy() {
        return animalEnergy;
    }

    public void move(){
        this.randomTurn();
        this.animalEnergy.substractEnergy(this.animalEnergy.getMoveEnergy());
        Vector2D oldPosition=this.position;
        Vector2D newPosition=this.position.add(this.orientation.toUnitVector());
        if(newPosition.x>this.map.upperRight.x) newPosition=newPosition.subtract(new Vector2D(this.map.upperRight.x+1,0));
        if(newPosition.x<0) newPosition=newPosition.add(new Vector2D(this.map.upperRight.x+1,0));
        if(newPosition.y>this.map.upperRight.y) newPosition=newPosition.subtract(new Vector2D(0,this.map.upperRight.y+1));
        if(newPosition.y<0) newPosition=newPosition.add(new Vector2D(0,this.map.upperRight.y+1));
        this.position=newPosition;
        if(!oldPosition.equals(newPosition)) this.positionChanged(oldPosition);
    }

    public void eat(Grass food,int parts){
        this.animalEnergy.addEnergy(food.plantEnergy/parts);
    }

    public Animal breed(Animal partner){
        Vector2D babyPosition=this.getMap().randomAdjacentPosition(this.getPosition());
        Integer startEnergy=this.animalEnergy.getCurrentEnergy()/4+partner.animalEnergy.getCurrentEnergy()/4;
        Energy babyEnergy=new Energy(partner.getAnimalEnergy().getStartEnergy(),startEnergy,this.animalEnergy.getMoveEnergy());
        this.animalEnergy.substractEnergy(this.animalEnergy.getCurrentEnergy()/4);
        partner.animalEnergy.substractEnergy(partner.animalEnergy.getCurrentEnergy()/4);
        return new Animal(this.map,babyPosition,babyEnergy,this,partner);
    }

    public boolean canBreed(){
        return animalEnergy.getCurrentEnergy()>animalEnergy.getStartEnergy()/2;
    }

    private void randomTurn(){
        int numberOfTurns=this.genotype.randomizeOrientation();
        for(int i=0;i<numberOfTurns;i++) this.orientation=this.orientation.next();
    }

    public boolean isDead(){
        if(this.animalEnergy.getCurrentEnergy()==0) return true;
        return false;
    }

    void addObserver(IPositionChangeObserver observer){
        listOfObservers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        listOfObservers.remove(observer);
    }

    private void positionChanged(Vector2D oldPosition) {
        for (IPositionChangeObserver observer : listOfObservers) observer.positionChanged(this, oldPosition);
    }
}
