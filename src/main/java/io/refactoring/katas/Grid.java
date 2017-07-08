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

    public int countLivingNeighbours(List<Cell> allCells, final Cell cell) {

        int nbNeighbours = 0;
        Cell cellOfTheRight = createCellAtPosition(cell.getPosX(), cell.getPosY() + 1);

        for(Cell c : allCells){
            boolean hasRightNeighboor = c.getPosX() == cellOfTheRight.getPosX() && c.getPosY() == cellOfTheRight.getPosY();
            if (hasRightNeighboor && c.isAlive()){
                   nbNeighbours++;
            }
        }

        if (hasANeighbourToTheLeft(allCells, cell, cell.getPosY() - 1)) {
            nbNeighbours++;
        }

        Cell cellToTheTop = getCellAbove(cell);
        if (hasANeighbour(allCells, cellToTheTop)) {
            nbNeighbours++;
        }

        if (hasATopRightNeighboor(allCells, cell, cellToTheTop)) {
            nbNeighbours++;
        }

        if (hasATopLeftNeighboor(allCells, cell, cellToTheTop)) {
            nbNeighbours++;
        }

        Cell cellInTheBottom = getCellInTheBottom(cell.getPosX() + 1, cell.getPosY());
        if (hasANeighbour(allCells, cellInTheBottom)) {
          nbNeighbours++;
        }

        Cell cellInTheBottomLeft = createCellAtPosition(cellInTheBottom.getPosX(), cell.getPosY() + 1);
        if (hasANeighbour(allCells, cellInTheBottomLeft)) {
            nbNeighbours++;
        }

        if (hasANeighbour(allCells, getCellInTheBottomRight(cell, cellInTheBottom))) {
            nbNeighbours++;
        }

        return nbNeighbours;
    }

    private Cell getCellInTheBottomRight(Cell cell, Cell cellInTheBottom) {
        return createCellAtPosition(cellInTheBottom.getPosX(), cell.getPosY() - 1);
    }

    private Cell getCellInTheBottom(int posX, int posY) {
        return createCellAtPosition(posX, posY);
    }

    private boolean hasATopLeftNeighboor(List<Cell> allCells, Cell cell, Cell cellToTheTop) {
        Cell cellToTheTopLeft = getCellInTheBottomRight(cell, cellToTheTop);

        return hasANeighbour(allCells, cellToTheTopLeft);
    }

    private boolean hasATopRightNeighboor(List<Cell> allCells, Cell cell, Cell cellToTheTop) {
        Cell cellToTheTopRight = createCellAtPosition(cellToTheTop.getPosX(), cell.getPosY() + 1);

        return hasANeighbour(allCells, cellToTheTopRight);
    }

    private Cell getCellAbove(Cell cell) {
        return createCellAtPosition(cell.getPosX() - 1, cell.getPosY());
    }

    private boolean hasANeighbourToTheLeft(List<Cell> allCells, Cell cell, int posY) {
        Cell cellToTheLeft = createCellAtPosition(cell.getPosX(), posY);

        return hasANeighbour(allCells, cellToTheLeft);
    }

    private boolean hasARightNeighbour(List<Cell> allCells, Cell cellOfTheRight) {

        return hasANeighbour(allCells, cellOfTheRight);
    }

    private Cell createCellAtPosition(int posX, int posY) {
        return new Cell(posX, posY);
    }

    private boolean hasANeighbour(List<Cell> allCells, Cell cellOfTheRight) {
        return allCells.contains(cellOfTheRight);
    }

}
