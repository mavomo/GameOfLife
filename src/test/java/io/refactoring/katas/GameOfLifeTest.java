package io.refactoring.katas;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeTest {

    private Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(1, 1);
    }

    @Test
    public void should_contain_one_cell_given_1x1_grid() {
        assertThat(grid.initializeCells()).containsExactly(new Cell(0, 0));
    }

    @Test
    public void should_return_1_cell_given_1_as_height_and_1_as_width_of_the_grid() {
        grid = new Grid(1, 1);

        assertThat(grid.initializeCells().size()).isEqualTo(1);
    }

    @Test
    public void should_return_two_cells_given_a_grid_of_1_x_2() {
        grid = new Grid(1, 2);

        List<Cell> cells = grid.initializeCells();
        assertThat(cells.size()).isEqualTo(2);
    }

    @Test
    public void should_return_4_cells_given_2_as_height_and_2_as_width_of_the_grid() {
        grid = new Grid(2, 2);
        assertThat(grid.initializeCells().size()).isEqualTo(4);
    }

    @Test
    public void should_return_2_cells_given_1_as_height_and_2_as_width_of_the_grid() {
        grid = new Grid(1, 2);
        assertThat(grid.initializeCells().size()).isEqualTo(2);
    }

    @Test
    public void should_contain_only_living_cells_given_a_grid_of_1_x_2() {
        grid = new Grid(1, 2);
        List<Cell> cells = grid.initializeCells();
        assertThat(cells).allMatch(c -> !c.isAlive());
    }

    @Test
    public void should_return_differents_posY_values_for_the_cells_given_a_grid_of_1x2() {
        grid = new Grid(1, 2);

        List<Cell> cells = grid.initializeCells();

        Cell firstCell = cells.get(0);
        Cell secondCell = cells.get(1);

        assertThat(firstCell.getPosY()).isNotEqualTo(secondCell.getPosY());
    }

    @Test
    public void should_return_differents_posX_values_for_the_cells_given_a_grid_of_2x1() {
        grid = new Grid(2, 1);

        List<Cell> cells = grid.initializeCells();

        Cell firstCell = cells.get(0);
        Cell secondCell = cells.get(1);

        assertThat(firstCell.getPosX()).isNotEqualTo(secondCell.getPosX());
    }

    @Test
    public void should_return_no_neighbors_to_first_cell_when_counting_neighbors_of_a_cell_in_a_1x1_grid() {
        List<Cell> allCells = grid.initializeCells();

        Cell firstCell = allCells.get(0);

        assertThat(grid.countLivingNeighbors(allCells, firstCell)).isEqualTo(0);
    }

    @Test
    public void should_return_only_1_living_neighbor_to_the_right_when_counting_the_living_neighbors_of_the_first_cell_in_a_1x2_grid() {
        grid = new Grid(1, 2);

        List<Cell> allCells = grid.initializeCells();
        Cell firstCell = allCells.get(0);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_living_neighbor_to_the_right_when_counting_neighbors_of_the_living_first_cell_in_a_1x4_grid() {
        grid = new Grid(1, 4);

        List<Cell> allCells = grid.initializeCells();

        Cell firstCell = allCells.get(0);
        allCells.get(0).setState(CellState.ALIVE);
        allCells.get(1).setState(CellState.ALIVE);
        assertThat(grid.countLivingNeighbors(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_living_neighbor_in_the_bottom_when_counting_the_neighbors_of_the_first_cell_in_a_2x1_grid() {
        grid = new Grid(2, 1);

        List<Cell> allCells = grid.initializeCells();

        Cell firstCell = allCells.get(0);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_living_1_neighbor_in_the_bottom_when_counting_the_neighbors_of_the_first_cell_in_a_4x1_grid() {
        grid = new Grid(4, 1);

        List<Cell> allCells = grid.initializeCells();

        Cell firstCell = allCells.get(0);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_first_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.initializeCells();

        Cell firstCell = allCells.get(0);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, firstCell)).isEqualTo(3);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_second_living_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.initializeCells();

        Cell secondCell = allCells.get(1);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, secondCell)).isEqualTo(3);
    }

    @Test
    public void should_return_5_living_neighbors_when_counting_the_neighbors_of_the_second_cell_in_a_3x3_grid() {
        grid = new Grid(3, 3);

        List<Cell> allCells = grid.initializeCells();

        Cell secondCell = allCells.get(1);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, secondCell)).isEqualTo(5);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_third_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.initializeCells();

        Cell secondCell = allCells.get(2);

        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, secondCell)).isEqualTo(3);
    }

    @Test
    public void should_return_5_living_neighbors_when_counting_the_neighbors_of_the_third_cell_in_a_3x4_grid() {
        grid = new Grid(3, 4);

        List<Cell> allCells = grid.initializeCells();
        Cell thirdCell = allCells.get(2);

        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, thirdCell)).isEqualTo(5);
    }

    @Test
    public void should_return_8_neighbors_when_counting_the_neighbors_of_the_fift_cell_in_a_3x4_grid() {
        grid = new Grid(3, 4);

        List<Cell> allCells = grid.initializeCells();

        Cell fifth = allCells.get(5);

        setAllNeighborhoodAsAlive(allCells);


        assertThat(grid.countLivingNeighbors(allCells, fifth)).isEqualTo(8);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_last_cell_in_a_3x4_grid() {
        grid = new Grid(3, 4);

        List<Cell> allCells = grid.initializeCells();

        Cell twelthieth = allCells.get(11);
        setAllNeighborhoodAsAlive(allCells);

        assertThat(grid.countLivingNeighbors(allCells, twelthieth)).isEqualTo(3);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_dead_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.initializeCells();

        allCells.get(0).setState(CellState.DEAD);
        allCells.get(1).setState(CellState.ALIVE);
        allCells.get(2).setState(CellState.ALIVE);
        allCells.get(3).setState(CellState.ALIVE);


        Cell firstCellAsDead = allCells.get(0);
        assertThat(grid.countLivingNeighbors(allCells, firstCellAsDead)).isEqualTo(3);
    }

    @Test
    public void should_mark_the_first_cell_as_live_when_its_state_is_changed_to_ALIVE() {
        grid = new Grid(1, 2);
        Cell firstCell = grid.initializeCells().get(0);
        firstCell.setState(CellState.ALIVE);

        Assertions.assertThat(firstCell.isAlive()).isTrue();
    }

    @Test
    public void should_return_no_neighborhood_to_right_first_dead_cell_when_the_second_cell_is_dead_as_well_in_a_1x2_grid() {
        grid = new Grid(1, 2);
        List<Cell> cells = grid.initializeCells();
        Cell firstCell = cells.get(0);

        grid.setCells(cells);
        grid.printCells();

        assertThat(grid.countLivingNeighbors(cells, firstCell)).isEqualTo(0);

    }

    @Test
    public void should_die_by_underpopulation_when_a_live_cell_has_fewer_than_2_live_neighbors_in_a_2x2_grid() {
        grid = new Grid(2, 2);
        List<Cell> cells = grid.initializeCells();

        cells.get(0).setState(CellState.ALIVE);
        cells.get(1).setState(CellState.ALIVE);

        Grid newGeneration = GameOfLife.computeNextGeneration(2, 2, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isFalse();
    }

    @Test
    public void should_die_by_overcrowding_when_a_live_cell_has_more_than_3_neighbors_in_a_4x4_grid() {
        grid = new Grid(4, 4);
        List<Cell> cells = grid.initializeCells();

        setAllNeighborhoodAsAlive(cells);

        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firthCellOfNewGen = newGeneration.getCells().get(5);

        assertThat(firthCellOfNewGen.isAlive()).isFalse();
    }

    @Test
    public void should_live_on_the_next_generation_when_a_live_cell_has_3_neighbors() {
        grid = new Grid(4, 4);
        List<Cell> cells = grid.initializeCells();

        setAllNeighborhoodAsAlive(cells);

        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    @Test
    public void should_live_on_the_next_generation_when_a_live_cell_has_2_neighbors() {
        grid = new Grid(3, 3);
        List<Cell> cells = grid.initializeCells();

        cells.get(0).setState(CellState.ALIVE);
        cells.get(1).setState(CellState.ALIVE);
        cells.get(3).setState(CellState.ALIVE);

        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    @Test
    public void should_live_on_the_next_generation_when_a_dead_cell_has_exactly_3_neighbors() {
        grid = new Grid(3, 3);
        List<Cell> cells = grid.initializeCells();

        cells.get(0).setState(CellState.DEAD);
        cells.get(1).setState(CellState.ALIVE);
        cells.get(3).setState(CellState.ALIVE);
        cells.get(4).setState(CellState.ALIVE);


        Grid newGeneration = GameOfLife.computeNextGeneration(4, 4, cells);
        Cell firstCellOfNewGen = newGeneration.getCells().get(0);

        assertThat(firstCellOfNewGen.isAlive()).isTrue();
    }

    private void setAllNeighborhoodAsAlive(List<Cell> allCells) {

        allCells.stream().forEach(c -> c.setState(CellState.ALIVE));
    }

}
