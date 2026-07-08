package auth;

import java.util.ArrayList;
import services.DashboardRouter;
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
                    DashboardRouter.openDashboard(user);
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