package io.refactoring.katas;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LifeAccordingToConwayShould {

    private Universe universe;

    @Before
    public void initialize_common_variables() {
        universe = Universe.startGame(4, 6);
    }

    @Test
    public void should_create_universe_when_given_two_numbers_as_dimensions() {
        assertThat(universe).isNotNull();
    }

    @Test
    public void should_have_height_value_equal_to_first_parameter_when_creating_the_universe() {
        assertThat(universe.getWidth()).isEqualTo(4);
    }

    @Test
    public void should_have_a_width_value_equal_to_second_parameter_when_creating_the_universe() {
        assertThat(universe.getHeight()).isEqualTo(6);
    }

    @Test
    public void should_contain_a_living_cell_when_given_a_finite_universe() {
       Cell cell = universe.initializeLivingCellAtPosition(0, 0);
        assertThat(cell).isNotNull();
    }

    @Test
    public void should_initialize_a_living_cell_with_positionX_as_first_parameter_given_a_finite_universe() {
        Cell cell = universe.initializeLivingCellAtPosition(2, 0);
        assertThat(cell.getPositionX()).isEqualTo(2);
    }

    @Test
    public void should_initialize_a_living_cell_with_positionY_as_second_parameter_given_a_finite_universe() {
        Cell cell = universe.initializeLivingCellAtPosition(2,5);
        assertThat(cell.getPositionY()).isEqualTo(5);
    }

    @Test
    public void should_mark_the_living_cell_as_alive_when_creating_the_universe() {
        Cell cell = universe.initializeLivingCellAtPosition(2,5);
        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void should_return_null_when_a_cell_positionX_is_off_the_universe() {
        Cell cell = universe.initializeLivingCellAtPosition(10,5);
        assertThat(cell).isNull();
    }

    @Test
    public void should_return_null_when_a_cell_positionY_is_off_the_universe() {
        Cell cell = universe.initializeLivingCellAtPosition(2,10);
        assertThat(cell).isNull();
    }

}
