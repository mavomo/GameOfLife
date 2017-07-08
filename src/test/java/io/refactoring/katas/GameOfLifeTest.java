package io.refactoring.katas;

import org.junit.Test;

import java.util.List;

import static io.refactoring.katas.GameOfLife.startGame;
import static io.refactoring.katas.GameOfLifeUtils.setAllNeighborhoodAsAlive;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeTest {
    private Grid grid;


    @Test
    public void should_create_a_grid_given_as_the_height_and_a_width_as_parameters() {
        grid = startGame(4, 8);

        assertThat(grid.getHeight()).isEqualTo(4);
        assertThat(grid.getWidth()).isEqualTo(8);
    }

    @Test
    public void should_initialize_a_list_of_dead_cells_when_the_game_starts() {
        grid = startGame(4, 8);

        assertThat(grid.getCells()).isNotEmpty();
        assertThat(grid.getCells()).allMatch(c -> !c.isAlive());
    }

    @Test
    public void should_die_by_underpopulation_when_a_live_cell_has_fewer_than_2_live_neighbors_in_a_2x2_grid() {
        grid = startGame(2, 2);

        List<Cell> cells = grid.getCells();

        cells.get(0).setState(CellState.ALIVE);
        cells.get(1).setState(CellState.ALIVE);

        Grid newGeneration = GameOfLife.computeNextGeneration(2, 2, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isFalse();
    }

    @Test
    public void should_die_by_overcrowding_when_a_live_cell_has_more_than_3_neighbors_in_a_4x4_grid() {
        grid = startGame(4, 4);

        List<Cell> cells = grid.getCells();
        setAllNeighborhoodAsAlive(cells);

        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firthCellOfNewGen = newGeneration.getCells().get(5);

        assertThat(firthCellOfNewGen.isAlive()).isFalse();
    }

    @Test
    public void should_live_on_the_next_generation_when_a_live_cell_has_3_neighbors() {
        grid = startGame(4, 4);
        List<Cell> cells = grid.getCells();
        setAllNeighborhoodAsAlive(cells);

        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    @Test
    public void should_live_on_the_next_generation_when_a_live_cell_has_2_neighbors() {
        grid = startGame(3, 3);
        List<Cell> cells = grid.getCells();

        cells.get(0).setState(CellState.ALIVE);
        cells.get(1).setState(CellState.ALIVE);
        cells.get(3).setState(CellState.ALIVE);

        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    @Test
    public void should_live_on_the_next_generation_when_a_dead_cell_has_exactly_3_neighbors() {
        grid = startGame(3, 3);

        List<Cell> cells = grid.getCells();

        cells.get(0).setState(CellState.DEAD);
        cells.get(1).setState(CellState.ALIVE);
        cells.get(3).setState(CellState.ALIVE);
        cells.get(4).setState(CellState.ALIVE);


        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }
}