package com.aleksandar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Represents a position in the grid.
 */
@Getter
@Setter
@AllArgsConstructor
public class Position {

    @NonNull
    private int row;
    @NonNull
    private int column;

    /**
     * Moves the position in the specified direction.
     * @param direction the desired direction
     */
    public void move(MapDirection direction) {
        switch (direction) {
            case RIGHT:
                column++;
                break;
            case LEFT:
                column--;
                break;
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
        }
    }

    @Override
    public int hashCode() {
        return row + column;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Position position && position.row == row && position.column == column;
    }
}
