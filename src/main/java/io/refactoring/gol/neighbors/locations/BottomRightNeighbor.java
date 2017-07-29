package io.refactoring.gol.neighbors.locations;

import io.refactoring.gol.Cell;
import io.refactoring.gol.neighbors.NeighborOrientation;
import io.refactoring.gol.neighbors.NeighborType;

import static io.refactoring.gol.neighbors.NeighborOrientation.BOTTOM_RIGHT;

public class BottomRightNeighbor extends NeighborType {
    @Override
    public int getNewPositionX(Cell currentCell) {
        return currentCell.getPositionX();
    }

    @Override
    public int getNewPositionY(Cell currentCell) {
        return currentCell.getPositionY() - 1;
    }

    @Override
    public NeighborOrientation getNeighborPosition() {
        return BOTTOM_RIGHT;
    }
}