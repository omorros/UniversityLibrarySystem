package main.util;

/** Simple utility class for input validation. */
public class InputValidator {
    public static String nonEmpty(String input, String field) {
        if (input == null || input.trim().isEmpty())
            throw new IllegalArgumentException(field + " cannot be empty!");
        return input;
    }
}
