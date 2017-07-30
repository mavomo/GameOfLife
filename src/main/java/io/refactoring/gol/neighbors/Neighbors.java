package io.refactoring.gol.neighbors;

import io.refactoring.gol.Cell;

public class Neighbors {
    /*private Cell neighborOnTheRight;
    private Cell neighborOnTheLeft;
    private Cell neighborOnTheTop;
    private Cell neighborAtTheBottom;
    private Cell neighborOnTheTopRight;
    private Cell neighborOnTheTopLeft;
    private Cell neighborAtTheBottomLeft;
    private Cell neighborAtTheBottomRight;
*/
    private Cell[] neighboors = new Cell[8];

    public Cell[] getNeighboors() {
        return neighboors;
    }


    public void setNeighborOnTheRight(Cell neighborOnTheRight) {
        neighboors[0] = neighborOnTheRight;
    }


    public void setNeighborOnTheLeft(Cell neighborOnTheLeft) {
        neighboors[1] = neighborOnTheLeft;
    }


    public void setNeighborOnTheTop(Cell neighborOnTheTop) {
        neighboors[2] = neighborOnTheTop;
    }


    public void setNeighborAtTheBottom(Cell neighborAtTheBottom) {
        neighboors[3] = neighborAtTheBottom;
    }


    public void setNeighborOnTheTopRight(Cell neighborOnTheTopRight) {
        neighboors[4] = neighborOnTheTopRight;
    }


    public void setNeighborOnTheTopLeft(Cell neighborOnTheTopLeft) {
        neighboors[5] = neighborOnTheTopLeft;
    }


    public void setNeighborAtTheBottomLeft(Cell neighborAtTheBottomLeft) {
        neighboors[6] = neighborAtTheBottomLeft;
    }


    public void setNeighborAtTheBottomRight(Cell bottomRightNeighbor) {
        neighboors[7] = bottomRightNeighbor;
    }

}
