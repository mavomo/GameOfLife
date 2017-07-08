package io.refactoring.katas;

import java.util.Objects;

public class Cell {
    private int posX;
    private int posY;
    private CellState state;

    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        state = CellState.DEAD;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }


    public CellState getState() {
        return state;
    }

    public boolean isAlive() {
        return this.state == CellState.ALIVE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return posX == cell.posX &&
                posY == cell.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
