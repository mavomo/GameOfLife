package io.refactoring.katas;

import java.util.List;


public class GameOfLifeUtils {

    public static final void setAllNeighborhoodAsAlive(List<Cell> allCells) {
        allCells.stream().forEach(c -> c.setState(CellState.ALIVE));
    }

    public static void printCells(Grid grid) {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Cell currentCell = grid.getCells().get(j);
                System.out.print(currentCell.isAlive() ? " 1 " : " * ");
            }
            System.out.println();
        }
    }


}
