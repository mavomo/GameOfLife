package io.refactoring.gol;

import io.refactoring.gol.neighbors.NeighborOrientation;
import io.refactoring.gol.neighbors.NeighborType;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final int TWO = 2;
    private static final int THREE = 3;

    private int height;
    private int width;
    private List<Cell> initialCells;
    private NeighborType neighborType;

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
        Grid gridToReturn = new Grid(width, height);
        List<Cell> newCells = initialCells;

        for (Cell currentCell : newCells) {
            int totalOfNeighbors = gridToReturn.countLivingNeighbors(newCells, currentCell);

            if (isUnderpopulated(totalOfNeighbors) || isOvercrowded(totalOfNeighbors)) {
                currentCell.setState(CellState.DEAD);
            }
            if (!currentCell.isAlive() && totalOfNeighbors == THREE) {
                currentCell.setState(CellState.ALIVE);
            }
        }

        gridToReturn.setInitialCells(newCells);
        return gridToReturn;
    }

    public void setAllNeighborhoodAsAlive() {
        initialCells.stream().forEach(c -> c.setState(CellState.ALIVE));
    }

    public int countLivingNeighbors(List<Cell> allCells, final Cell currentCell) {
        Cell[] cells = getNeighborsOf(currentCell);
        int totalNeighbors = 0;
        for (Cell neighbor : allCells) {
            if (bothCellsAreAlive(currentCell, neighbor) || !currentCell.isAlive() && neighbor.isAlive()) {
                for (Cell cell : cells) {
                    if (hasANeighbor(cell, neighbor)) {
                        totalNeighbors++;
                    }
                }
            }
        }
        return totalNeighbors;
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
        cell.setState(CellState.DEAD);

    }

    private boolean isOvercrowded(int totalOfNeighbors) {
        return totalOfNeighbors > THREE;
    }

    private boolean isUnderpopulated(int totalOfNeighbors) {
        return totalOfNeighbors < TWO;
    }

    private Cell[] getNeighborsOf(Cell currentCell) {

        Cell cellFromTheTop = getNeighborhood(currentCell, NeighborOrientation.TOP);
        Cell cellFromTheBottom = getNeighborhood(currentCell, NeighborOrientation.BOTTOM);

        return new Cell[]{
                getNeighborhood(currentCell, NeighborOrientation.RIGHT),
                getNeighborhood(currentCell, NeighborOrientation.LEFT),
                getNeighborhood(currentCell, NeighborOrientation.TOP),
                getNeighborhood(cellFromTheTop, NeighborOrientation.TOP_RIGHT),
                getNeighborhood(cellFromTheTop, NeighborOrientation.TOP_LEFT),
                getNeighborhood(currentCell, NeighborOrientation.BOTTOM),
                getNeighborhood(cellFromTheBottom, NeighborOrientation.BOTTOM_LEFT),
                getNeighborhood(cellFromTheBottom, NeighborOrientation.BOTTOM_RIGHT)
        };
    }

    private Cell getNeighborhood(Cell currentCell, NeighborOrientation neighborOrientation) {
        neighborType = NeighborType.create(neighborOrientation);

        return neighborType.getNeighbor(currentCell);
    }

    private void setInitialCells(List<Cell> initialCells) {
        this.initialCells = initialCells;
    }

    private void initializeGridWithDeadCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell cellAtPosition = createCellAtPosition(i, j);
                cells.add(cellAtPosition);
            }
        }
        this.setInitialCells(cells);
    }

    private boolean bothCellsAreAlive(Cell currentCell, Cell neighbor) {
        return neighbor.isAlive() && currentCell.isAlive();
    }

    private boolean hasANeighbor(Cell cellToTheLeft, Cell neighbor) {
        return neighbor.getPositionX() == cellToTheLeft.getPositionX()
                && neighbor.getPositionY() == cellToTheLeft.getPositionY();
    }

    private Cell createCellAtPosition(int posX, int posY) {
        return new Cell(posX, posY);
    }

    public void setCellsAsAlive(int... cellIndexes) {
        for (int cellIndexe : cellIndexes) {
            this.setACellAsAlive(cellIndexe);
        }
    }

    private void setACellAsAlive(int cellIndex) {
        Cell cell = this.getCellAtPosition(cellIndex);
        cell.setState(CellState.ALIVE);
    }
}
