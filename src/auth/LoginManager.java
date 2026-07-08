package auth;

import java.util.ArrayList;

import datahandler.CSVReader;
import models.User;
import utils.InputHelper;

public class LoginManager {

    public static void showLoginScreen() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("                LOGIN");
        System.out.println("========================================");
        System.out.println();
        System.out.print("User ID : ");
        String enteredId = InputHelper.scanner.nextLine();
        System.out.print("Password : ");
        String enteredPassword = InputHelper.scanner.nextLine();
        ArrayList<User> users = CSVReader.readUsers();
        boolean loginSuccess = false;
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(enteredId)) {
                if (user.getPassword().equals(enteredPassword)) {
                    loginSuccess = true;
                    System.out.println();
                    System.out.println("Login Successful!");
                    System.out.println("Welcome " + user.getName());
                    System.out.println();
                    if (user.getRole().equalsIgnoreCase("student")) {
                        System.out.println("Opening Student Dashboard...");
                    }
                    else if (user.getRole().equalsIgnoreCase("teacher")) {
                        System.out.println("Opening Teacher Dashboard...");
                    }
                    else if (user.getRole().equalsIgnoreCase("admin")) {
                        System.out.println("Opening Admin Dashboard...");
                    }
                    break;
                }
                else {
                    System.out.println();
                    System.out.println("Incorrect Password.");
                    return;
                }
            }
        }
        if (!loginSuccess) {
            System.out.println();
            System.out.println("User ID not found.");
        }
    }
}