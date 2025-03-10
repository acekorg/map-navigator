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

        return new Result(
                collectFoundLetters(letters),
                path.toString()
        );
    }

    private void rotateDirectionIfNeeded(boolean isLetterPosition) {

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

        switch (direction) {
            case UP:
            case DOWN:

                Character leftNeighbour = map.getNeighbourValue(position, LEFT);
                Character rightNeighbour = map.getNeighbourValue(position, RIGHT);

                if (Character.valueOf('-').equals(leftNeighbour) && Character.valueOf('-').equals(rightNeighbour)) {

                    throw new ForkInPathException();

                } else if (Character.valueOf('-').equals(leftNeighbour)
                        || Character.valueOf('+').equals(leftNeighbour)
                        || isCapitalLetter(leftNeighbour)) {

                    direction = MapDirection.LEFT;

                } else if (Character.valueOf('-').equals(rightNeighbour)
                        || Character.valueOf('+').equals(rightNeighbour)
                        || isCapitalLetter(rightNeighbour)) {
                    direction = MapDirection.RIGHT;
                }
                break;
            case LEFT:
            case RIGHT:
                Character upNeighbour = map.getNeighbourValue(position, UP);
                Character downNeighbour = map.getNeighbourValue(position, DOWN);

                if (Character.valueOf('|').equals(upNeighbour) && Character.valueOf('|').equals(downNeighbour)) {

                    throw new ForkInPathException();

                } else if (Character.valueOf('|').equals(upNeighbour)
                        || Character.valueOf('+').equals(upNeighbour)
                        || isCapitalLetter(upNeighbour)) {

                    direction = MapDirection.UP;

                } else if (Character.valueOf('|').equals(downNeighbour)
                        || Character.valueOf('+').equals(downNeighbour)
                        || isCapitalLetter(downNeighbour)) {

                    direction = MapDirection.DOWN;
                }
                break;
        }
    }

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

    private String collectFoundLetters(Set<Position> letterPositions) {

        StringBuilder foundLettersBuilder = new StringBuilder();

        for (Position letterPosition : letterPositions) {
            Character value = map.getValue(letterPosition);
            foundLettersBuilder.append(value);
        }

        return foundLettersBuilder.toString();
    }
}
