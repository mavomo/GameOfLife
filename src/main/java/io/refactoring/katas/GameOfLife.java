package io.refactoring.katas;

public class GameOfLife {

  /*  public static Grid computeNextGeneration(int width, int height, List<Cell> cells) {
        Grid gridToReturn = new Grid(width, height);

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
    }*/

    /*public static Grid startGame(int height, int width) {
        Grid grid = new Grid(height, width);
        grid.initializeGridWithDeadCells();

        return grid;
    }*/
}
