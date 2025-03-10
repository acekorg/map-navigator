package com.aleksandar.exception;

/**
 * Exception thrown when there are multiple starting points.
 */
public class MultipleStartingPointsException extends IllegalStateException {

    public MultipleStartingPointsException() {
        super("Multiple starting points");
    }
}
