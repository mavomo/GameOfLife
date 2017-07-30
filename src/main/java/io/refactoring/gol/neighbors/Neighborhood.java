package io.refactoring.gol.neighbors;

import io.refactoring.gol.Cell;

public class Neighborhood {

    private Cell neighborOnTheRight;
    private Cell neighborOnTheLeft;
    private Cell neighborOnTheTop;
    private Cell neighborAtTheBottom;
    private Cell neighborOnTheTopRight;
    private Cell neighborOnTheTopLeft;
    private Cell neighborAtTheBottomLeft;
    private Cell neighborAtTheBottomRight;

    private NeighborType neighborType;
    private Cell[] neighboors = new Cell[8];

    private Cell getNeighbor(Cell currentCell, NeighborOrientation neighborOrientation) {
        neighborType = NeighborType.create(neighborOrientation);

        return neighborType.getNeighbor(currentCell);
    }

    public Cell getNeighborOnTheRight() {
        return neighborOnTheRight;
    }

    public void setNeighborOnTheRight(Cell currentCell) {
        neighborOnTheRight = getNeighbor(currentCell, NeighborOrientation.RIGHT);
        neighboors[0] = neighborOnTheRight;
    }

    public Cell getNeighborOnTheLeft() {
        return neighborOnTheLeft;
    }

    public void setNeighborOnTheLeft(Cell currentCell) {
        neighborOnTheLeft = getNeighbor(currentCell, NeighborOrientation.LEFT);
        neighboors[1] = neighborOnTheLeft;
    }

    public Cell getNeighborOnTheTop() {
        return neighborOnTheTop;
    }

    public void setNeighborOnTheTop(Cell currentCell) {
        neighborOnTheTop = getNeighbor(currentCell, NeighborOrientation.TOP);
        neighboors[2] = neighborOnTheTop;
    }

    public Cell getNeighborAtTheBottom() {
        return neighborAtTheBottom;
    }

    public void setNeighborAtTheBottom(Cell currentCell) {
        neighborAtTheBottom = getNeighbor(currentCell, NeighborOrientation.BOTTOM);
        neighboors[3] = neighborAtTheBottom;
    }

    public Cell getNeighborOnTheTopRight() {
        return neighborOnTheTopRight;
    }

    public void setNeighborOnTheTopRight(Cell currentCell) {
        currentCell = getNeighborOnTheTop();
        neighborOnTheTopRight = getNeighbor(currentCell, NeighborOrientation.TOP_RIGHT);
        neighboors[4] = neighborOnTheTopRight;

    }

    public Cell getNeighborOnTheTopLeft() {
        return neighborOnTheTopLeft;
    }

    public void setNeighborOnTheTopLeft(Cell currentCell) {
        currentCell = getNeighborOnTheTop();
        neighborOnTheTopLeft = getNeighbor(currentCell, NeighborOrientation.TOP_LEFT);
        neighboors[5] = neighborOnTheTopLeft;
    }

    public Cell getNeighborAtTheBottomLeft() {
        return neighborAtTheBottomLeft;
    }

    public void setNeighborAtTheBottomLeft(Cell currentCell) {
        currentCell = getNeighborAtTheBottom();
        neighborAtTheBottomLeft =  getNeighbor(currentCell, NeighborOrientation.BOTTOM_LEFT);
        neighboors[6] = neighborAtTheBottomLeft;
    }

    public Cell getNeighborAtTheBottomRight() {
        return neighborAtTheBottomRight;
    }

    public void setNeighborAtTheBottomRight(Cell currentCell) {
        currentCell = getNeighborAtTheBottom();
        neighborAtTheBottomRight = getNeighbor(currentCell, NeighborOrientation.BOTTOM_RIGHT);
        neighboors[7] = neighborAtTheBottomRight;
    }

    public Cell[] getNeighboors() {
        return neighboors;
    }
}
