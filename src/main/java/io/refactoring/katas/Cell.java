package io.refactoring.katas;

import java.util.Objects;

public class Cell {
    private int posX;
    private int posY;
    private boolean alive;

    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return posX == cell.posX &&
                posY == cell.posY &&
                alive == cell.alive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
