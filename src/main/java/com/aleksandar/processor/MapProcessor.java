package com.aleksandar.processor;

import com.aleksandar.exception.*;
import com.aleksandar.model.*;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.aleksandar.model.MapDirection.LEFT;
import static com.aleksandar.model.MapDirection.RIGHT;
import static com.aleksandar.model.MapDirection.UP;
import static com.aleksandar.model.MapDirection.DOWN;
import static com.aleksandar.utils.MapUtils.isCapitalLetter;

/**
 * Processes the map and returns the result.
 */
public class MapProcessor {

    private final Map map;
    private final Position position;
    private MapDirection direction;

    /**
     * Constructs a new instance of the processor initializing the starting position and direction.
     *
     * @param map the map to process
     */
    public MapProcessor(Map map) {
        this.map = map;
        position = map.getStart();
        direction = determineInitialDirection();
    }

    /**
     * Processes the map and returns the result.
     * @return the result path and letters
     */
    public Result process() {

        boolean iterationComplete = false;

        // To avoid duplication of letters, only Position objects are stored in a set
        Set<Position> letters = new LinkedHashSet<>();
        StringBuilder path = new StringBuilder();

         do {

            char field = map.getValue(position);
            path.append(field);

            if (field == 'x') {
                iterationComplete = true;
            } else if (field == '+') {
                rotateDirectionIfNeeded(false);
            } else if (isCapitalLetter(field)) {
                Position letterPosition = new Position(position.getRow(), position.getColumn());
                letters.add(letterPosition);
                rotateDirectionIfNeeded(true);
            } else if (field == ' ') {
                throw new BrokenPathException();
            }

            position.move(direction);

        } while (!iterationComplete);

        String collectedLetters = collectFoundLetters(letters);

        return new Result(
                collectedLetters,
                path.toString()
        );
    }

    /**
     * Rotates the direction if needed.
     * @param isLetterPosition True if it's a rotation by letter, false if it's rotation by '+'.
     */
    private void rotateDirectionIfNeeded(boolean isLetterPosition) {

        // If next step in the same direction is valid, continue in the same direction
        Character nextStepValue = map.getNeighbourValue(position, direction);
        if (Character.valueOf(direction.getSymbol()).equals(nextStepValue)
                    || Character.valueOf('+').equals(nextStepValue)
                    || isCapitalLetter(nextStepValue)) {
            if (isLetterPosition) {
                return;
            } else {
                throw new FakeTurnException();
            }
        }

        // If going in the same direction isn't an option, then check for a valid turn
        switch (direction) {
            case UP:
            case DOWN:

                // Vertical to horizontal turn
                rotateDirection(LEFT, RIGHT);
                break;
            case LEFT:
            case RIGHT:

                // Horizontal to vertical turn
                rotateDirection(UP, DOWN);
                break;
        }
    }

    /**
     * Rotates the direction 90 or 270 degrees depending on which way it's a valid turn.
     * @param directionOptionOne the first direction option
     * @param directionOptionTwo the second direction option
     */
    private void rotateDirection(MapDirection directionOptionOne, MapDirection directionOptionTwo) {

        Character optionOneNeighbour = map.getNeighbourValue(position, directionOptionOne);
        Character optionTwoNeighbour = map.getNeighbourValue(position, directionOptionTwo);

        if (Character.valueOf(directionOptionOne.getSymbol()).equals(optionOneNeighbour)
                && Character.valueOf(directionOptionTwo.getSymbol()).equals(optionTwoNeighbour)) {
            throw new ForkInPathException();
        } else if (isCharacterValidTurn(optionOneNeighbour, directionOptionOne)) {
            direction = directionOptionOne;
        } else if (isCharacterValidTurn(optionTwoNeighbour, directionOptionTwo)) {
            direction = directionOptionTwo;
        }
    }

    /**
     * Determines the initial direction based on the starting position.
     * @return the initial direction
     */
    private MapDirection determineInitialDirection() {

        Character upValue = map.getNeighbourValue(position, UP);
        Character downValue = map.getNeighbourValue(position, DOWN);
        Character leftValue = map.getNeighbourValue(position, LEFT);
        Character rightValue = map.getNeighbourValue(position, RIGHT);

        boolean upValid = upValue != null && ("|+".contains(String.valueOf(upValue)) || isCapitalLetter(upValue));
        boolean downValid = downValue != null && ("|+".contains(String.valueOf(downValue)) || isCapitalLetter(downValue));
        boolean leftValid = leftValue != null && ("-+".contains(String.valueOf(leftValue)) || isCapitalLetter(leftValue));
        boolean rightValid = rightValue != null && ("-+".contains(String.valueOf(rightValue)) || isCapitalLetter(rightValue));

        int trueCount = (upValid ? 1 : 0) + (downValid ? 1 : 0) + (leftValid ? 1 : 0) + (rightValid ? 1 : 0);
        if (trueCount != 1) {
            throw new MultipleStartingPathsException();
        }

        if (upValid) {
            return UP;
        } else if (downValid) {
            return DOWN;
        } else if (leftValid) {
            return LEFT;
        } else if (rightValid) {
            return RIGHT;
        }
        return null;
    }

    /**
     * Collects the found letters in the order they were found.
     * @param letterPositions the positions of the found letters
     * @return the found letters
     */
    private String collectFoundLetters(Set<Position> letterPositions) {

        StringBuilder foundLettersBuilder = new StringBuilder();

        for (Position letterPosition : letterPositions) {
            Character value = map.getValue(letterPosition);
            foundLettersBuilder.append(value);
        }

        return foundLettersBuilder.toString();
    }

    private boolean isCharacterValidTurn(Character character, MapDirection direction) {

        if (character == null || character.equals(' ')) {
            return false;
        }

        return Character.valueOf(direction.getSymbol()).equals(character)
                || Character.valueOf('+').equals(character)
                || Character.valueOf('x').equals(character)
                || isCapitalLetter(character);
    }
}
