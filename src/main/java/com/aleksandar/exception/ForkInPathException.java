package com.aleksandar.exception;

/**
 * Exception thrown when there are more than one valid direction after a direction change symbol.
 */
public class ForkInPathException extends IllegalStateException {

    public ForkInPathException() {
        super("Fork in path");
    }
}
