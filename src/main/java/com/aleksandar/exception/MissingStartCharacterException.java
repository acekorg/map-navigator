package com.aleksandar.exception;

/**
 * Exception thrown when the start character is missing.
 */
public class MissingStartCharacterException extends IllegalStateException {

    public MissingStartCharacterException() {
        super("Missing start character");
    }
}
