package io.refactoring.gol;

import java.util.Objects;

import static io.refactoring.gol.CellState.DEAD;

public class Cell {
    private int positionX;
    private int positionY;
    private CellState state;

    public Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        state = DEAD;
    }

    void setState(CellState state) {
        this.state = state;
    }

    public int getPositionX() {
        return positionX;
    }

   public int getPositionY() {
        return positionY;
    }

    private void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    private void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setLocation(int positionX, int positionY) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
    }


   public boolean isAlive() {
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
