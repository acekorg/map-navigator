package com.aleksandar.unit;

import com.aleksandar.model.MapDirection;
import com.aleksandar.model.Position;
import com.aleksandar.unit.provider.PositionProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Position class.
 */
public class PositionUnitTest {

    @Test
    void shouldConstructPosition() {

        // GIVEN
        int row = 0;
        int col = 0;

        // WHEN
        Position position = new Position(row, col);

        // THEN
        assertNotNull(position);
        assertEquals(row, position.getRow());
        assertEquals(col, position.getColumn());
    }

    @ParameterizedTest
    @ArgumentsSource(PositionProvider.class)
    void shouldMovePosition(int row, int col, MapDirection direction, Position expectedPosition) {

        // WHEN
        Position position = new Position(row, col);
        position.move(direction);

        // THEN
        assertEquals(expectedPosition.getRow(), position.getRow());
        assertEquals(expectedPosition.getColumn(), position.getColumn());
    }
}
