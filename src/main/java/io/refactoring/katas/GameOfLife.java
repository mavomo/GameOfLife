package io.refactoring.katas;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    public static List<Cell> grid(int i, int i1) {
        return new ArrayList<>();
    }


    public static Grid computeNextGeneration(int posX, int posY, List<Cell> cells) {
        Grid gridToReturn = new Grid(posX, posY);

        for(Cell currentCell : cells){
            int totalOfNeighbors = gridToReturn.countLivingNeighbors(cells, currentCell);
            if (totalOfNeighbors < 2 || totalOfNeighbors > 3){
                currentCell.setState(CellState.DEAD);
            }
            if (!currentCell.isAlive() && totalOfNeighbors == 3){
                currentCell.setState(CellState.ALIVE);
            }
        }

        gridToReturn.setCells(cells);

        return gridToReturn;
    }
}
