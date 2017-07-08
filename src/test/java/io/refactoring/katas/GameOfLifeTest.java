package io.refactoring.katas;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
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
    public void should_contain_one_cell_given_a_grid_of_1_x_1() {
        assertThat(grid.getCells()).containsExactly(new Cell(0, 0));
    }

    @Test
    public void should_return_1_cell_given_1_as_height_and_1_as_width_of_the_grid() {
        grid = new Grid(1, 1);

        assertThat(grid.getCells().size()).isEqualTo(1);
    }

    @Test
    public void should_return_two_cells_given_a_grid_of_1_x_2() {
        grid = new Grid(1, 2);

        List<Cell> cells = grid.getCells();
        assertThat(cells.size()).isEqualTo(2);
    }

    @Test
    public void should_return_4_cells_given_2_as_height_and_2_as_width_of_the_grid() {
        grid = new Grid(2, 2);
        assertThat(grid.getCells().size()).isEqualTo(4);
    }

    @Test
    public void should_return_2_cells_given_1_as_height_and_2_as_width_of_the_grid() {
        grid = new Grid(1, 2);
        assertThat(grid.getCells().size()).isEqualTo(2);
    }

    @Test
    public void should_contain_only_living_cells_given_a_grid_of_1_x_2() {
        grid = new Grid(1, 2);
        List<Cell> cells = grid.getCells();
        assertThat(cells).allMatch(c -> c.isAlive());
    }

    @Test
    public void should_return_differents_posY_values_for_the_cells_given_a_grid_of_1x2() {
        grid = new Grid(1, 2);

        List<Cell> cells = grid.getCells();

        Cell firstCell = cells.get(0);
        Cell secondCell = cells.get(1);

        assertThat(firstCell.getPosY()).isNotEqualTo(secondCell.getPosY());
    }

    @Test
    public void should_return_differents_posX_values_for_the_cells_given_a_grid_of_2x1() {
        grid = new Grid(2, 1);

        List<Cell> cells = grid.getCells();

        Cell firstCell = cells.get(0);
        Cell secondCell = cells.get(1);

        assertThat(firstCell.getPosX()).isNotEqualTo(secondCell.getPosX());
    }

    @Test
    public void should_return_no_neighbours_to_first_cell_when_counting_neighbours_of_a_cell_in_a_1x1_grid() {
        List<Cell> allCells = grid.getCells();

        Cell firstCell = allCells.get(0);

        assertThat(grid.countNeighbourhood(allCells, firstCell)).isEqualTo(0);
    }

    @Test
    public void should_return_only_1_living_neighbour_to_the_right_when_counting_the_neighbours_of_the_first_cell_in_a_1x2_grid() {
        grid = new Grid(1, 2);

        List<Cell> allCells = grid.getCells();
        Cell firstCell = allCells.get(0);
        assertThat(grid.countNeighbourhood(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_neighbour_to_the_right_when_counting_neighbours_of_the_first_cell_in_a_1x4_grid() {
        grid = new Grid(1, 4);

        List<Cell> allCells = grid.getCells();

        Cell firstCell = allCells.get(0);

        assertThat(grid.countNeighbourhood(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_neighbour_in_the_bottom_when_counting_the_neighbours_of_the_first_cell_in_a_2x1_grid() {
        grid = new Grid(2, 1);

        List<Cell> allCells = grid.getCells();

        Cell firstCell = allCells.get(0);

        assertThat(grid.countNeighbourhood(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_neighbour_in_the_bottom_when_counting_the_neighbours_of_the_first_cell_in_a_4x1_grid() {
        grid = new Grid(4, 1);

        List<Cell> allCells = grid.getCells();

        Cell firstCell = allCells.get(0);

        assertThat(grid.countNeighbourhood(allCells, firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_3_neighbours_when_counting_the_neighbours_of_the_first_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.getCells();

        Cell firstCell = allCells.get(0);

        assertThat(grid.countNeighbourhood(allCells, firstCell)).isEqualTo(3);
    }

    @Test
    public void should_return_3_neighbours_when_counting_the_neighbours_of_the_second_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.getCells();

        Cell secondCell = allCells.get(1);

        assertThat(grid.countNeighbourhood(allCells, secondCell)).isEqualTo(3);
    }

    @Test
    public void should_return_5_neighbours_when_counting_the_neighbours_of_the_second_cell_in_a_3x3_grid() {
        grid = new Grid(3, 3);

        List<Cell> allCells = grid.getCells();

        Cell secondCell = allCells.get(1);

        assertThat(grid.countNeighbourhood(allCells, secondCell)).isEqualTo(5);
    }

    @Test
    public void should_return_3_neighbours_when_counting_the_neighbours_of_the_third_cell_in_a_2x2_grid() {
        grid = new Grid(2, 2);

        List<Cell> allCells = grid.getCells();

        Cell secondCell = allCells.get(2);

        assertThat(grid.countNeighbourhood(allCells, secondCell)).isEqualTo(3);
    }

    @Test
    public void should_return_5_neighbours_when_counting_the_neighbours_of_the_third_cell_in_a_3x4_grid() {
        grid = new Grid(3, 4);

        List<Cell> allCells = grid.getCells();

        Cell thirdCell = allCells.get(2);

        assertThat(grid.countNeighbourhood(allCells, thirdCell)).isEqualTo(5);
    }

    @Test
    public void should_return_8_neighbours_when_counting_the_neighbours_of_the_fift_cell_in_a_3x4_grid() {
        grid = new Grid(3, 4);

        List<Cell> allCells = grid.getCells();

        Cell fifth = allCells.get(5);


        assertThat(grid.countNeighbourhood(allCells, fifth)).isEqualTo(8);
    }

    @Test
    public void should_return_3_neighbours_when_counting_the_neighbours_of_the_last_cell_in_a_3x4_grid() {
        grid = new Grid(3, 4);

        List<Cell> allCells = grid.getCells();

        Cell twelthieth = allCells.get(11);

        assertThat(grid.countNeighbourhood(allCells, twelthieth)).isEqualTo(3);
    }

    @Test
    public void should_mark_the_first_cell_as_alive_when_its_state_is_changed_to_ALIVE() {
        grid = new Grid(1, 2);
        Cell firstCell = grid.getCells().get(0);
        firstCell.setState(CellState.ALIVE);

        Assertions.assertThat(firstCell.isAlive()).isTrue();
    }

    @Test
    public void should_return_no_neighboorhood_to_right_first_cell_when_the_second_cell_is_dead_in_a_1x2_grid() {
        //   1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
        grid = new Grid(1, 2);
        List<Cell> cells = grid.getCells();
        Cell firstCell = cells.get(0);
        Cell secondCell = cells.get(1);

        secondCell.setState(CellState.DEAD);
        grid.setCells(cells);
        grid.printCells();


        assertThat(grid.countNeighbourhood(cells, firstCell)).isEqualTo(0);

    }


//
//    private void markAllCellsAsAlive(List<Cell> allCells) {
//        allCells.stream().forEach(c -> c.setState(CellState.ALIVE));
//    }

    //    @Test
//        grid = new Grid(2, 1);
//
//        Cell firstCell = grid.getCells().get(0);
//
//        assertThat(grid.countNeighbourhood(firstCell)).isEqualTo(1);
//    }
//
//    @Test
//    public void should_return_2_neighbour_when_counting_neigbours_of_a_cell_in_a_3x1_grid() {
//        grid = new Grid(3, 1);
//
//        Cell firstCell = grid.getCells().get(0);
//
//        assertThat(grid.countNeighbourhood(firstCell)).isEqualTo(2);
//    }


    //    @Test
//    public void should_contain_only_living_cells_given_a_grid_of_1_x_2() {
//        grid = new Grid(1, 3);
//        List<Cell> cells = grid.getCells();
//        assertThat(cells).allMatch(c -> c.isAlive());
//
//    }


}
