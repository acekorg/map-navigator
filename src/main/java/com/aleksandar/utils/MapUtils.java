package com.aleksandar.utils;

/**
 * Utility class for map operations.
 */
public final class MapUtils {

    /**
     * Checks if a character is a capital letter in a null safe fashion.
     *
     * @param c the character to check
     * @return true if the character is a capital letter, false otherwise
     */
    public static boolean isCapitalLetter(Character c) {
        return c != null && Character.isUpperCase(c);
    }
}
