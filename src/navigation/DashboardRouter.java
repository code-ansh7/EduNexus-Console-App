package navigation;

import dashboard.StudentDashboard;
import dashboard.TeacherDashboard;
import dashboard.AdminDashboard;
import utils.ConsoleUI;

import java.util.Scanner;

public class DashboardRouter {

    public void routeToDashboard(String role, String userId, Scanner scanner) {
        if (role == null) {
            ConsoleUI.printError("Invalid role detected.");
            return;
        }

        switch (role.toLowerCase()) {
            case "student":
                StudentDashboard studentDashboard = new StudentDashboard(scanner);
                studentDashboard.show();
                break;
            case "teacher":
                TeacherDashboard teacherDashboard = new TeacherDashboard(scanner);
                teacherDashboard.show();
                break;
            case "admin":
                AdminDashboard adminDashboard = new AdminDashboard(scanner);
                adminDashboard.show();
                break;
            default:
                ConsoleUI.printError("Unknown role: " + role);
        }
    }
}