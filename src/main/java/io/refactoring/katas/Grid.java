package io.refactoring.katas;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static final int TWO = 2;
    public static final int THREE = 3;
    private int height;
    private int width;
    private List<Cell> initialCells;

    private Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.initialCells = new ArrayList<>();
    }

    static Grid startGame(int height, int width) {
        Grid grid = new Grid(height, width);
        grid.initializeGridWithDeadCells();

        return grid;
    }

    Grid computeNextGeneration(int width, int height) {
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

    void setAllNeighborhoodAsAlive() {
        initialCells.stream().forEach(c -> c.setState(CellState.ALIVE));
    }

    int countLivingNeighbors(List<Cell> allCells, final Cell currentCell) {
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

    int getTotalCells() {
        return getInitialCells().size();
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    Cell getCellAtPosition(int index) {
        return initialCells.get(index);
    }

    List<Cell> getInitialCells() {
        return initialCells;
    }

    void setAsDead(int cellIndex) {
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
        Cell cellOfTheRight = getNeighborhood(currentCell, Neighbor.RIGHT);
        Cell cellToTheLeft = getNeighborhood(currentCell, Neighbor.LEFT);
        Cell cellToTheTop = getNeighborhood(currentCell, Neighbor.TOP);
        Cell cellInTheBottom = getNeighborhood(currentCell, Neighbor.BOTTOM);
        Cell cellToTheTopRight = getNeighborhood(cellToTheTop, Neighbor.TOP_RIGHT);
        Cell cellToTheTopLeft = getNeighborhood(cellToTheTop, Neighbor.TOP_LEFT);
        Cell cellInTheBottomRight = getNeighborhood(cellInTheBottom, Neighbor.BOTTOM_RIGHT);
        Cell cellInTheBottomLeft = getNeighborhood(cellInTheBottom, Neighbor.BOTTOM_LEFT);

        return new Cell[]{
                cellOfTheRight,
                cellToTheLeft,
                cellToTheTop,
                cellToTheTopRight,
                cellToTheTopLeft,
                cellInTheBottom,
                cellInTheBottomLeft,
                cellInTheBottomRight
        };
    }

    private Cell getNeighborhood(Cell currentCell, Neighbor neighborLocation) {
        Cell cell = new Cell(currentCell.getPositionX(), currentCell.getPositionY());
        if (neighborLocation == Neighbor.RIGHT)
            cell.setLocation(currentCell.getPositionX(), currentCell.getPositionY() + 1);
        if (neighborLocation == Neighbor.LEFT)
            cell.setLocation(currentCell.getPositionX(), currentCell.getPositionY() - 1);
        if (neighborLocation == Neighbor.TOP)
            cell.setLocation(currentCell.getPositionX() - 1, currentCell.getPositionY());
        if (neighborLocation == Neighbor.BOTTOM)
            cell.setLocation(currentCell.getPositionX() + 1, currentCell.getPositionY());
        if (neighborLocation == Neighbor.TOP_RIGHT)
            cell.setLocation(currentCell.getPositionX(), currentCell.getPositionY() + 1);
        if (neighborLocation == Neighbor.TOP_LEFT)
            cell.setLocation(currentCell.getPositionX(), currentCell.getPositionY() - 1);
        if (neighborLocation == Neighbor.BOTTOM_RIGHT)
            cell.setLocation(currentCell.getPositionX(), currentCell.getPositionY() - 1);
        if (neighborLocation == Neighbor.BOTTOM_LEFT)
            cell.setLocation(currentCell.getPositionX(), currentCell.getPositionY() + 1);
        return cell;
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

    void setCellsAsAlive(int... cellIndexes) {
        for (int cellIndexe : cellIndexes) {
            this.setACellAsAlive(cellIndexe);
        }
    }

    private void setACellAsAlive(int cellIndex) {
        Cell cell = this.getCellAtPosition(cellIndex);
        cell.setState(CellState.ALIVE);
    }
}
