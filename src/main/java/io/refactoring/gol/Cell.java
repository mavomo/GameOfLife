package io.refactoring.gol;

import java.util.Objects;

import static io.refactoring.gol.CellState.ALIVE;
import static io.refactoring.gol.CellState.DEAD;

public class Cell {
    private int positionX;
    private int positionY;
    private CellState state;

    private Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        state = DEAD;
    }
    public static Cell createCellAtPosition(int posX, int posY) {
        return new Cell(posX, posY);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setLocation(int positionX, int positionY) {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
    }

    public boolean isAlive() {
        return this.state.equals(CellState.ALIVE);
    }

    public static boolean bothCellsAreAlive(Cell currentCell, Cell neighbor) {
        return neighbor.isAlive() && currentCell.isAlive();
    }

    public static boolean deadCellHasALivingNeighbor(Cell currentCell, Cell neighbor) {
        return !currentCell.isAlive() && neighbor.isAlive();
    }

    public static boolean hasANeighbor(Cell currentCell, Cell neighbor) {
        return neighbor.getPositionX() == currentCell.getPositionX()
                && neighbor.getPositionY() == currentCell.getPositionY();
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

    public void markAsAlive() {
        this.setState(ALIVE);
    }

    public void markAsDead() {
        this.setState(DEAD);

    }

    private void setState(CellState state) {
        this.state = state;
    }

    private void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    private void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
