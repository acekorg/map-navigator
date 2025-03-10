package com.aleksandar.acceptance.provider;

import com.aleksandar.model.Result;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Provides some additional custom maps for testing edge cases like double intersections or compact letter turns.
 */
public class CustomMapsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                // Map with double intersection cross
                Arguments.of(new char[][]{
                        {'@', '-', '-', '-', 'A', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', '|', '+', 'B', '-', '+'},
                        {' ', ' ', ' ', ' ', '|', '|', ' ', ' ', 'C'},
                        {'x', '-', 'D', '-', '|', '|', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', '+', '+', ' ', ' ', ' '}
                }, new Result("ABCD", "@---A|||++||+B-+C+--||-D-x")),
                // Map with compact letter turns
                Arguments.of(new char[][]{
                        {' ', '+', '-', 'E', '-', '+'},
                        {' ', '|', ' ', ' ', '+', 'S', '-', '+'},
                        {'@', 'P', 'R', ' ', '+', '+', ' ', 'S'},
                        {' ', 'C', 'O', ' ', ' ', ' ', ' ', 'x'}
                }, new Result("PROCESS", "@PROCP|+-E-+S+++S-+Sx")),
                // Map with parallel neighbouring paths and change of direction with letter followed by x.
                Arguments.of(new char[][]{
                        {' ', ' ', '+', '+', ' ', ' '},
                        {' ', ' ', '|', '|', ' ', ' ', ' ', ' '},
                        {'@', '-', '+', '|', ' ', '+', '-', 'C'},
                        {' ', ' ', ' ', 'A', '-', 'B', ' ', 'x'}
                }, new Result("ABC", "@-+|++||A-B+-Cx"))
        );
    }
}

