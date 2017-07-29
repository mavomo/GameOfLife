package io.refactoring.gol.neighbors;

import io.refactoring.gol.Cell;
import io.refactoring.gol.neighbors.locations.*;

public abstract class NeighborType {

    public abstract int getNewPositionX(Cell currentCell);

    public abstract int getNewPositionY(Cell currentCell);

    public abstract NeighborOrientation getNeighborPosition();

    public static NeighborType create(NeighborOrientation neighborOrientation){
        switch (neighborOrientation) {
            case TOP:
                return new TopNeighbor();
            case TOP_RIGHT:
                return new TopRightNeighbor();
            case TOP_LEFT:
                return new TopLeftNeighbor();
            case LEFT:
                return new LeftNeighbor();
            case RIGHT:
                return new RightNeighbor();
            case BOTTOM:
                return new BottomNeighbor();
            case BOTTOM_LEFT:
                return new BottomLeftNeighbor();
            case BOTTOM_RIGHT:
                return new BottomRightNeighbor();
            default:
                throw new IllegalArgumentException("Unknown position");
        }

    }

    public Cell getNeighbor(Cell currentCell) {
        Cell cell = new Cell(currentCell.getPositionX(), currentCell.getPositionY());
        cell.setLocation(getNewPositionX(currentCell), getNewPositionY(currentCell));
        return cell;

    }

}
