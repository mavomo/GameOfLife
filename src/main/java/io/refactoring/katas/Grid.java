package io.refactoring.katas;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final int TWO = 2;
    private static final int THREE = 3;
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

        Cell cellFromTheTop = getNeighborhood(currentCell, Neighbor.TOP);
        Cell cellFromTheBottom = getNeighborhood(currentCell, Neighbor.BOTTOM);

        return new Cell[]{
                getNeighborhood(currentCell, Neighbor.RIGHT),
                getNeighborhood(currentCell, Neighbor.LEFT),
                getNeighborhood(currentCell, Neighbor.TOP),
                getNeighborhood(cellFromTheTop, Neighbor.TOP_RIGHT),
                getNeighborhood(cellFromTheTop, Neighbor.TOP_LEFT),
                getNeighborhood(currentCell, Neighbor.BOTTOM),
                getNeighborhood(cellFromTheBottom, Neighbor.BOTTOM_LEFT),
                getNeighborhood(cellFromTheBottom, Neighbor.BOTTOM_RIGHT)
        };
    }

    private Cell getNeighborhood(Cell currentCell, Neighbor neighborLocation) {
        Cell cell = new Cell(currentCell.getPositionX(), currentCell.getPositionY());
        int newPositionX;
        int newPositionY;

        switch (neighborLocation) {
            case TOP:
                newPositionX = currentCell.getPositionX() - 1;
                newPositionY = currentCell.getPositionY();
                break;
            case TOP_RIGHT:
                newPositionX = currentCell.getPositionX();
                newPositionY = currentCell.getPositionY() + 1;
                break;
            case TOP_LEFT:
                newPositionX = currentCell.getPositionX();
                newPositionY = currentCell.getPositionY() - 1;
                break;
            case LEFT:
                newPositionX = currentCell.getPositionX();
                newPositionY = currentCell.getPositionY() - 1;
                break;
            case RIGHT:
                newPositionX = currentCell.getPositionX();
                newPositionY = currentCell.getPositionY() + 1;
                break;
            case BOTTOM:
                newPositionX = currentCell.getPositionX() + 1;
                newPositionY = currentCell.getPositionY();
                break;
            case BOTTOM_LEFT:
                newPositionX = currentCell.getPositionX();
                newPositionY = currentCell.getPositionY() + 1;
                break;
            case BOTTOM_RIGHT:
                newPositionX = currentCell.getPositionX();
                newPositionY = currentCell.getPositionY() - 1;
                break;
            default:
                throw new IllegalArgumentException("Unknown position");
        }
        cell.setLocation(newPositionX, newPositionY);

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
