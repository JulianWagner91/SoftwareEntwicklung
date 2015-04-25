package edu.hm.hafner.java2.sokoban;

import javax.annotation.concurrent.Immutable;

/**
 * A point represents a location in {@code (x,y)} coordinate space,
 * specified in integer precision. Instances of this class are immutable.
 *
 * @author Julian Wagner
 */
@Immutable
public class Point {
    private final int x;
    private final int y;
    private final String display;

    /**
     * Create a new instance of Point
     *
     * @param x  Value for the x Coordination.
     * @param y Value for the y Coordination.
     * @throws IllegalArgumentException
     */
    public Point(final int x, final int y) {
        display = String.format("(%d, %d)", x, y);

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException(String.format("Point %s is out of bounds!", display));
        }

        this.x = x;
        this.y = y;
    }

    //adfjaödfkajödfkajdöfajkdföakjdföakdjf

    /**
     * Returns the x Coordination
     *
     * @return Int value of x Coordination.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y Coordination
     * @return Int value of y Coordination.
     */
    public int getY() {
        return y;
    }

    /**
     * Checks is the instance is equal to the other point
     * @param point other point to compare with instance variable
     * @return {@code false} if point is null or not equal, {@code true} otherwise
     */
    public boolean isEqualTo(final Point point) {
        return point != null && point.getX() == x && point.getY() == y;
    }

    /**
     * Create a new instance of the point right from the current instance.
     *
     * @return Point
     */
    public Point moveRight() {
        return new Point(x + 1, y);
    }

    /**
     * Create a new instance of the point left from the current instance.
     *
     * @return Point
     */
    public Point moveLeft() {
        return new Point(x - 1, y);
    }

    /**
     * Create a new instance of the point above the current instance.
     *
     * @return Point
     */
    public Point moveUp() {
        return new Point(x, y - 1);
    }

    /**
     * Create a new instance of the point under the current instance.
     *
     * @return Point
     */
    public Point moveDown() {
        return new Point(x, y + 1);
    }

    @Override
    public String toString() {
        return display;
    }

}
