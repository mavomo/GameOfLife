package io.refactoring.gol.neighbors.locations;

import io.refactoring.gol.Cell;
import io.refactoring.gol.neighbors.NeighborOrientation;
import io.refactoring.gol.neighbors.NeighborType;

public class TopNeighbor extends NeighborType {

    @Override
    public int getNewPositionX(Cell currentCell) {
        return currentCell.getPositionX() - 1;
    }

    @Override
    public int getNewPositionY(Cell currentCell) {
        return currentCell.getPositionY();
    }

    @Override
    public NeighborOrientation getNeighborPosition() {
        return NeighborOrientation.TOP;
    }
}
