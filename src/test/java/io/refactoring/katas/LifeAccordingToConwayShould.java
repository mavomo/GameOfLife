package io.refactoring.katas;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LifeAccordingToConwayShould {

    private Universe universe;

    @Before
    public void initialize_common_variables() throws Exception {
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
    

}
