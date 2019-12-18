package main;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.lang.reflect.Array;
import java.util.*;

public class WorldMap implements IPositionChangeObserver,IWorldMap{
MultiValuedMap<Vector2D,Animal> animalsHash = new ArrayListValuedHashMap<>();
List<Animal> animalList= new ArrayList<>();
Map<Vector2D,Grass> grassesHash =new HashMap<>();
List<Grass> grassesList=new ArrayList<>();
List<Vector2D> emptyFields=new ArrayList<>();
Jungle jungleField;
Vector2D upperRight;
Vector2D lowerLeft;
MapVisualizer visualMap;

    public WorldMap(int width, int height, double jungleRatio, int plantEnergy) {
        this.upperRight = new Vector2D(width-1,height-1);
        this.lowerLeft = new Vector2D(0,0);
        this.visualMap= new MapVisualizer(this);
        this.jungleField=new Jungle(width,height,jungleRatio,plantEnergy);
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++) {
                emptyFields.add(new Vector2D(i,j));
            }}
        preFillJungle();
    }

    @Override
    public void place(Animal animal) {
            animal.addObserver(this);
            animalsHash.put(animal.getPosition(),animal);
            animalList.add(animal);
            emptyFields.remove(animal.getPosition());
            if(jungleField.isItJungle(animal.getPosition()))
                jungleField.emptyJungleFields.remove(animal.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return !(objectAt(position) == null);
    }
    @Override
    public Object objectAt(Vector2D position) {
        if(animalsHash.containsKey(position)) return animalsHash.get(position);
        if(grassesHash.containsKey(position)) return  grassesHash.get(position);
        return null;
    }

    public Vector2D randomAdjacentPosition(Vector2D position) {
        Random random=new Random();
        int randIdx=random.nextInt(8);
        MapDirection[] mapDirections=MapDirection.values();
        Vector2D randomPos;
        int i=0;
        do {
            randomPos = position.add(mapDirections[(randIdx+i)%8].toUnitVector());
            i++;
        }while(isOccupied(randomPos) && i<8);
        if(i==8) randomPos=position.add(mapDirections[random.nextInt(8)].toUnitVector());
        return randomPos;
    }

    public Vector2D randomPosition(){
        Random random=new Random();
        Vector2D position;
        do {
            position=new Vector2D(random.nextInt(upperRight.x+1),random.nextInt(upperRight.y+1));
        }while(animalsHash.containsKey(position));
        return position;
    }

    private void preFillJungle(){
        Random random=new Random();
        int size=(this.jungleField.upperRight.x-this.jungleField.lowerLeft.x+1)*(this.jungleField.upperRight.y-this.jungleField.lowerLeft.y+1);
        int grassNumber=(int) Math.sqrt(size);
        for(int i=0;i<grassNumber;i++){
           addJunglePlant();
        }
    }

    private void addJunglePlant(){
        if(!jungleField.emptyJungleFields.isEmpty() && !emptyFields.isEmpty()){
            Vector2D newGrassPosition;
            do{
        newGrassPosition=jungleField.emptyJungleFields.get(new Random().nextInt(jungleField.emptyJungleFields.size()));}
            while(!emptyFields.contains(newGrassPosition));
        Grass newGrass=new Grass(newGrassPosition,this.jungleField.plantEnergy);
        this.grassesHash.put(newGrassPosition,newGrass);
        this.grassesList.add(newGrass);
        emptyFields.remove(newGrassPosition);
        jungleField.emptyJungleFields.remove(newGrassPosition);}
    }

    public void growNewPlants(){
        addJunglePlant();
        if(!emptyFields.isEmpty()){
        Vector2D newPlantPosition;
        do{
            newPlantPosition=emptyFields.get(new Random().nextInt(emptyFields.size()));
        }while(jungleField.emptyJungleFields.contains(newPlantPosition));
        Grass newPlant=new Grass(newPlantPosition,jungleField.plantEnergy);
        this.grassesHash.put(newPlantPosition,newPlant);
        this.grassesList.add(newPlant);
        this.emptyFields.remove(newPlantPosition);
        }
    }

    public void removePlant(Grass plant){
        this.grassesList.remove(plant);
        this.grassesHash.remove(plant.getPosition());
    }

    public String toString(){
        return visualMap.draw(this.lowerLeft,this.upperRight);
    }


    @Override
    public void positionChanged(Animal animal, Vector2D oldPosition) {
        animalsHash.removeMapping(oldPosition,animal);
        animalsHash.put(animal.getPosition(),animal);
        if(animalsHash.get(oldPosition).isEmpty()) emptyFields.add(oldPosition);
        if(jungleField.isItJungle(oldPosition) && animalsHash.get(oldPosition).isEmpty()
                && !jungleField.emptyJungleFields.contains(oldPosition)) jungleField.emptyJungleFields.add(oldPosition);
        if(jungleField.isItJungle(animal.getPosition()))
            jungleField.emptyJungleFields.remove(animal.getPosition());
        emptyFields.remove(animal.getPosition());
    }
}
