package com.aleksandar.model;

/**
 * Represents a navigation direction.
 */
public enum MapDirection {

    RIGHT('-'),
    LEFT('-'),
    UP('|'),
    DOWN('|');

    private final char symbol;

    MapDirection(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
