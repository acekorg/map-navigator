package com.aleksandar.unit;

import com.aleksandar.exception.MissingEndCharacterException;
import com.aleksandar.exception.MissingStartCharacterException;
import com.aleksandar.exception.MultipleStartingPointsException;
import com.aleksandar.model.Map;
import com.aleksandar.model.MapDirection;
import com.aleksandar.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Map class.
 */
public class MapUnitTest {

    @Test
    void shouldConstructMap() {
        // GIVEN
        char[][] matrix = new char[][]{
                {' ', '+', '-', 'E'},
                {' ', '|', ' ', '|'},
                {' ', '@', ' ', 'x'},
                {' ', ' ', ' ', ' '}
        };

        // WHEN
        Map map = new Map(matrix);

        // THEN
        assertNotNull(map);
        assertEquals(1, map.getStart().getRow());
        assertEquals(2, map.getStart().getColumn());
        assertEquals('x', map.getValue(new Position(2, 3)));
        assertEquals(' ', map.getNeighbourValue(new Position(1, 1), MapDirection.RIGHT));
        assertEquals(' ', map.getNeighbourValue(new Position(1, 1), MapDirection.DOWN));
        assertEquals('|', map.getNeighbourValue(new Position(1, 1), MapDirection.UP));
        assertEquals(' ', map.getNeighbourValue(new Position(1, 1), MapDirection.LEFT));
        assertNull(map.getNeighbourValue(new Position(0, 0), MapDirection.LEFT));
        assertNull(map.getNeighbourValue(new Position(0, 0), MapDirection.UP));
        assertNull(map.getNeighbourValue(new Position(3, 3), MapDirection.RIGHT));
        assertNull(map.getNeighbourValue(new Position(3, 3), MapDirection.DOWN));
    }

    @Test
    void shouldConstructMapFromNull() {
        // GIVEN
        char[][] matrix = null;

        // WHEN
        Exception exception = assertThrows(NullPointerException.class, () -> new Map(matrix));

        // THEN
        assertEquals("matrix is marked non-null but is null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMultipleStartingPoints() {
        // GIVEN
        char[][] matrix = new char[][]{
                {' ', '+', '-', 'E'},
                {'@', '|', ' ', '|'},
                {' ', '@', ' ', 'x'},
                {' ', ' ', ' ', ' '}
        };

        // WHEN
        Exception exception = assertThrows(MultipleStartingPointsException.class, () -> new Map(matrix));

        // THEN
        assertEquals("Multiple starting points found", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMissingStartCharacter() {
        // GIVEN
        char[][] matrix = new char[][]{
                {' ', '+', '-', 'E'},
                {' ', '|', ' ', '|'},
                {' ', ' ', ' ', 'x'},
                {' ', ' ', ' ', ' '}
        };

        // WHEN
        Exception exception = assertThrows(MissingStartCharacterException.class, () -> new Map(matrix));

        // THEN
        assertEquals("Missing starting point", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMissingEndCharacter() {
        // GIVEN
        char[][] matrix = new char[][]{
                {' ', '+', '-', 'E'},
                {' ', '|', ' ', '|'},
                {' ', '@', ' ', ' '},
                {' ', ' ', ' ', ' '}
        };

        // WHEN
        Exception exception = assertThrows(MissingEndCharacterException.class, () -> new Map(matrix));

        // THEN
        assertEquals("Missing end character", exception.getMessage());
    }
}
