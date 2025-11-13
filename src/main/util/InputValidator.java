package main.util;

/**
 * Utility class that provides input validation methods.
 * <p>
 * The {@code InputValidator} class helps ensure that user input
 * meets basic data integrity requirements before being processed.
 * This promotes cleaner code and better error handling across
 * the application.
 * </p>
 * <p>
 * This class demonstrates good programming practices such as
 * static utility design (no instantiation required) and the
 * use of exceptions for validation feedback.
 * </p>
 */
public class InputValidator {

    /**
     * Ensures that a given string input is not null or empty.
     * <p>
     * If the input is invalid, an {@link IllegalArgumentException}
     * is thrown, preventing further processing of incomplete data.
     * </p>
     *
     * @param input the user-provided input string
     * @param field the name of the field being validated (used in the error message)
     * @return the trimmed input string if valid
     * @throws IllegalArgumentException if the input is null or blank
     */
    public static String nonEmpty(String input, String field) {
        // Validate that the input is not null or empty.
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty!");
        }
        return input;
    }
}
