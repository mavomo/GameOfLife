package io.refactoring.gol.neighbors;

import io.refactoring.gol.Cell;

import java.util.List;

public class Neighborhood {

    private NeighborType neighborType;
    private Cell currentCell;
    private Neighbors neighbors;

    private Neighborhood(Cell currentCell) {
        this.currentCell = currentCell;
        neighbors = new Neighbors();

        initializeNeigbhors();
    }

    static Neighborhood create(Cell currentCell) {
        Neighborhood neighborhood = new Neighborhood(currentCell);

        return neighborhood;
    }

    public static int countLivingNeighbors(List<Cell> allCells, final Cell currentCell) {
        Neighborhood neighborhood = create(currentCell);
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

    private Cell getNeighbor(Cell currentCell, NeighborOrientation neighborOrientation) {
        neighborType = NeighborType.create(neighborOrientation);
        return neighborType.getNeighbor(currentCell);
    }

    private Cell getNeighborOnTheTop() {
        return getNeighbor(currentCell, NeighborOrientation.TOP);
    }

    private Cell getNeighborOnTheRight() {
        return getNeighbor(this.currentCell, NeighborOrientation.RIGHT);
    }


    private Cell getNeighborOnTheLeft() {
        return getNeighbor(currentCell, NeighborOrientation.LEFT);
    }

    private Cell getNeighborAtTheBottom() {
        return getNeighbor(currentCell, NeighborOrientation.BOTTOM);
    }

    private Cell getNeighborOnTheTopRight() {
        return getNeighbor(getNeighborOnTheTop(), NeighborOrientation.TOP_RIGHT);
    }


    private Cell getNeighborOnTheTopLeft() {
        return getNeighbor(getNeighborOnTheTop(), NeighborOrientation.TOP_LEFT);
    }

    private Cell getNeighborAtTheBottomLeft() {
        return getNeighbor(getNeighborAtTheBottom(), NeighborOrientation.BOTTOM_LEFT);
    }

    private Cell getNeighborAtTheBottomRight() {
        return getNeighbor(getNeighborAtTheBottom(), NeighborOrientation.BOTTOM_RIGHT);
    }

    public Cell[] getNeighbors() {
        return neighbors.getNeighboors();
    }

    /*public int countNeighbors() {
        int total = 0;

        Cell top = getNeighborOnTheTop();
        if (top.isAlive()) {
            total++;
        }
        if (getNeighborOnTheLeft().isAlive()) {
            total++;
        }
        if (getNeighborOnTheRight().isAlive()) {
            total++;
        }
        if (getNeighborAtTheBottom().isAlive()) {
            total++;
        }
        if (getNeighborOnTheTopLeft().isAlive()){
            total++;
        }
        if(getNeighborOnTheTopRight().isAlive()){
            total++;
        }
        if(getNeighborAtTheBottomLeft().isAlive()){
            total++;
        }
        if(getNeighborAtTheBottomRight().isAlive()){
            total++;
        }
        return total;
    }*/

    private void initializeNeigbhors() {
        neighbors.setNeighborOnTheTop(getNeighborOnTheTop());
        neighbors.setNeighborOnTheRight(getNeighborOnTheRight());
        neighbors.setNeighborOnTheLeft(getNeighborOnTheLeft());
        neighbors.setNeighborAtTheBottom(getNeighborAtTheBottom());
        neighbors.setNeighborAtTheBottomLeft(getNeighborAtTheBottomLeft());
        neighbors.setNeighborAtTheBottomRight(getNeighborAtTheBottomRight());
        neighbors.setNeighborOnTheTopLeft(getNeighborOnTheTopLeft());
        neighbors.setNeighborOnTheTopRight(getNeighborOnTheTopRight());
    }
}
