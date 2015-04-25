package edu.hm.hafner.java2.sokoban;

import java.util.Arrays;

/**
 * A collection of points that contains no duplicates. More formally, a {@link PointSet} contains no pair of elements
 * {@code p1} and {@code p2} such that {@code p1.isEqualTo(p2)}. The order of the added elements is preserved.
 *
 * @author Julian Wagner
 */
public class PointSet {
    private Point[] points;

    /**
     * Creates a new instance of PointSet.
     */
    public PointSet() {
        points = new Point[0];
    }

    /**
     * Creates a new instance of PointSet.
     * Copies the field points from other instance.
     *
     * @param other PointSet to copy from
     * @throws NullPointerException
     */
    public PointSet(final PointSet other) {
        this.points = Arrays.copyOf(other.points, other.points.length);
    }

    /**
     * Adds a point to the field points.
     *
     * @param other {@link Point} to add
     * @return {@code false} if point is already in points and {@code true} if add was successful.
     * @throws NullPointerException
     */
    public boolean add(final Point other) {
        if (contains(other)) {
            return false;
        }

        points = Arrays.copyOf(points, points.length + 1);
        points[points.length - 1] = other;
        return true;
    }

    /**
     * Removes a point from the field points.
     *
     * @param point Point to remove from the array
     * @return {@code false} if point is not in points and {@code true} if remove was successful.
     * @throws IllegalArgumentException
     */
    public boolean remove(final Point point) {
        if (!contains(point)) {
            return false;
        }

        Point[] remindPoints = new Point[points.length - 1];
        int counter = 0;

        for (Point value : points) {
            if (!value.isEqualTo(point)) {
                remindPoints[counter] = value;
                counter++;
            }
        }
        points = remindPoints;
        return true;
    }

    /**
     * Checks if a point is in points.
     *
     * @param point Point to check if is in points
     * @return {@code false} if point is not in points and {@code true} otherwise.
     * @throws NullPointerException
     */
    public boolean contains(final Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Other point must not be null!");
        }

        for (Point value : points) {
            if (value.isEqualTo(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the size of the array
     *
     * @return length of the array
     */
    public int size() {
        return points.length;
    }

    /**
     * Returns a point from the array.
     *
     * @param i the Index to return
     * @throws IllegalArgumentException
     */
    public Point get(final int i) {
        if (!(0 <= i && i < size())) {
            throw new IllegalArgumentException(String.format("Index has to be between %d and %d", 0, size()));
        }
        return points[i];
    }

    @Override
    public String toString() {
        return Arrays.toString(points);
    }
}
