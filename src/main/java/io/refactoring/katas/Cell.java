package io.refactoring.katas;

import java.util.Objects;

import static io.refactoring.katas.CellState.DEAD;

public class Cell {
    private int posX;
    private int posY;
    private CellState state;

    public Cell(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        state = DEAD;
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


    public boolean isAlive() {
        return this.state.equals(CellState.ALIVE);
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
