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
        for (User user : users) {

            if (!user.getId().equalsIgnoreCase(enteredId)) {
                continue;
            }

            if (!user.getStatus().equalsIgnoreCase("active")) {
                System.out.println();
                System.out.println("Your account is inactive.");
                return;
            }

            if (!user.getPassword().equals(enteredPassword)) {
                System.out.println();
                System.out.println("Incorrect Password.");
                return;
            }

            System.out.println();
            System.out.println("Login Successful!");
            System.out.println("Welcome " + user.getName());

            DashboardRouter.openDashboard(user);
            return;
        }
    }
}
