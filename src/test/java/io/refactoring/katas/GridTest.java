package io.refactoring.katas;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.refactoring.katas.Grid.startGame;
import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {

    private Grid grid;

    @Before
    public void setUp() {
        grid = startGame(1, 1);
    }

    @Test
    public void should_create_a_grid_given_as_the_height_and_a_width_as_parameters() {
        grid = startGame(4, 8);

        assertThat(grid.getHeight()).isEqualTo(4);
        assertThat(grid.getWidth()).isEqualTo(8);
    }

    @Test
    public void should_be_initialized_with_only_dead_cells_when_the_game_starts() {
        grid = startGame(4, 8);

        assertThat(grid.getCells()).isNotEmpty();
        assertThat(grid.getCells()).allMatch(c -> !c.isAlive());
    }


    @Test
    public void should_contain_one_cell_given_1x1_grid() {
        assertThat(grid.getCells()).containsExactly(new Cell(0, 0));
    }

    @Test
    public void should_return_1_cell_given_1_as_height_and_1_as_width_of_the_grid() {
        assertThat(grid.getTotalCells()).isEqualTo(1);
    }

    @Test
    public void should_return_two_cells_given_a_1x2_grid() {
        initializeAGridOf1x2();

        assertThat(grid.getTotalCells()).isEqualTo(2);
    }

    @Test
    public void should_return_4_cells_given_2_as_height_and_2_as_width_of_the_grid() {
        initializeAGridOf2x2();

        assertThat(grid.getTotalCells()).isEqualTo(4);
    }

    @Test
    public void should_return_2_cells_given_1_as_height_and_2_as_width_of_the_grid() {
        initializeAGridOf1x2();

        assertThat(grid.getTotalCells()).isEqualTo(2);
    }

    @Test
    public void should_contain_only_living_cells_given_a_grid_of_1_x_2() {
        initializeAGridOf1x2();

        assertThat(grid.getCells()).allMatch(c -> !c.isAlive());
    }

    @Test
    public void should_return_different_posY_values_for_the_cells_given_a_grid_of_1x2() {
        initializeAGridOf1x2();

        Cell firstCell = grid.getCellAtPosition(0);
        Cell secondCell = grid.getCellAtPosition(1);

        assertThat(firstCell.getPositionY()).isNotEqualTo(secondCell.getPositionY());
    }

    @Test
    public void should_return_different_posX_values_for_the_cells_given_a_grid_of_2x1() {
        initializeAGridOf2x1();

        Cell firstCell = grid.getCellAtPosition(0);
        Cell secondCell = grid.getCellAtPosition(1);

        assertThat(firstCell.getPositionX()).isNotEqualTo(secondCell.getPositionX());
    }

    @Test
    public void should_return_no_neighbors_to_first_cell_when_counting_neighbors_of_a_cell_in_a_1x1_grid() {

        Cell firstCell = grid.getCellAtPosition(0);

        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(0);
    }

    @Test
    public void should_return_only_1_living_neighbor_to_the_right_when_counting_the_living_neighbors_of_the_first_cell_in_a_1x2_grid() {
        initializeAGridOf1x2();

        Cell firstCell = grid.getCellAtPosition(0);

        grid.setAllNeighborhoodAsAlive();

        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_living_neighbor_to_the_right_when_counting_neighbors_of_the_living_first_cell_in_a_1x4_grid() {
        grid = startGame(1, 4);

        Cell firstCell = grid.getCellAtPosition(0);

        grid.setCellsAsAlive(0,1);

        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_1_living_neighbor_in_the_bottom_when_counting_the_neighbors_of_the_first_cell_in_a_2x1_grid() {
        initializeAGridOf2x1();

        grid.setAllNeighborhoodAsAlive();

        Cell firstCell = grid.getCellAtPosition(0);
        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_only_living_1_neighbor_in_the_bottom_when_counting_the_neighbors_of_the_first_cell_in_a_4x1_grid() {
        grid = startGame(4, 1);
        grid.setAllNeighborhoodAsAlive();

        Cell firstCell = grid.getCellAtPosition(0);
        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(1);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_first_cell_in_a_2x2_grid() {
        grid = startGame(2, 3);

        grid.setAllNeighborhoodAsAlive();

        Cell firstCell = grid.getCellAtPosition(0);
        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(3);
    }


    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_second_living_cell_in_a_2x2_grid() {
        initializeAGridOf2x2();

        grid.setAllNeighborhoodAsAlive();

        List<Cell> allCells = grid.getCells();
        assertThat(grid.countLivingNeighbors(allCells, grid.getCellAtPosition(1))).isEqualTo(3);
    }

    @Test
    public void should_return_5_living_neighbors_when_counting_the_neighbors_of_the_second_cell_in_a_3x3_grid() {
        grid = startGame(3, 3);

        Cell secondCell = grid.getCellAtPosition(1);
        grid.setAllNeighborhoodAsAlive();

        List<Cell> allCells = grid.getCells();
        assertThat(grid.countLivingNeighbors(allCells, secondCell)).isEqualTo(5);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_third_cell_in_a_2x2_grid() {
        initializeAGridOf2x2();

        grid.setAllNeighborhoodAsAlive();

        List<Cell> allCells = grid.getCells();
        assertThat(grid.countLivingNeighbors(allCells, grid.getCellAtPosition(2))).isEqualTo(3);
    }

    @Test
    public void should_return_5_living_neighbors_when_counting_the_neighbors_of_the_third_cell_in_a_3x4_grid() {
        initializeAGridOf3x4();

        grid.setAllNeighborhoodAsAlive();

        List<Cell> allCells = grid.getCells();
        assertThat(grid.countLivingNeighbors(allCells, grid.getCellAtPosition(2))).isEqualTo(5);
    }

    @Test
    public void should_return_8_neighbors_when_counting_the_neighbors_of_the_fift_cell_in_a_3x4_grid() {
        initializeAGridOf3x4();

        Cell fifth = grid.getCellAtPosition(5);

        grid.setAllNeighborhoodAsAlive();

        List<Cell> allCells = grid.getCells();
        assertThat(grid.countLivingNeighbors(allCells, fifth)).isEqualTo(8);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_the_last_cell_in_a_3x4_grid() {
        initializeAGridOf3x4();

        grid.setAllNeighborhoodAsAlive();

        List<Cell> allCells = grid.getCells();
        assertThat(grid.countLivingNeighbors(allCells, grid.getCellAtPosition(11))).isEqualTo(3);
    }

    @Test
    public void should_return_3_living_neighbors_when_counting_the_neighbors_of_dead_cell_in_a_2x2_grid() {
        initializeAGridOf2x2();


        grid.setAsDead(0);
        grid.setCellsAsAlive(1,2,3);

        Cell firstCellAsDead = grid.getCellAtPosition(0);
        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCellAsDead)).isEqualTo(3);
    }

    @Test
    public void should_mark_the_first_cell_as_live_when_its_state_is_changed_to_ALIVE() {
        initializeAGridOf1x2();

        grid.setCellsAsAlive(0);
        assertThat(grid.getCellAtPosition(0).isAlive()).isTrue();
    }

    @Test
    public void should_return_no_neighborhood_to_right_of_dead_cell_when_the_second_cell_is_dead_as_well_in_a_1x2_grid() {
        initializeAGridOf1x2();

        Cell firstCell = grid.getCellAtPosition(0);

        assertThat(grid.countLivingNeighbors(grid.getCells(), firstCell)).isEqualTo(0);

    }

    private void initializeAGridOf3x4() {
        grid = startGame(3, 4);
    }

    private void initializeAGridOf2x1() {
        grid = startGame(2, 1);
    }
    private void initializeAGridOf2x2() {
        grid = startGame(2, 2);
    }

    private void initializeAGridOf1x2() {
        grid = startGame(1, 2);
    }
}
