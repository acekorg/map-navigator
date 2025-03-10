package com.aleksandar.acceptance.provider;

import com.aleksandar.exception.*;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Provides invalid maps given from requirements.
 */
public class InvalidMapsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new char[][]{
                        {' ', ' ', ' ', '-', 'A', '-', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {'x', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
                        {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'}
                }, new MissingStartCharacterException()),
                Arguments.of(new char[][]{
                        {'@', '-', '-', 'A', '-', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {' ', 'B', '-', '+', ' ', ' ', ' ', 'C'},
                        {' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', '+', '-', '-', '-', '+'}
                }, new MissingEndCharacterException()),
                Arguments.of(new char[][]{
                        {' ', '@', '-', '-', 'A', '-', '@', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {'x', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
                        {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'}
                }, new MultipleStartingPointsException()),
                Arguments.of(new char[][]{
                        {' ', ' ', ' ', ' ', ' ', 'x', '-', 'B'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {'@', '-', '-', 'A', '-', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {' ', ' ', 'x', '+', ' ', ' ', ' ', 'C'},
                        {' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', '+', '-', '-', '-', '+'}
                }, new ForkInPathException()),
                Arguments.of(new char[][]{
                        {'@', '-', '-', 'A', '-', '+', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', 'B', '-', 'x', ' '}
                }, new BrokenPathException()),
                Arguments.of(new char[][]{
                        {'x', '-', 'B', '-', '@', '-', 'A', '-', 'x'}
                }, new MultipleStartingPathsException()),
                Arguments.of(new char[][]{
                        {'@', '-', 'A', '-', '+', '-', 'B', '-', 'x'}
                }, new FakeTurnException())
        );
    }
}