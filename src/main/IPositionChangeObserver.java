package main;

public interface IPositionChangeObserver {
    void positionChanged(Animal animal, Vector2D newPosition);
}
