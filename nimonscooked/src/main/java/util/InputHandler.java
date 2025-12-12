package util;

/**
 * Utility class for input handling.
 */
public class InputHandler {
    
    /**
     * Validates if the input is a valid menu choice.
     */
    public static boolean isValidMenuChoice(String input, int maxChoice) {
        try {
            int choice = Integer.parseInt(input);
            return choice >= 1 && choice <= maxChoice;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Parses an integer from string input.
     */
    public static int parseInt(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
