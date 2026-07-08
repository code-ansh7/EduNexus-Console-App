package services;

import models.User;

public class DashboardRouter {

    public static void openDashboard(User user) {

        switch (user.getRole().toLowerCase()) {
            case "student":
                StudentDashboard.show(user);
                break;
            case "teacher":
                TeacherDashboard.show(user);
                break;
            case "admin":
                AdminDashboard.show(user);
                break;
            default:
                System.out.println("Unknown User Role.");
        }
    }
}