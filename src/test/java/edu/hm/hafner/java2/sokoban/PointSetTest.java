package edu.hm.hafner.java2.sokoban;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests the class {@link PointSet}.
 *
 * @author Julian Wagner
 */
public class PointSetTest {
    /**
     * Checks that new standard constructor creates an array with the lenght of 0
     * Checks that the copy constructor works as expected
     */
    @Test
    public void shouldCheckConstructors() {
        PointSet points = new PointSet();
        points.add(new Point(2, 3));
        points.add(new Point(1, 3));

        assertThat(new PointSet().size()).isEqualTo(0);
        assertThat(new PointSet(points).size()).isEqualTo(2);
        assertThat(points.toString()).isEqualTo("[(2, 3), (1, 3)]");
    }

    /**
     * Checks that you can add a new point
     */
    @Test
    public void shouldPointWasAdded() {
        PointSet points = new PointSet();




        
        assertThat(points.add(new Point(5, 4))).isTrue();
        assertThat(points.add(new Point(2, 4))).isTrue();
        assertThat(points.get(0).isEqualTo(new Point(5, 4))).isTrue();
        assertThat(points.get(1).isEqualTo(new Point(2, 4))).isTrue();
        assertThat(points.size()).isEqualTo(2);
        assertThat(points.toString()).isEqualTo("[(5, 4), (2, 4)]");
    }

    /**
     * Checks that you can not add a duplicate point
     */
    @Test
    public void shouldPointWasNotAdded() {
        //Given
        PointSet points = new PointSet();
        points.add(new Point(5, 4));

        //Then
        assertThat(points.add(new Point(5, 4))).isFalse();
    }

    /**
     * Checks that the point was removed
     */
    @Test
    public void shouldRemovePoint() {
        //Given
        PointSet points = new PointSet();
        points.add(new Point(5, 4));
        points.add(new Point(4, 4));
        points.add(new Point(3, 4));

        //Then
        assertThat(points.remove(new Point(5, 4))).isTrue();
        assertThat(points.contains(new Point(5, 4))).isFalse();
        assertThat(points.size()).isEqualTo(2);
    }

    /**
     * Checks that you can not remove a point which is not in the array
     */
    @Test
    public void shouldNotRemovePoint() {
        assertThat(new PointSet().remove(new Point(5, 4))).isFalse();
    }

    /**
     * Checks that the point is in the array
     */
    @Test
    public void shouldContainPoint() {
        //Given
        PointSet points = new PointSet();
        points.add(new Point(5, 4));
        points.add(new Point(2, 4));
        points.add(new Point(3, 4));

        //Then
        assertThat(points.contains(new Point(5, 4))).isTrue();
    }

    /**
     * Checks that the point is not in the array
     */
    @Test
    public void shouldNotContainPoint() {
        assertThat(new PointSet().contains(new Point(5, 4))).isFalse();
    }

    /**
     * Checks the size of the array
     */
    @Test
    public void shouldCheckSize() {
        PointSet points = new PointSet();

        //When
        points.add(new Point(5, 4));
        points.add(new Point(3, 4));

        //Then
        assertThat(points.size()).isEqualTo(2);
        assertThat(new PointSet().size()).isEqualTo(0);
    }

    /**
     * Checks that you get the first point of an array
     */
    @Test
    public void shouldGetSpecialPoint() {
        //Given
        PointSet points = new PointSet();
        Point firstPoint = new Point(1, 3);
        Point lastPoint = new Point(2, 3);

        points.add(firstPoint);
        points.add(new Point(1, 2));
        points.add(lastPoint);

        //When
        Point getFirstPoint = points.get(0);
        Point getLastPoint = points.get(2);

        //Then
        assertThat(firstPoint.isEqualTo(getFirstPoint)).isTrue();
        assertThat(lastPoint.isEqualTo(getLastPoint)).isTrue();
    }


    /**
     * Checks that copy constructor throws NullPointException
     */
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionAtCopyConstructor() {
        new PointSet(null);
    }

    /**
     * Should check that the add method throws NullPointerException
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtAdd() {
        PointSet pointSet = new PointSet();
        pointSet.add(null);
    }

    /**
     * Should check that the remove method throws NullPointerException
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtRemove() {
        PointSet pointSet = new PointSet();
        pointSet.remove(null);
    }

    /**
     * Should check that the remove method throws NullPointerException
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtContains() {
        PointSet pointSet = new PointSet();
        pointSet.contains(null);
    }

    /**
     * Should check that the remove method throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtGetUpper() {
        PointSet pointSet = new PointSet();
        pointSet.add(new Point(1, 2));
        pointSet.add(new Point(2, 2));
        pointSet.add(new Point(3, 2));
        pointSet.get(3);

    }

    /**
     * Should check that the remove method throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtGetLower() {
        PointSet pointSet = new PointSet();
        pointSet.get(-1);
    }
}


