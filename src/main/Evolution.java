package main;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Evolution {
    int startAnimalEnergy;
    int moveAnimalEnergy;
    int startAnimalNumber;
    WorldMap world;

    public Evolution(int width, int height, double jungleRatio, int startEnergy, int moveEnergy, int plantEnergy, int startAnimalNumber) {
        this.startAnimalEnergy = startEnergy;
        this.moveAnimalEnergy = moveEnergy;
        this.startAnimalNumber=startAnimalNumber;
        world = new WorldMap(width, height, jungleRatio, plantEnergy);
    }

    public void startEvolution() {
        for (int i = 0; i < startAnimalNumber; i++) {
            Animal newAnimal = new Animal(world, startAnimalEnergy, moveAnimalEnergy);
            world.place(newAnimal);
        }

        while (!world.animalList.isEmpty()) {

            removeDeadAnimals();

            moveAllAnimals();

            letAnimalsEat();

            letAnimalsBreed();

            world.growNewPlants();

            System.out.println(world.toString());
        }
    }
    private void removeDeadAnimals(){
        List<Animal> animalToRemove=new ArrayList<>();
        for (Animal animal : world.animalList) {
            if (animal.isDead()) {
                animalToRemove.add(animal);
            }
        }
        if(!animalToRemove.isEmpty()){
            for(Animal animal:animalToRemove){
                world.animalsHash.removeMapping(animal.getPosition(),animal);
                world.animalList.remove(animal);
            }
        }
    }
    private void moveAllAnimals(){
        for(Animal animal: world.animalList) {
            animal.move();
        }
    }
    private void letAnimalsEat(){
        for(Vector2D animalPosition: world.animalsHash.keySet()){
            Animal[] animals=world.animalsHash.get(animalPosition).toArray(new Animal[world.animalsHash.get(animalPosition).size()]);
            int howManyAnimals=animals.length;
            if(world.grassesHash.containsKey(animalPosition)){
                Grass food=world.grassesHash.get(animalPosition);
                if(howManyAnimals==1){
                    Animal animal=animals[0];
                    animal.eat(food,1);
                    world.removePlant(food);
                }
                if(howManyAnimals>1){
                    Comparator<Animal> compareEnergy = Comparator.comparingInt(ani -> ani.getAnimalEnergy().getCurrentEnergy());
                    Arrays.sort(animals,compareEnergy);
                    int maxEnergy=animals[animals.length-1].getAnimalEnergy().getCurrentEnergy();
                    int i=animals.length-1;
                    int strongest=0;
                    while(i>=0 && animals[i].getAnimalEnergy().getCurrentEnergy()==maxEnergy){
                        strongest++;
                        i--;
                    }
                    while(i>=0 && animals[i].getAnimalEnergy().getCurrentEnergy()==maxEnergy){
                        animals[i].eat(food,strongest);
                        i--;
                    }
                    world.removePlant(food);
                }
            }
        }
    }
    private void letAnimalsBreed(){
        Animal mother=null;
        Animal father=null;
        Animal baby=null;
        for(Iterator<Vector2D> it= world.animalsHash.keySet().iterator();it.hasNext();){
            Vector2D animalPosition=it.next();
            Animal[] animals=world.animalsHash.get(animalPosition).toArray(new Animal[world.animalsHash.get(animalPosition).size()]);
            int howManyAnimals=animals.length;
            if(howManyAnimals==2){
                mother=animals[0];
                father= animals[1];
            }
            else if(howManyAnimals>=2){
                Comparator<Animal> compareEnergy = Comparator.comparingInt(ani -> ani.getAnimalEnergy().getCurrentEnergy());
                Arrays.sort(animals,compareEnergy);
                mother=animals[animals.length-1];
                father=animals[animals.length-2];
            }
        }
        if(mother!=null && father!=null && mother.canBreed() && father.canBreed()){
            baby=mother.breed(father);
            world.place(baby);}
    }
}