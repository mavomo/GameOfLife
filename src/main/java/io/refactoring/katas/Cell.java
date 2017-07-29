package io.refactoring.katas;

import java.util.Objects;

import static io.refactoring.katas.CellState.DEAD;

public class Cell {
    private int positionX;
    private int positionY;
    private CellState state;

    Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        state = DEAD;
    }

    void setState(CellState state) {
        this.state = state;
    }

    int getPositionX() {
        return positionX;
    }

    int getPositionY() {
        return positionY;
    }


    boolean isAlive() {
        return this.state.equals(CellState.ALIVE);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return positionX == cell.positionX &&
                positionY == cell.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
