package edu.hm.hafner.java2.sokoban;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * Tests the class {@link Point}.
 *
 * @author Julian Wagner
 */
public class PointTest {
    /**
     * Checks the creation of the instance
     */
    @Test
    public void shouldCheckConstructor() {
        // Given
        Point point = new Point(1, 5);

        // Then
        assertThat(point.getX()).isEqualTo(1);
        assertThat(point.getY()).isEqualTo(5);
        assertThat(point.toString()).isEqualTo("(1, 5)");
    }

    /**
     * Checks that point 1 is equal to point 2
     */
    @Test
    public void shouldBeEqual() {
        assertThat(new Point(5, 2).isEqualTo(new Point(5, 2))).isTrue();
    }

    /**
     * Checks that point 1 is not equal to point 2 or null
     */
    @Test
    public void shouldNotBeEqual() {
        assertThat(new Point(5, 3).isEqualTo(new Point(3, 5))).isFalse();
        assertThat(new Point(5, 2).isEqualTo(null)).isFalse();
    }

    /**
     * Checks that the class is immutable
     * Checks all movements
     */
    @Test
    public void shouldMovementsBeImmutable() {
        //Given
        Point orgPoint = new Point(5, 7);

        //Then
        assertThat(orgPoint.moveLeft().isEqualTo(new Point(4, 7))).isTrue();
        assertThat(orgPoint.moveRight().isEqualTo(new Point(6, 7))).isTrue();
        assertThat(orgPoint.moveUp().isEqualTo(new Point(5, 6))).isTrue();
        assertThat(orgPoint.moveDown().isEqualTo(new Point(5, 8))).isTrue();

        assertThat(orgPoint.toString()).isEqualTo("(5, 7)");
    }

    /**
    * Checks if method throws IllegalArgumentExceptions
    */
    @Test
    public void shouldThrowExceptionIfCoordinationsAreNegative() {
        assertThatThrownBy(() -> {
            new Point(-1, 2);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Point (-1, 2) is out of bounds!");

        assertThatThrownBy(() -> {
            new Point(1, -2);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("Point (1, -2) is out of bounds!");

    }
}