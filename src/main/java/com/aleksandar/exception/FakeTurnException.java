package com.aleksandar.exception;

/**
 * Exception thrown when after a direction change symbol there is no change in direction.
 */
public class FakeTurnException  extends IllegalStateException {

    public FakeTurnException() {
        super("Fake turn");
    }
}
