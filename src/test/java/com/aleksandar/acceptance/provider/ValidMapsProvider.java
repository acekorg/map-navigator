package com.aleksandar.acceptance.provider;

import com.aleksandar.model.Result;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/**
 * Provides valid maps given from requirements.
 */
public class ValidMapsProvider implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new char[][] {
                        {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {'x', '-', 'B', '-', '+', ' ', ' ', ' ', 'C'},
                        {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', '+', '-', '-', '-', '+'}
                }, new Result("ACB", "@---A---+|C|+---+|+-B-x")),
                Arguments.of(new char[][] {
                        {'@', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {'|', ' ', '+', '-', 'C', '-', '-', '+'},
                        {'A', ' ', '|', ' ', ' ', ' ', ' ', '|'},
                        {'+', '-', '-', '-', 'B', '-', '-', '+'},
                        {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', 'x'},
                        {' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {' ', ' ', '+', '-', '-', '-', 'D', '-', '-', '+'}
                }, new Result("ABCD", "@|A+---B--+|+--C-+|-||+---D--+|x")),
                Arguments.of(new char[][] {
                        {'@', '-', '-', '-', 'A', '-', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {'x', '-', 'B', '-', '+', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', '+', '-', '-', '-', 'C'}
                }, new Result("ACB", "@---A---+|||C---+|+-B-x")),
                Arguments.of(new char[][] {
                        {' ', ' ', ' ', ' ', '+', '-', 'O', '-', 'N', '-', '+'},
                        {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '+', '-', 'I', '-', '+'},
                        {'@', '-', 'G', '-', 'O', '-', '+', ' ', '|', ' ', '|', ' ', '|'},
                        {' ', ' ', ' ', ' ', '|', ' ', '|', ' ', '+', '-', '+', ' ', 'E'},
                        {' ', ' ', ' ', ' ', '+', '-', '+', ' ', ' ', ' ', ' ', ' ', 'S'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x'}
                }, new Result("GOONIES", "@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x")),
                Arguments.of(new char[][] {
                        {' ', '+', '-', 'L', '-', '+'},
                        {' ', '|', ' ', ' ', '+', 'A', '-', '+'},
                        {'@', 'B', '+', ' ', '+', '+', ' ', 'H'},
                        {' ', '+', '+', ' ', ' ', ' ', ' ', 'x'}
                }, new Result("BLAH", "@B+++B|+-L-+A+++A-+Hx")),
                Arguments.of(new char[][] {
                        {'@', '-', 'A', '-', '-', '+'},
                        {' ', ' ', ' ', ' ', ' ', '|'},
                        {' ', ' ', ' ', ' ', ' ', '+', '-', 'B', '-', '-', 'x', '-', 'C', '-', '-', 'D'}
                }, new Result("AB", "@-A--+|+-B--x"))
        );
    }
}
