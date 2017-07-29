package io.refactoring.katas;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public static final int TWO = 2;
    public static final int THREE = 3;
    private int height;
    private int width;
    private List<Cell> cells;

    private Grid(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new ArrayList<>();
    }

    static Grid startGame(int height, int width) {
        Grid grid = new Grid(height, width);
        grid.initializeGridWithDeadCells();

        return grid;
    }

    Grid computeNextGeneration(int width, int height) {
        Grid gridToReturn = new Grid(width, height);

        for (Cell currentCell : cells) {
            int totalOfNeighbors = gridToReturn.countLivingNeighbors(cells, currentCell);
            if (isUnderpopulated(totalOfNeighbors) || isOvercrowded(totalOfNeighbors)) {
                currentCell.setState(CellState.DEAD);
            }
            if (!currentCell.isAlive() && totalOfNeighbors == THREE) {
                currentCell.setState(CellState.ALIVE);
            }
        }

        gridToReturn.setCells(cells);
        return gridToReturn;
    }

    private boolean isOvercrowded(int totalOfNeighbors) {
        return totalOfNeighbors > THREE;
    }

    private boolean isUnderpopulated(int totalOfNeighbors) {
        return totalOfNeighbors < TWO;
    }

    void setAllNeighborhoodAsAlive() {
        cells.stream().forEach(c -> c.setState(CellState.ALIVE));
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
        return getCells().size();
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    Cell getCellAtPosition(int index) {
        return cells.get(index);
    }

    void printCells() {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                Cell currentCell = this.getCells().get(j);
                System.out.print(currentCell.isAlive() ? " 1 " : " * ");
            }
            System.out.println();
        }
    }

    List<Cell> getCells() {
        return cells;
    }

    void setAsDead(int cellIndex) {
        Cell cell = this.getCellAtPosition(cellIndex);
        cell.setState(CellState.DEAD);

    }

    private Cell[] getNeighborsOf(Cell currentCell) {

        int currentX = currentCell.getPositionX();
        int currentY = currentCell.getPositionY();

        Cell cellOfTheRight = createCellAtPosition(currentX, currentY + 1);
        Cell cellToTheLeft = createCellAtPosition(currentX, currentY - 1);
        Cell cellToTheTop = createCellAtPosition(currentX - 1, currentY);
        Cell cellInTheBottom = createCellAtPosition(currentX + 1, currentY);

        Cell cellToTheTopRight = createCellAtPosition(cellToTheTop.getPositionX(), currentY + 1);
        Cell cellToTheTopLeft = createCellAtPosition(cellToTheTop.getPositionX(), currentY - 1);

        Cell cellInTheBottomRight = createCellAtPosition(cellInTheBottom.getPositionX(), currentY - 1);
        Cell cellInTheBottomLeft = createCellAtPosition(cellInTheBottom.getPositionX(), currentY + 1);

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

    private void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    private void initializeGridWithDeadCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell cellAtPosition = createCellAtPosition(i, j);
                cells.add(cellAtPosition);
            }
        }
        this.setCells(cells);
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
        for (int i = 0; i < cellIndexes.length; i++) {
            this.setACellAsAlive(cellIndexes[i]);
        }
    }

    private void setACellAsAlive(int cellIndex) {
        Cell cell = this.getCellAtPosition(cellIndex);
        cell.setState(CellState.ALIVE);
    }
}
