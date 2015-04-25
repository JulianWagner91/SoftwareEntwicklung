package edu.hm.hafner.java2.sokoban;

/**
 * Represents the game field of Sokoban.
 *
 * @author Julian Wagner
 */
public class Sokoban {
    private final PointSet treasures = new PointSet();
    private Field[][] level;
    private Point player;

    /**
     * Sets the level. The level consists of an array of lines. Each line is represented by an array of fields.
     *
     * @param level the level
     * @throws IllegalArgumentException
     */
    public void setLevel(final Field[][] level) {
        if (level == null) {
            throw new IllegalArgumentException("Level must not be null!");
        }

        //adfjaödfkajödfkajdöfajkdföakjdföakdjf

        this.level = new Field[level.length][level[0].length];
        int lengthOfLevel = level[0].length;

        for (int i = 0; i < level.length; i++) {
            if (level[i].length != lengthOfLevel) {
                throw new IllegalArgumentException("Length of the lines are not equal!");
            }
            for (int j = 0; j < lengthOfLevel; j++) {
                if (level[i][j] == null) {
                    throw new IllegalArgumentException("Field value must not be null!");
                }
                this.level[i][j] = level[i][j];
            }
        }
    }

    /**
     * Adds a treasure at the specified coordinates. If there is already a treasure at that position, nothing is
     * done.
     *
     * @param treasure the position of the treasure
     * @throws IllegalArgumentException
     */
    public void addTreasure(final Point treasure) {
        if (treasure == null) {
            throw new IllegalArgumentException("Treasure must not be null!");
        }

        treasures.add(treasure);
    }

    /**
     * Removes a treasure from the specified coordinates. If there is no treasure at that position, nothing is
     * done.
     *
     * @param treasure the position of the treasure
     * @throws IllegalArgumentException
     */
    public void removeTreasure(final Point treasure) {
        if (treasure == null) {
            throw new IllegalArgumentException("Treasure must not be null!");
        }
        treasures.remove(treasure);
    }

    /**
     * Validates this level.
     *
     * @throws IllegalArgumentException if the level is not valid
     */
    public void validate() {
        if (level == null) {
            throw new IllegalArgumentException("Level must not be null!");
        }
        checkPlayer();
        checkTreasure();
    }

    /**
     * Returns whether this level has been solved. The level is solved, if each treasure covers a target.
     *
     * @return {@code true} if this level has been solved, {@code false} otherwise
     */
    public boolean isSolved() {
        for (int i = 0; i < treasures.size(); i++) {
            if (getField(treasures.get(i)) != Field.TARGET) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the field at the specified position.
     *
     * @param point the position
     * @return the field at the specified position
     * @throws IllegalArgumentException
     */
    public Field getField(final Point point) {
        if (point == null) {
            throw new IllegalArgumentException("Point must not be null!");
        }
        if (point.getX() >= getWidth() || point.getY() >= getHeight()) {
            throw new IllegalArgumentException(String.format("Point %s is out of bounds!", point.toString()));
        }

        return level[point.getY()][point.getX()];
    }

    /**
     * Returns the player position.
     *
     * @return the player position.
     */
    public Point getPlayer() {
        return player;
    }

    /**
     * Sets the position of the player to the specified coordinates.
     *
     * @param player the new position
     * @throws IllegalArgumentException
     */
    public void setPlayer(final Point player) {
        if (player == null) {
            throw new IllegalArgumentException("Player must not be null!");
        }
        this.player = player;
    }

    /**
     * Returns the treasure positions.
     *
     * @return the treasure positions.
     */
    public PointSet getTreasures() {
        return treasures;
    }

    /**
     * Returns the width of the level.
     *
     * @return the width of the level.
     */
    public int getWidth() {
        return level[0].length;
    }

    /**
     * Returns the height of the level.
     *
     * @return the height of the level.
     */
    public int getHeight() {
        return level.length;
    }

    @Override
    public String toString() {
        String[][] stringLevel = new String[getHeight()][getWidth()];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                stringLevel[i][j] = getValue(level[i][j]);
            }
        }

        stringLevel[player.getY()][player.getX()] = "@";

        for (int i = 0; i < treasures.size(); i++) {
            stringLevel[treasures.get(i).getY()][treasures.get(i).getY()] = "$";
        }

        StringBuilder builder = new StringBuilder();
        for (String[] sndLevel : stringLevel) {
            for (String fieldValue : sndLevel) {
                builder = builder.append(fieldValue);
            }
            builder = builder.append("\n");
        }

        return builder.toString();
    }

    private String getValue(final Field field) {
        if (field == Field.TARGET) {
            return ".";
        } else if (field == Field.WALL) {
            return "#";
        }
        return " ";
    }

    private void checkPlayer() {
        if (player == null) {
            throw new IllegalArgumentException("Player must not be null!");
        }

        if (!objectNotOutOfBounds(player)) {
            throw new IllegalArgumentException(String.format("Player %s is out of bounds!", player.toString()));
        }

        if (isObjectNotInTheField(player)) {
            throw new IllegalArgumentException("Player is not within the field!");
        }
    }

    private void checkTreasure() {
        if (treasures.size() == 0) {
            throw new IllegalArgumentException("At least one treasure must be set!");
        }
        int countTargets = countTargets();


        int size = treasures.size();
        if (size != countTargets) {
            throw new IllegalArgumentException(
                    String.format("Number of treasures (%d) and targets (%d) have to be equal!", size, countTargets));
        }

        for (int i = 0; i < size; i++) {
            Point treasure = treasures.get(i);

            if (!objectNotOutOfBounds(treasure)) {
                throw new IllegalArgumentException(String.format("Treasure %s is out of bounds!", treasure.toString()));
            }

            if (isObjectNotInTheField(treasure)) {
                throw new IllegalArgumentException(String.format("Treasure %s is not within the field!", treasure.toString()));
            }
        }

        if (treasures.contains(player)) {
            throw new IllegalArgumentException(String.format("Player and Treasure must not on the same field %s!", player.toString()));
        }
    }

    private boolean isObjectNotInTheField(final Point point) {
        return getField(point) == Field.BACKGROUND || getField(point) == Field.WALL;
    }

    private boolean objectNotOutOfBounds(final Point point) {
        return point.getX() < getWidth() && point.getY() < getHeight();
    }

    private int countTargets() {
        int counter = 0;
        for (Field[] line : level) {
            for (Field field : line) {
                if (field == Field.TARGET) {
                    counter++;
                }
            }
        }
        return counter;
    }
}