package io.refactoring.gol;

import io.refactoring.gol.neighbors.Neighborhood;

import java.util.List;
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

    void setState(CellState state) {
        this.state = state;
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

    static boolean bothCellsAreAlive(Cell currentCell, Cell neighbor) {
        return neighbor.isAlive() && currentCell.isAlive();
    }


   private static boolean deadCellHasALivingNeighbor(Cell currentCell, Cell neighbor) {
        return !currentCell.isAlive() && neighbor.isAlive();
    }


    private static boolean hasANeighbor(Cell cellToTheLeft, Cell neighbor) {
        return neighbor.getPositionX() == cellToTheLeft.getPositionX()
                && neighbor.getPositionY() == cellToTheLeft.getPositionY();
    }

    public static int countLivingNeighbors(List<Cell> allCells, final Cell currentCell) {
        Neighborhood neighborhood = Neighborhood.create(currentCell);
        int totalNeighbors = 0;
        for (Cell neighbor : allCells) {
            if (Cell.bothCellsAreAlive(currentCell, neighbor) || Cell.deadCellHasALivingNeighbor(currentCell, neighbor)) {
                for (Cell cell : neighborhood.getNeighbors()) {
                    if (Cell.hasANeighbor(cell, neighbor)) {
                        totalNeighbors++;
                    }
                }
            }
        }
        return totalNeighbors;
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

    private void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    private void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
