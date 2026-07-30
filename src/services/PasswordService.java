package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.User;
import utils.ConsoleUI;
import utils.InputHelper;
import utils.Validator;

import java.util.Scanner;

public class PasswordService {

    private static final String USERS_FILE = "users.csv";

    public void changePassword(String userId, Scanner scanner) {
        InputHelper input = new InputHelper(scanner);

        ConsoleUI.printHeader("🔐 Change Password");

        String[] row = CSVReader.findById(USERS_FILE, userId);
        if (row == null) {
            ConsoleUI.printError("User not found.");
            return;
        }
        User user = User.fromCSV(row);
        if (user == null) {
            ConsoleUI.printError("User not found.");
            return;
        }

        String currentPassword = input.readPassword("Enter current password");
        if (!currentPassword.equals(user.getPassword())) {
            ConsoleUI.printError("Current password is incorrect.");
            return;
        }

        String newPassword = input.readPassword("Enter new password (min 6 characters)");
        if (!Validator.isValidPassword(newPassword)) {
            ConsoleUI.printError("Password must be at least 6 characters long.");
            return;
        }

        String confirmPassword = input.readPassword("Confirm new password");
        if (!newPassword.equals(confirmPassword)) {
            ConsoleUI.printError("Passwords do not match.");
            return;
        }

        user.setPassword(newPassword);
        boolean updated = CSVWriter.updateRow(USERS_FILE, userId, user.toCSV());

        if (updated) {
            ConsoleUI.printSuccess("Password changed successfully!");
        } else {
            ConsoleUI.printError("Failed to update password.");
        }
    }
}