package com.aleksandar.exception;

/**
 * Exception thrown when a map path is missing an end character.
 */
public class MissingEndCharacterException extends IllegalStateException {

    public MissingEndCharacterException() {
        super("Missing end character");
    }
}
