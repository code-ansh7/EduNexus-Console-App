package services;

import models.User;
import utils.InputHelper;

public class TeacherDashboard {

    public static void show(User user) {
        while (true) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("         TEACHER DASHBOARD");
            System.out.println("========================================");
            System.out.println();

            System.out.println("Welcome, " + user.getName());
            System.out.println();

            System.out.println("1. My Profile");
            System.out.println("2. Upload Homework");
            System.out.println("3. View My Classes");
            System.out.println("4. Student Attendance");
            System.out.println("5. Notice Board");
            System.out.println("6. Timetable");
            System.out.println("7. Student Feedback");
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
                    System.out.println("Upload Homework (Coming Soon)");
                    break;

                case 3:
                    System.out.println("View My Classes (Coming Soon)");
                    break;

                case 4:
                    System.out.println("Attendance Module (Coming Soon)");
                    break;

                case 5:
                    System.out.println("Notice Board (Coming Soon)");
                    break;

                case 6:
                    System.out.println("Timetable (Coming Soon)");
                    break;

                case 7:
                    System.out.println("Student Feedback (Coming Soon)");
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
