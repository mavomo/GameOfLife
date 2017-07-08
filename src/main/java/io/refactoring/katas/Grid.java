package io.refactoring.katas;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int height;
    private int width;
    private List<Cell> cells;

    public Grid(int i, int j) {
        this.height = i;
        this.width = j;
    }

    public List<Cell> initializeCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell cellAtPosition = createCellAtPosition(i, j);
                cells.add(cellAtPosition);
            }
        }
        return cells;
    }


    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        if(this.cells.isEmpty()){
            this.cells = new ArrayList<>();
        }
        return cells;
    }

    public void printCells() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell currentCell = this.cells.get(j);
                System.out.print(currentCell.isAlive() ? " 1 " : " * ");
            }
            System.out.println();
        }

    }

    public int countLivingNeighbors(List<Cell> allCells, final Cell currentCell) {
        Cell cellOfTheRight = createCellAtPosition(currentCell.getPosX(), currentCell.getPosY() + 1);
        Cell cellToTheLeft = createCellAtPosition(currentCell.getPosX(), currentCell.getPosY() - 1);
        Cell cellToTheTop = getCellAbove(currentCell);
        Cell cellToTheTopRight = createCellAtPosition(cellToTheTop.getPosX(), currentCell.getPosY() + 1);
        Cell cellToTheTopLeft = getCellInTheBottomRight(currentCell, cellToTheTop);
        Cell cellInTheBottom = getCellInTheBottom(currentCell.getPosX() + 1, currentCell.getPosY());
        Cell cellInTheBottomLeft = createCellAtPosition(cellInTheBottom.getPosX(), currentCell.getPosY() + 1);
        Cell cellInTheBottomRight = getCellInTheBottomRight(currentCell, cellInTheBottom);

        int totalNeighbors = 0;
        for(Cell neighbor : allCells){
            if (bothCellsAreAlive(currentCell, neighbor) || !currentCell.isAlive() && neighbor.isAlive()) {
                if (hasANeighbor(cellOfTheRight, neighbor)) {
                    totalNeighbors++;
                }
                if ( hasANeighbor(cellToTheLeft, neighbor)){
                    totalNeighbors++;
                }
                if (hasANeighbor(cellToTheTop, neighbor)){
                    totalNeighbors++;
                }
                if (hasANeighbor(cellToTheTopRight, neighbor)){
                    totalNeighbors++;
                }
                if (hasANeighbor(cellToTheTopLeft, neighbor)){
                    totalNeighbors++;
                }
                if (hasANeighbor(cellInTheBottom, neighbor)){
                    totalNeighbors++;
                }
                if (hasANeighbor(cellInTheBottomLeft, neighbor)){
                    totalNeighbors++;
                }
                if (hasANeighbor(cellInTheBottomRight, neighbor)){
                    totalNeighbors++;
                }
            }
        }
        return totalNeighbors;
    }

    private boolean bothCellsAreAlive(Cell currentCell, Cell neighbor) {
        return neighbor.isAlive() && currentCell.isAlive();
    }

    private boolean hasANeighbor(Cell cellToTheLeft, Cell neighbor) {
        return neighbor.getPosX() == cellToTheLeft.getPosX()
                && neighbor.getPosY() == cellToTheLeft.getPosY();
    }


    private Cell getCellInTheBottomRight(Cell cell, Cell cellInTheBottom) {
        return createCellAtPosition(cellInTheBottom.getPosX(), cell.getPosY() - 1);
    }

    private Cell getCellInTheBottom(int posX, int posY) {
        return createCellAtPosition(posX, posY);
    }


    private Cell getCellAbove(Cell cell) {
        return createCellAtPosition(cell.getPosX() - 1, cell.getPosY());
    }

    private Cell createCellAtPosition(int posX, int posY) {
        return new Cell(posX, posY);
    }

}
