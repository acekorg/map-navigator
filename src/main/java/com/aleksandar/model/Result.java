package com.aleksandar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the result of the search.
 */
@Getter
@Setter
@AllArgsConstructor
public class Result {
    
    private String letters;
    private String path;
}
