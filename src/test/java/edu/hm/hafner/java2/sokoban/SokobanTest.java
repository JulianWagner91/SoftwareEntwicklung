package edu.hm.hafner.java2.sokoban;

import org.junit.Test;

import static edu.hm.hafner.java2.sokoban.Field.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by Julian on 14.04.2015.
 * This class should test the class {@code Sokoban}.
 */
public class SokobanTest {
    private static final Field[][] LEVEL = new Field[][]{
            {BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND},
            {BACKGROUND, WALL, WALL, WALL, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
            {BACKGROUND, WALL, FLOOR, TARGET, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
            {BACKGROUND, WALL, FLOOR, FLOOR, WALL, WALL, WALL, BACKGROUND},
            {BACKGROUND, WALL, TARGET, FLOOR, FLOOR, FLOOR, WALL, BACKGROUND},
            {BACKGROUND, WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, BACKGROUND},
            {BACKGROUND, WALL, FLOOR, FLOOR, WALL, WALL, WALL, BACKGROUND},
            {BACKGROUND, WALL, WALL, WALL, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
            {BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND}
    };

    /**
     * Checks if function setLevel works as expected
     */
    @Test
    public void shouldCheckSetLevel() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);

        assertThat(sokoban.getField(new Point(0, 0))).isEqualTo(BACKGROUND);
        assertThat(sokoban.getField(new Point(1, 1))).isEqualTo(WALL);
        assertThat(sokoban.getField(new Point(2, 2))).isEqualTo(FLOOR);
        assertThat(sokoban.getField(new Point(3, 2))).isEqualTo(TARGET);
        assertThat(sokoban.getHeight()).isEqualTo(9);
        assertThat(sokoban.getWidth()).isEqualTo(8);
    }

    /**
     * Checks that Level can not be manipulated out of the class
     */
    @Test
    public void shouldNotBeAbleToManipulateLevel() {
        Field[][] level = new Field[][]{
                {BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND},
                {BACKGROUND, WALL, WALL, WALL, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
                {BACKGROUND, WALL, FLOOR, TARGET, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
                {BACKGROUND, WALL, FLOOR, FLOOR, WALL, WALL, WALL, BACKGROUND},
                {BACKGROUND, WALL, TARGET, FLOOR, FLOOR, FLOOR, WALL, BACKGROUND},
                {BACKGROUND, WALL, FLOOR, FLOOR, FLOOR, FLOOR, WALL, BACKGROUND},
                {BACKGROUND, WALL, FLOOR, FLOOR, WALL, WALL, WALL, BACKGROUND},
                {BACKGROUND, WALL, WALL, WALL, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
                {BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND}
        };

        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(level);
        sokoban.addTreasure(new Point(4, 5));
        sokoban.addTreasure(new Point(3, 3));
        sokoban.setPlayer(new Point(3, 4));
        level[0][0] = WALL;

        sokoban.validate();

        assertThat(sokoban.toString()).isEqualTo("        \n" +
                " ####   \n" +
                " # .#   \n" +
                " # $### \n" +
                " #.@  # \n" +
                " #   $# \n" +
                " #  ### \n" +
                " ####   \n" +
                "        \n");
    }

    /**
     * Checks if all exceptions would be thrown as expected
     */
    @Test
    public void shouldThrowExceptionsAtSetLevel() {
        Sokoban sokoban = new Sokoban();

        assertThatThrownBy(() -> sokoban.setLevel(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            Field[][] level = {
                    {BACKGROUND, BACKGROUND, null},
                    {WALL, BACKGROUND, null}
            };
            sokoban.setLevel(level);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            Field[][] level = new Field[][]{
                    {BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND},
                    {BACKGROUND, WALL, WALL, WALL, BACKGROUND, BACKGROUND, BACKGROUND},
                    {BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND, BACKGROUND},
            };
            sokoban.setLevel(level);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks if the method setPlayer sets the player on the right field.
     */
    @Test
    public void shouldCheckPlayer() {
        Sokoban sokoban = new Sokoban();
        sokoban.setPlayer(new Point(2, 3));
        assertThat(sokoban.getPlayer().toString()).isEqualTo("(2, 3)");
    }

    /**
     * Checks if method setPlayer throws IllegalArgumentException if parameter is null.
     */
    @Test
    public void shouldThrowExceptionAtSetPlayer() {
        Sokoban sokoban = new Sokoban();
        assertThatThrownBy(() -> sokoban.setPlayer(null)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks if method addTreasure throws IllegalArgumentException if parameter is null.
     */
    @Test
    public void shouldThrowExceptionAtTreasureFunctions() {
        Sokoban sokoban = new Sokoban();
        assertThatThrownBy(() -> sokoban.addTreasure(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sokoban.removeTreasure(null)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks if method add- and removeTreasure works as expected
     */
    @Test
    public void shouldCheckTreasureFunctions() {
        Sokoban sokoban = new Sokoban();
        sokoban.addTreasure(new Point(3, 5));
        sokoban.addTreasure(new Point(4, 5));
        sokoban.addTreasure(new Point(7, 2));
        assertThat(sokoban.getTreasures().toString()).isEqualTo("[(3, 5), (4, 5), (7, 2)]");

        sokoban.removeTreasure(new Point(3, 5));
        sokoban.removeTreasure(new Point(8, 5));
        assertThat(sokoban.getTreasures().toString()).isEqualTo("[(4, 5), (7, 2)]");

        sokoban.removeTreasure(new Point(7, 2));
        assertThat(sokoban.getTreasures().toString()).isEqualTo("[(4, 5)]");
    }

    /**
     * Checks if the method isSolved return {@code true} if the level is solved.
     */
    @Test
    public void shouldLevelIsSolved() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);
        sokoban.setPlayer(new Point(2, 2));
        sokoban.addTreasure(new Point(3, 2));
        sokoban.addTreasure(new Point(2, 4));

        assertThat(sokoban.isSolved());
    }

    /**
     * Checks if the method isSolved return {@code false} if the level is not solved.
     */
    @Test
    public void shouldLevelIsNotSolved() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);
        sokoban.setPlayer(new Point(2, 2));
        sokoban.addTreasure(new Point(3, 2));
        sokoban.addTreasure(new Point(3, 3));
        sokoban.validate();

        assertThat(sokoban.isSolved()).isFalse();
    }

    /**
     * Checks if method getField throws IllegalArgumentException if parameter is null.
     */
    @Test
    public void shouldThrowExceptionAtGetField() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);

        assertThatThrownBy(() -> sokoban.getField(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sokoban.getField(new Point(1, 10))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sokoban.getField(new Point(10, 1))).isInstanceOf(IllegalArgumentException.class);
    }


    /**
     * Checks if method validate works as expected.
     */
    @Test
    public void shouldValidateSuccessFul() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);
        sokoban.addTreasure(new Point(4, 5));
        sokoban.addTreasure(new Point(3, 3));
        sokoban.setPlayer(new Point(3, 4));
        sokoban.validate();
    }

    /**
     * Checks if instance variables are having valid values
     */
    @Test
    public void shouldExceptionsAtValidateIfInstanceVariablesNotSet() {
        //Level is not set
        assertThatThrownBy(() -> {
            Sokoban sokoban = new Sokoban();
            sokoban.setPlayer(new Point(4, 4));
            sokoban.addTreasure(new Point(4, 5));
            sokoban.addTreasure(new Point(3, 3));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        //Player is not set
        assertThatThrownBy(() -> {
            Sokoban sokoban = new Sokoban();
            sokoban.setLevel(LEVEL);
            sokoban.addTreasure(new Point(4, 5));
            sokoban.addTreasure(new Point(3, 3));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        //Treasures are not set
        assertThatThrownBy(() -> {
            Sokoban sokoban = new Sokoban();
            sokoban.setLevel(LEVEL);
            sokoban.setPlayer(new Point(2, 2));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks if treasures are having a valid position
     */
    @Test
    public void shouldThrowExceptionsIfTreasureHasWrongPosition() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);
        sokoban.setPlayer(new Point(4, 5));
        sokoban.addTreasure(new Point(3, 3));

        //Check player is out of field
        assertThatThrownBy(() -> {
            sokoban.addTreasure(new Point(2, 1));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(2, 1));
            sokoban.addTreasure(new Point(1, 2));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(1, 2));
            sokoban.addTreasure(new Point(1, 6));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(1, 6));
            sokoban.addTreasure(new Point(2, 7));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(2, 7));
            sokoban.addTreasure(new Point(6, 6));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        //Out of bounds
        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(6, 6));
            sokoban.addTreasure(new Point(0, 8));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(0, 8));
            sokoban.addTreasure(new Point(9, 8));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.removeTreasure(new Point(9, 8));
            sokoban.addTreasure(new Point(7, 9));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks if player has a valid position
     */
    @Test
    public void shouldThrowExceptionsIfPlayerHasWrongPosition() {
        Sokoban sokoban = new Sokoban();
        sokoban.setLevel(LEVEL);
        sokoban.addTreasure(new Point(4, 5));
        sokoban.addTreasure(new Point(3, 3));

        //Check player is out of field
        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(2, 1));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(1, 2));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(1, 6));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(2, 7));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(6, 6));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        //Out of bounds
        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(0, 8));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(9, 8));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            sokoban.setPlayer(new Point(7, 9));
            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);
    }


    /**
     * Checks if treasures and targets has the same amount of occurrences
     */
    @Test
    public void shouldThrowExceptionIfTreasureHasWrongSize() {
        assertThatThrownBy(() -> {
            Sokoban sokoban = new Sokoban();
            sokoban.setLevel(LEVEL);
            sokoban.setPlayer(new Point(2, 2));
            sokoban.addTreasure(new Point(4, 5));
            sokoban.addTreasure(new Point(3, 3));
            sokoban.addTreasure(new Point(2, 3));

            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Checks if player and treasures are not on the same field
     */
    @Test
    public void shouldThrowExceptionIfPlayerIsOnTreasure() {
        assertThatThrownBy(() -> {
            Sokoban sokoban = new Sokoban();
            sokoban.setLevel(LEVEL);
            sokoban.setPlayer(new Point(2, 2));
            sokoban.addTreasure(new Point(2, 2));
            sokoban.addTreasure(new Point(3, 3));

            sokoban.validate();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

//adfjaödfkajödfkajdöfajkdföakjdföakdjf