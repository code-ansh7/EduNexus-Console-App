package auth;

import datahandler.CSVReader;
import models.User;
import utils.ConsoleUI;
import utils.InputHelper;

import java.util.List;
import java.util.Scanner;

public class LoginManager {

    private static final String USERS_FILE = "users.csv";

    public boolean login(Scanner scanner) {
        InputHelper input = new InputHelper(scanner);

        ConsoleUI.showLoginHeader();

        String username = input.readStringRequired("Enter your User ID or Email");
        String password = input.readStringRequired("Enter your password");

        List<String[]> allUsers = CSVReader.readAll(USERS_FILE);

        if (allUsers.isEmpty()) {
            ConsoleUI.printError("No users found in the system. Please contact admin.");
            return false;
        }

        User matchedUser = null;

        for (String[] row : allUsers) {
            User u = User.fromCSV(row);
            if (u == null) continue;

            boolean matchById = u.getId().equals(username);
            boolean matchByEmail = u.getEmail().equalsIgnoreCase(username);

            if (matchById || matchByEmail) {
                if (u.getPassword().equals(password)) {
                    if (!u.getStatus().equalsIgnoreCase("active")) {
                        ConsoleUI.printError("Your account is inactive. Please contact admin.");
                        return false;
                    }
                    matchedUser = u;
                    break;
                } else {
                    ConsoleUI.printError("Incorrect password!");
                    return false;
                }
            }
        }

        if (matchedUser == null) {
            ConsoleUI.printError("User not found. Please check your ID or email.");
            return false;
        }

        SessionManager.setCurrentUser(matchedUser);
        ConsoleUI.printSuccess("Login successful! Welcome, " + matchedUser.getName());
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        return true;
    }
}