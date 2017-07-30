package io.refactoring.gol;

import io.refactoring.gol.neighbors.Neighborhood;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final int MIN_NEIGHBOOR_TO_BEAR = 2;
    private static final int MAX_NEIGHBOOR_TO_BEAR = 3;

    private int height;
    private int width;
    private List<Cell> initialCells;

    private Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.initialCells = new ArrayList<>();
    }

    public static Grid createGrid(int height, int width) {
        Grid grid = new Grid(height, width);
        grid.initializeGridWithDeadCells();
        return grid;
    }

    public Grid computeNextGeneration(int width, int height) {
        Grid newGrid = new Grid(width, height);
        List<Cell> newCells = initialCells;

        for (Cell currentCell : newCells) {
            int totalOfNeighbors = Neighborhood.countLivingNeighbors(newCells, currentCell);

            if (isUnderpopulated(totalOfNeighbors) || isOvercrowded(totalOfNeighbors)) {
                currentCell.markAsDead();
            }
            if (!currentCell.isAlive() && totalOfNeighbors == MAX_NEIGHBOOR_TO_BEAR) {
                currentCell.markAsAlive();
            }
        }

        newGrid.setInitialCells(newCells);
        return newGrid;
    }


    public void markAllNeighborhoodAsAlive() {
        initialCells.stream().forEach(c -> c.markAsAlive());
    }

    public int getTotalCells() {
        return getInitialCells().size();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCellAtPosition(int index) {
        return initialCells.get(index);
    }

    public List<Cell> getInitialCells() {
        return initialCells;
    }

    public void setAsDead(int cellIndex) {
        Cell cell = this.getCellAtPosition(cellIndex);
        cell.markAsDead();
    }

    private boolean isOvercrowded(int totalOfNeighbors) {
        return totalOfNeighbors > MAX_NEIGHBOOR_TO_BEAR;
    }

    private boolean isUnderpopulated(int totalOfNeighbors) {
        return totalOfNeighbors < MIN_NEIGHBOOR_TO_BEAR;
    }


    private void setInitialCells(List<Cell> initialCells) {
        this.initialCells = initialCells;
    }

    private void initializeGridWithDeadCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell cellAtPosition = Cell.createCellAtPosition(i, j);
                cells.add(cellAtPosition);
            }
        }
        this.setInitialCells(cells);
    }

    public void setCellsAsAlive(int... cellIndexes) {
        for (int cellIndexe : cellIndexes) {
            Cell cell = this.getCellAtPosition(cellIndexe);
            cell.markAsAlive();
        }
    }

}
