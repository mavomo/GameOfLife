package io.refactoring.katas;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int height;
    private int width;


    public Grid(int i, int j) {
        this.height = i;
        this.width = j;
    }

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(" * ");
                cells.add(new Cell(i, j));
            }
            System.out.println();
        }

        if (cells.size() == 3) {
            cells.stream().forEach(c -> c.setAlive(true));
        }
        return cells;
    }

    public int countNeigbourhood(List<Cell> allCells, final Cell cell) {

        int nbNeighbours = 0;

        Cell cellOfTheRight = new Cell(cell.getPosX(), cell.getPosY()+1);
        if (hasANeighbour(allCells, cellOfTheRight)){
            nbNeighbours++;
        }

        Cell cellToTheLeft = new Cell(cell.getPosX(), cell.getPosY()-1);
        if (hasANeighbour(allCells, cellToTheLeft)){
            nbNeighbours++;
        }

        Cell cellToTheTop = new Cell(cell.getPosX() - 1, cell.getPosY());
        if (hasANeighbour(allCells, cellToTheTop)){
            nbNeighbours++;
        }

        Cell cellToTheTopRight = new Cell(cellToTheTop.getPosX(), cell.getPosY() +1);
        if (hasANeighbour(allCells, cellToTheTopRight)){
            nbNeighbours++;
        }

        Cell cellToTheTopLeft = new Cell(cellToTheTop.getPosX(), cell.getPosY() - 1);
        if (hasANeighbour(allCells, cellToTheTopLeft)){
            nbNeighbours++;
        }

        Cell cellInTheBottom = new Cell(cell.getPosX()+1, cell.getPosY());
        if (hasANeighbour(allCells, cellInTheBottom)){
            nbNeighbours++;
        }

        Cell cellInTheBottomLeft = new Cell(cellInTheBottom.getPosX(), cell.getPosY()+1);
        if (hasANeighbour(allCells, cellInTheBottomLeft)){
            nbNeighbours++;
        }

        Cell cellInTheBottomRight = new Cell(cellInTheBottom.getPosX(), cell.getPosY()-1);
        if (hasANeighbour(allCells, cellInTheBottomRight)){
            nbNeighbours++;
        }

        return nbNeighbours;
    }

    private boolean hasANeighbour(List<Cell> allCells, Cell cellOfTheRight) {
        return allCells.contains(cellOfTheRight);
    }

}
