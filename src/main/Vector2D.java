package main;

import java.util.Objects;
import java.util.Random;

public class Vector2D {
        final int x;
        final int y;

        public Vector2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return ("(" + Integer.toString(this.x) + "," + Integer.toString(this.y) + ")");
        }

        public boolean precedes(Vector2D other) {
            if (other == null) return false;
            return this.x <= other.x && this.y <= other.y;
        }

        public boolean follows(Vector2D other) {
            if (other == null) return false;
            return this.x >= other.x && this.y >= other.y;
        }

        public Vector2D upperRight(Vector2D other) {
            if (other == null) return null;
            int rx, ry;
            if (this.x > other.x) rx = this.x;
            else rx = other.x;
            if (this.y > other.y) ry = this.y;
            else ry = other.y;
            return new Vector2D(rx, ry);
        }

        public Vector2D lowerLeft(Vector2D other) {
            if (other == null) return null;
            int lx, ly;
            if (other.x > this.x) lx = this.x;
            else lx = other.x;
            if (other.y > this.y) ly = this.y;
            else ly = other.y;
            return new Vector2D(lx, ly);
        }

        public Vector2D add(Vector2D other) {
            if (other == null) return null;
            int adx, ady;
            adx = this.x + other.x;
            ady = this.y + other.y;
            return new Vector2D(adx, ady);
        }

        public Vector2D subtract(Vector2D other) {
            if (other == null) return null;
            int sux, suy;
            sux = this.x - other.x;
            suy = this.y - other.y;
            return new Vector2D(sux, suy);
        }

    public Vector2D opposite() {
            Vector2D oppVector = new Vector2D(-this.x, -this.y);
            return oppVector;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return x == vector2D.x &&
                y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
