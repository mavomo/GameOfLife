package io.refactoring.gol.neighbors;

import io.refactoring.gol.Cell;

public class Neighborhood {


    private NeighborType neighborType;
    private Cell currentCell;
    private Neighbors neighbors;

    private Neighborhood(Cell currentCell) {
        this.currentCell = currentCell;
        neighbors = new Neighbors();

        neighbors.setNeighborOnTheTop(getNeighborOnTheTop());
        neighbors.setNeighborOnTheRight(getNeighborOnTheRight());
        neighbors.setNeighborOnTheLeft(getNeighborOnTheLeft());
        neighbors.setNeighborAtTheBottom(getNeighborAtTheBottom());
        neighbors.setNeighborAtTheBottomLeft(getNeighborAtTheBottomLeft());
        neighbors.setNeighborAtTheBottomRight(getNeighborAtTheBottomRight());
        neighbors.setNeighborOnTheTopLeft(getNeighborOnTheTopLeft());
        neighbors.setNeighborOnTheTopRight(getNeighborOnTheTopRight());
    }

    public static Neighborhood create(Cell currentCell) {
        Neighborhood neighborhood = new Neighborhood(currentCell);

        return neighborhood;
    }

    private Cell getNeighbor(Cell currentCell, NeighborOrientation neighborOrientation) {
        neighborType = NeighborType.create(neighborOrientation);

        return neighborType.getNeighbor(currentCell);
    }

    public Cell getNeighborOnTheTop() {
        return getNeighbor(currentCell, NeighborOrientation.TOP);
    }


    public Cell getNeighborOnTheRight() {
        return getNeighbor(this.currentCell, NeighborOrientation.RIGHT);
    }

    public Cell getNeighborOnTheLeft() {
        return getNeighbor(currentCell, NeighborOrientation.LEFT);
    }

    public Cell getNeighborAtTheBottom() {
        return getNeighbor(currentCell, NeighborOrientation.BOTTOM);
    }


    public Cell getNeighborOnTheTopRight() {
        return getNeighbor(getNeighborOnTheTop(), NeighborOrientation.TOP_RIGHT);
    }

    public Cell getNeighborOnTheTopLeft() {
        return getNeighbor(getNeighborOnTheTop(), NeighborOrientation.TOP_LEFT);
    }

    public Cell getNeighborAtTheBottomLeft() {
        return getNeighbor(getNeighborAtTheBottom(), NeighborOrientation.BOTTOM_LEFT);
    }

    public Cell getNeighborAtTheBottomRight() {
        return getNeighbor(getNeighborAtTheBottom(), NeighborOrientation.BOTTOM_RIGHT);
    }

    public Cell[] getNeighbors() {
        return neighbors.getNeighboors();
    }
}
