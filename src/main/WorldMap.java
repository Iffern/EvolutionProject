package main;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import javax.swing.*;
import java.util.*;

public class WorldMap implements IPositionChangeObserver,IWorldMap {
    MultiValuedMap<Vector2D, Animal> animalsHash = new ArrayListValuedHashMap<>();
    List<Animal> animalList = new ArrayList<>();
    Map<Vector2D, Grass> grassesHash = new HashMap<>();
    List<Grass> grassesList = new ArrayList<>();
    List<Vector2D> emptyFields = new ArrayList<>();
    Jungle jungleField;
    Vector2D upperRight;
    Vector2D lowerLeft;
    VisualMap visualizer;

    public WorldMap(int width, int height, double jungleRatio, double plantEnergy) {
        this.upperRight = new Vector2D(width - 1, height - 1);
        this.lowerLeft = new Vector2D(0, 0);
        this.visualizer = new VisualMap(width, height);
        this.jungleField = new Jungle(width, height, jungleRatio, plantEnergy);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                emptyFields.add(new Vector2D(i, j));
            }
        }
        preFillJungle();
    }

    @Override
    public void place(Animal animal) {
        animal.addObserver(this);
        animalsHash.put(animal.getPosition(), animal);
        animalList.add(animal);
        emptyFields.remove(animal.getPosition());
        if (jungleField.isItJungle(animal.getPosition()))
            jungleField.emptyJungleFields.remove(animal.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return !(objectAt(position) == null);
    }

    @Override
    public Object objectAt(Vector2D position) {
        if (animalsHash.containsKey(position)) return animalsHash.get(position);
        if (grassesHash.containsKey(position)) return grassesHash.get(position);
        return null;
    }

    public Vector2D randomAdjacentPosition(Vector2D position) {
        Random random = new Random();
        int randIdx = random.nextInt(8);
        MapDirection[] mapDirections = MapDirection.values();
        Vector2D randomPos;
        int i = 0;
        do {
            randomPos = position.add(mapDirections[(randIdx + i) % 8].toUnitVector());
            i++;
        } while (isOccupied(randomPos) && i < 8);
        if (i == 8) randomPos = position.add(mapDirections[random.nextInt(8)].toUnitVector());

        return randomPos;
    }

    public Vector2D randomPosition() {
        Random random = new Random();
        Vector2D position;
        do {
            position = new Vector2D(random.nextInt(upperRight.x + 1), random.nextInt(upperRight.y + 1));
        } while (animalsHash.containsKey(position));
        return position;
    }

    Vector2D positionOnMap(Vector2D position){
        Vector2D positionOnMap=position;
        if(positionOnMap.x>this.upperRight.x) positionOnMap=positionOnMap.subtract(new Vector2D(this.upperRight.x+1,0));
        if(positionOnMap.x<0) positionOnMap=positionOnMap.add(new Vector2D(this.upperRight.x+1,0));
        if(positionOnMap.y>this.upperRight.y) positionOnMap=positionOnMap.subtract(new Vector2D(0,this.upperRight.y+1));
        if(positionOnMap.y<0) positionOnMap=positionOnMap.add(new Vector2D(0,this.upperRight.y+1));
        return positionOnMap;
    }

    private void preFillJungle() {
        int size = (this.jungleField.upperRight.x - this.jungleField.lowerLeft.x + 1) *
                (this.jungleField.upperRight.y - this.jungleField.lowerLeft.y + 1);
        int grassNumber = (int) Math.sqrt(size);
        for (int i = 0; i < grassNumber; i++) {
            addJunglePlant();
        }
    }

    private void addJunglePlant() {
        if (!jungleField.emptyJungleFields.isEmpty() && !emptyFields.isEmpty()) {
            Vector2D newGrassPosition;
            do {
                newGrassPosition = jungleField.emptyJungleFields.get(new Random().nextInt(jungleField.emptyJungleFields.size()));
            }
            while (!emptyFields.contains(newGrassPosition));
            Grass newGrass = new Grass(newGrassPosition, this.jungleField.plantEnergy);
            this.grassesHash.put(newGrassPosition, newGrass);
            this.grassesList.add(newGrass);
            emptyFields.remove(newGrassPosition);
            jungleField.emptyJungleFields.remove(newGrassPosition);
            visualizer.mapPanel.mapEvent(newGrassPosition,true,MapEvents.NEW_GRASS);
        }
    }

    public void growNewPlants() {
        addJunglePlant();
        if (!emptyFields.isEmpty()) {
            Vector2D newPlantPosition;
            do {
                newPlantPosition = emptyFields.get(new Random().nextInt(emptyFields.size()));
            } while (jungleField.emptyJungleFields.contains(newPlantPosition));
            Grass newPlant = new Grass(newPlantPosition, jungleField.plantEnergy);
            this.grassesHash.put(newPlantPosition, newPlant);
            this.grassesList.add(newPlant);
            this.emptyFields.remove(newPlantPosition);
            visualizer.mapPanel.mapEvent(newPlantPosition,false,MapEvents.NEW_GRASS);
        }
    }

    public void removePlant(Grass plant) {
        this.grassesList.remove(plant);
        this.grassesHash.remove(plant.getPosition());
    }

    public void animalDied(Vector2D position){
        visualizer.mapPanel.mapEvent(position,jungleField.isItJungle(position),MapEvents.DEATH);
    }

    public void isBreeding(Vector2D position){
        visualizer.mapPanel.mapEvent(position,jungleField.isItJungle(position),MapEvents.BREED);
    }

    @Override
    public void positionChanged(Animal animal, Vector2D oldPosition) {
        animalsHash.removeMapping(oldPosition, animal);
        animalsHash.put(animal.getPosition(), animal);
        if (animalsHash.get(oldPosition).isEmpty()) emptyFields.add(oldPosition);
        if (jungleField.isItJungle(oldPosition) && animalsHash.get(oldPosition).isEmpty()
                && !jungleField.emptyJungleFields.contains(oldPosition)) jungleField.emptyJungleFields.add(oldPosition);
        if (jungleField.isItJungle(animal.getPosition()))
            jungleField.emptyJungleFields.remove(animal.getPosition());
        emptyFields.remove(animal.getPosition());
        visualizer.mapPanel.changedPosition(oldPosition,animal.getPosition(),animalsHash.get(oldPosition).size(),
                animalsHash.get(animal.getPosition()).size(),jungleField.isItJungle(oldPosition),
                jungleField.isItJungle(animal.getPosition()));
    }

    public void renderMainPanel() {
        for (int y = upperRight.y; y >= 0; y--) {
            for (int x = 0; x <= upperRight.x; x++) {
                Vector2D position = new Vector2D(x, y);
                boolean jungleField = this.jungleField.isItJungle(position);
                Grass plantOnPosition = null;
                Animal[] animalsOnPosition = null;
                if (this.isOccupied(position)) {
                    Object object = this.objectAt(position);
                    if (object instanceof Grass) {
                        plantOnPosition = (Grass) object;
                    } else if (object instanceof Collection) {
                        animalsOnPosition = (Animal[]) ((Collection) object).toArray(new Animal[((Collection) object).size()]);
                    }
                }

                JLabel label = visualizer.mapPanel.labels.get(position);
                label.setText("");
                ImageIcon background = (jungleField ? MapElementsRepresentation.jungleRepresentation :
                        MapElementsRepresentation.savannahRepresentation);
                background = visualizer.mapPanel.resizeBigIcon(background);
                Icon icon;
                if (plantOnPosition == null && animalsOnPosition == null) icon = background;
                else if (plantOnPosition != null)
                    icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                            CompoundIcon.CENTER, background, visualizer.mapPanel.resizeSmallIcon(MapElementsRepresentation.grassRepresentation));
                else {
                    if (animalsOnPosition.length == 1)
                        icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                                CompoundIcon.CENTER, background, visualizer.mapPanel.resizeSmallIcon(MapElementsRepresentation.animalRepresentation));
                    else if (animalsOnPosition.length == 2)
                        icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                                CompoundIcon.CENTER, background, visualizer.mapPanel.resizeBigIcon(MapElementsRepresentation.twoAnimalsRepresentation));
                    else if (animalsOnPosition.length == 3)
                        icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                                CompoundIcon.CENTER, background, visualizer.mapPanel.resizeBigIcon(MapElementsRepresentation.threeAnimalsRepresentation));
                    else icon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, 0, CompoundIcon.CENTER,
                                CompoundIcon.CENTER, background, visualizer.mapPanel.resizeBigIcon(MapElementsRepresentation.manyAnimalsRepresentation));
                }
                label.setIcon(icon);
            }
        }
    }
}
