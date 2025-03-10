package com.aleksandar.model;

import com.aleksandar.exception.MissingEndCharacterException;
import com.aleksandar.exception.MissingStartCharacterException;
import com.aleksandar.exception.MultipleStartingPointsException;
import lombok.Getter;
import lombok.NonNull;

/**
 * Represents a map with a start position.
 */
public class Map {

    private char[][] matrix;

    @Getter
    Position start;

    /**
     * Creates a new map and initializes the start but also validates the starting and ending characters.
     *
     * @param matrix the map matrix
     */
    public Map(@NonNull char[][] matrix) {
        this.matrix = matrix;
        initializeAndValidate();
    }

    /**
     * Initializes the map start and validates the starting and ending characters.
     */
    private void initializeAndValidate() {

        boolean endCharacterFound = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '@') {
                    if (start != null) {
                        throw new MultipleStartingPointsException();
                    }
                    start = new Position(i, j);
                } else if (matrix[i][j] == 'x') {
                    endCharacterFound = true;
                }
            }
        }
        if (start == null) {
            throw new MissingStartCharacterException();
        } else if (!endCharacterFound) {
            throw new MissingEndCharacterException();
        }
    }

    /**
     * Returns the value at the specified position.
     * @param position the desired position
     * @return the value at the specified position
     */
    public Character getValue(Position position) {
        return matrix[position.getRow()][position.getColumn()];
    }

    /**
     * Returns the value of the neighbour in the specified direction.
     * @param position the current position
     * @param mapDirection the desired direction
     * @return the value of the neighbour in the specified direction
     */
    public Character getNeighbourValue(Position position, MapDirection mapDirection) {
        try {

            return switch (mapDirection) {
                case UP -> (position.getRow() - 1 < 0) ? null : matrix[position.getRow() - 1][position.getColumn()];
                case DOWN ->
                        (position.getRow() + 1 >= matrix.length) ? null : matrix[position.getRow() + 1][position.getColumn()];
                case LEFT ->
                        (position.getColumn() - 1 < 0) ? null : matrix[position.getRow()][position.getColumn() - 1];
                case RIGHT ->
                        (position.getColumn() + 1 >= matrix[position.getRow()].length) ? null : matrix[position.getRow()][position.getColumn() + 1];
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
