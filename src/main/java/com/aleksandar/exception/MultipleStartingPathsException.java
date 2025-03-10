package com.aleksandar.exception;

/**
 * Exception thrown when there are multiple starting paths.
 */
public class MultipleStartingPathsException extends IllegalStateException {

    public MultipleStartingPathsException() {
        super("Multiple starting paths");
    }
}
