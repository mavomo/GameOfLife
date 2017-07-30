package io.refactoring.katas;

import io.refactoring.gol.Cell;
import io.refactoring.gol.Grid;
import org.junit.Test;

import static io.refactoring.gol.Grid.createGrid;
import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    private Grid grid;

    @Test
    public void
    should_die_by_underpopulation_when_a_live_cell_has_fewer_than_2_live_neighbors_in_a_2x2_grid() {
        grid = createGrid(2, 2);

        Grid newGrid = grid.computeNextGeneration(2, 2);

        Cell firstCellOfNewGen = newGrid.getCellAtPosition(0);

        assertThat(firstCellOfNewGen.isAlive()).isFalse();
    }

    @Test
    public void
    should_die_by_overcrowding_when_a_live_cell_has_more_than_3_neighbors_in_a_4x4_grid() {
        grid = createGrid(4, 4);

        grid.markAllNeighborhoodAsAlive();

        Grid newGeneration = grid.computeNextGeneration(4, 4);
        Cell fifthCellOfNewGen = newGeneration.getCellAtPosition(5);

        assertThat(fifthCellOfNewGen.isAlive()).isFalse();
    }

    @Test
    public void
    should_live_on_the_next_generation_when_a_live_cell_has_3_living_neighbors() {
        grid = createGrid(4, 4);
        grid.markAllNeighborhoodAsAlive();

        Grid newGeneration = grid.computeNextGeneration(4, 4);
        Cell firstCellOfNewGen = newGeneration.getCellAtPosition(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    @Test
    public void
    should_live_on_the_next_generation_when_a_live_cell_has_2_living_neighbors() {
        grid = createGrid(3, 3);

        grid.setCellsAsAlive(0,1,3);

        Grid newGeneration = grid.computeNextGeneration(4, 4);
        Cell firstCellOfNewGen = newGeneration.getCellAtPosition(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    @Test
    public void
    should_live_on_the_next_generation_when_a_dead_cell_has_exactly_3_living_neighbors() {
        grid = createGrid(3, 3);

        grid.setAsDead(0);
        grid.setCellsAsAlive(1,3,4);

        Grid newGeneration = grid.computeNextGeneration(4, 4);
        Cell firstCellOfNewGen = newGeneration.getCellAtPosition(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }
}