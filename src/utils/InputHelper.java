package utils;

import java.util.Scanner;

public class InputHelper {

    private Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt) {
        System.out.print("  " + prompt + ": ");
        String input = scanner.nextLine();
        return input.trim();
    }

    public String readStringRequired(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (!input.isEmpty()) {
                return input;
            }
            ConsoleUI.printError("This field cannot be empty. Please try again.");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            String input = readString(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Please enter a valid number.");
            }
        }
    }

    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            ConsoleUI.printError("Please enter a number between " + min + " and " + max + ".");
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            String input = readString(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                ConsoleUI.printError("Please enter a valid decimal number.");
            }
        }
    }

    public String readMenuChoice(String prompt) {
        return readString(prompt);
    }

    public boolean confirm(String message) {
        System.out.print("\n  " + message + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    public String readPassword(String prompt) {
        System.out.print("  " + prompt + ": ");
        String input = scanner.nextLine();
        return input.trim();
    }
}