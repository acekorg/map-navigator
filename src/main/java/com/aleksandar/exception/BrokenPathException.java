package com.aleksandar.exception;

/**
 * Exception thrown when a map path is broken.
 */
public class BrokenPathException extends IllegalStateException {

    public BrokenPathException() {
        super("Broken path");
    }
}
