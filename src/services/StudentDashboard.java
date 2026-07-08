package services;

import models.User;
import utils.InputHelper;

public class StudentDashboard {

    public static void show(User user) {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("         STUDENT DASHBOARD              ");
            System.out.println("========================================");
            System.out.println();

            System.out.println("Welcome, " + user.getName());
            System.out.println();

            System.out.println("1. My Profile");
            System.out.println("2. Homework");
            System.out.println("3. Notice Board");
            System.out.println("4. Timetable");
            System.out.println("5. Attendance");
            System.out.println("6. My Feelings");
            System.out.println("7. Logout");

            System.out.println();
            System.out.print("Enter Choice : ");

            int choice = InputHelper.scanner.nextInt();
            InputHelper.scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("My Profile (Coming Soon)");
                    break;
                case 2:
                    System.out.println("Homework (Coming Soon)");
                    break;
                case 3:
                    System.out.println("Notice Board (Coming Soon)");
                    break;
                case 4:
                    System.out.println("Timetable (Coming Soon)");
                    break;
                case 5:
                    System.out.println("Attendance (Coming Soon)");
                    break;
                case 6:
                    System.out.println("My Feelings (Coming Soon)");
                    break;
                case 7:
                    System.out.println("Logging Out...");
                    return;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}