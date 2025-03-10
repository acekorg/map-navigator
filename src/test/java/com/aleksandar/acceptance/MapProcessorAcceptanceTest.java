package com.aleksandar.acceptance;

import com.aleksandar.acceptance.provider.CustomMapsProvider;
import com.aleksandar.acceptance.provider.InvalidMapsProvider;
import com.aleksandar.acceptance.provider.ValidMapsProvider;
import com.aleksandar.model.Map;
import com.aleksandar.model.Result;
import com.aleksandar.processor.MapProcessor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the full functionality of the MapProcessor class by providing valid and invalid maps.
 */
class MapProcessorAcceptanceTest {

    @ParameterizedTest
    @ArgumentsSource(ValidMapsProvider.class)
    void shouldProcessValidProvidedMap(char[][] matrix, Result expectedResult) {

        // GIVEN
        Map map = new Map(matrix);
        MapProcessor processor = new MapProcessor(map);

        // WHEN
        Result result = processor.process();

        assertEquals(expectedResult.getLetters(), result.getLetters());
        assertEquals(expectedResult.getPath(), result.getPath());
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidMapsProvider.class)
    void shouldProcessInvalidProvidedMap(char[][] matrix, IllegalStateException expectedException) {

        assertThrows(expectedException.getClass(), () -> {
            Map map = new Map(matrix);
            MapProcessor processor = new MapProcessor(map);
            processor.process();
        });
    }

    @ParameterizedTest
    @ArgumentsSource(CustomMapsProvider.class)
    void shouldProcessCustomMaps(char[][] matrix, Result expectedResult) {

        Map map = new Map(matrix);
        MapProcessor processor = new MapProcessor(map);
        Result result = processor.process();

        assertEquals(expectedResult.getLetters(), result.getLetters());
        assertEquals(expectedResult.getPath(), result.getPath());
    }
}
