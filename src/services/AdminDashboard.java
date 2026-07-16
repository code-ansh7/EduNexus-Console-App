package services;

import models.User;
import utils.InputHelper;

public class AdminDashboard {

    public static void show(User user) {
        while (true) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("          ADMIN DASHBOARD");
            System.out.println("========================================");
            System.out.println();

            System.out.println("Welcome, " + user.getName());
            System.out.println();

            System.out.println("1. View Profile");
            System.out.println("2. Manage Students");
            System.out.println("3. Manage Teachers");
            System.out.println("4. Manage Notices");
            System.out.println("5. School Reports");
            System.out.println("6. System Settings");
            System.out.println("7. Backup Database");
            System.out.println("8. Logout");

            System.out.println();
            System.out.print("Enter Choice : ");

            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();

            switch (choice) {

                case 1:
                    ProfileService.showProfile(user);
                    break;
                
                case 2:
                    System.out.println("Manage Students (Coming Soon)");
                    break;

                case 3:
                    System.out.println("Manage Teachers (Coming Soon)");
                    break;

                case 4:
                    System.out.println("Manage Notices (Coming Soon)");
                    break;

                case 5:
                    System.out.println("School Reports (Coming Soon)");
                    break;

                case 6:
                    System.out.println("System Settings (Coming Soon)");
                    break;

                case 7:
                    System.out.println("Database Backup (Coming Soon)");
                    break;

                case 8:
                    System.out.println("Logging Out...");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
