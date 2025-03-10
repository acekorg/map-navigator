package com.aleksandar.unit.provider;

import com.aleksandar.model.MapDirection;
import com.aleksandar.model.Position;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Provides arguments for testing position movement.
 */
public class PositionProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(0, 0, MapDirection.UP, new Position(-1, 0)),
                Arguments.of(1, 1, MapDirection.UP, new Position(0, 1)),
                Arguments.of(0, 0, MapDirection.DOWN, new Position(1, 0)),
                Arguments.of(0, 0, MapDirection.LEFT, new Position(0, -1)),
                Arguments.of(1, 1, MapDirection.LEFT, new Position(1, 0)),
                Arguments.of(0, 0, MapDirection.RIGHT, new Position(0, 1))
        );
    }
}
