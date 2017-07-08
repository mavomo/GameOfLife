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

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Cell cellAtPosition = createCellAtPosition(i, j);
                cells.add(cellAtPosition);
            }
        }
        this.cells = cells;

        return cells;
    }


    public void setCells(List<Cell> cells) {
        this.cells = cells;
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

    public int countLivingNeighbours(List<Cell> allCells, final Cell currentCell) {
        int nbNeighbours = 0;
        Cell cellOfTheRight = createCellAtPosition(currentCell.getPosX(), currentCell.getPosY() + 1);
        Cell cellToTheLeft = createCellAtPosition(currentCell.getPosX(), currentCell.getPosY() - 1);

        for(Cell neighboor : allCells){
            if (bothCellsAreAlive(currentCell, neighboor)) {
                if (hasANeighbour(cellOfTheRight, neighboor)) {
                    nbNeighbours++;
                }
                if ( hasANeighbour(cellToTheLeft, neighboor)){
                    nbNeighbours++;
                }
            }

        }


        Cell cellToTheTop = getCellAbove(currentCell);
        if (allCells.contains(cellToTheTop)) {
            nbNeighbours++;
        }

        if (hasATopRightNeighboor(allCells, currentCell, cellToTheTop)) {
            nbNeighbours++;
        }

        if (hasATopLeftNeighboor(allCells, currentCell, cellToTheTop)) {
            nbNeighbours++;
        }

        Cell cellInTheBottom = getCellInTheBottom(currentCell.getPosX() + 1, currentCell.getPosY());
        if (allCells.contains(cellInTheBottom)) {
          nbNeighbours++;
        }

        Cell cellInTheBottomLeft = createCellAtPosition(cellInTheBottom.getPosX(), currentCell.getPosY() + 1);
        if (allCells.contains(cellInTheBottomLeft)) {
            nbNeighbours++;
        }

        if (allCells.contains(getCellInTheBottomRight(currentCell, cellInTheBottom))) {
            nbNeighbours++;
        }

        return nbNeighbours;
    }

    private boolean bothCellsAreAlive(Cell currentCell, Cell neighboor) {
        return neighboor.isAlive() && currentCell.isAlive();
    }

    private boolean hasANeighbour(Cell cellToTheLeft, Cell neighboor) {
        return neighboor.getPosX() == cellToTheLeft.getPosX()
                && neighboor.getPosY() == cellToTheLeft.getPosY();
    }


    private Cell getCellInTheBottomRight(Cell cell, Cell cellInTheBottom) {
        return createCellAtPosition(cellInTheBottom.getPosX(), cell.getPosY() - 1);
    }

    private Cell getCellInTheBottom(int posX, int posY) {
        return createCellAtPosition(posX, posY);
    }

    private boolean hasATopLeftNeighboor(List<Cell> allCells, Cell cell, Cell cellToTheTop) {
        Cell cellToTheTopLeft = getCellInTheBottomRight(cell, cellToTheTop);

        return allCells.contains(cellToTheTopLeft);
    }

    private boolean hasATopRightNeighboor(List<Cell> allCells, Cell cell, Cell cellToTheTop) {
        Cell cellToTheTopRight = createCellAtPosition(cellToTheTop.getPosX(), cell.getPosY() + 1);

        return allCells.contains(cellToTheTopRight);
    }

    private Cell getCellAbove(Cell cell) {
        return createCellAtPosition(cell.getPosX() - 1, cell.getPosY());
    }

    private boolean hasARightNeighbour(List<Cell> allCells, Cell cellOfTheRight) {

        return allCells.contains(cellOfTheRight);
    }

    private Cell createCellAtPosition(int posX, int posY) {
        return new Cell(posX, posY);
    }

}
