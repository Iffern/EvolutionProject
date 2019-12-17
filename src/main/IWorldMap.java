package main;

public interface IWorldMap {
    void place(Animal animal);
    boolean isOccupied(Vector2D position);
    Object objectAt(Vector2D position);
    Vector2D randomAdjacentPosition(Vector2D position);
}
