package dashboard;

import auth.SessionManager;
import java.util.Scanner;
import services.*;
import utils.ConsoleUI;
import utils.InputHelper;

public class TeacherDashboard {

    private Scanner scanner;
    private InputHelper input;
    private String userId;
    private String userName;

    public TeacherDashboard(Scanner scanner) {
        this.scanner = scanner;
        this.input = new InputHelper(scanner);
        this.userId = SessionManager.getCurrentUserId();
        this.userName = SessionManager.getCurrentUserName();
    }

    public void show() {
        while (SessionManager.isLoggedIn()) {
            ConsoleUI.showTeacherDashboardHeader(userName);

            System.out.println("  ┌──────────────────────────────────────────────────┐");
            System.out.println("  │  1. 👤 My Profile                                │");
            System.out.println("  │  2. 📚 Upload Homework                           │");
            System.out.println("  │  3. 📋 Mark Attendance                           │");
            System.out.println("  │  4. 📢 Notice Board                              │");
            System.out.println("  │  5. 🗓️ Timetable                                 │");
            System.out.println("  │  6. 💬 Student Feedback                          │");
            System.out.println("  │  7. 🔐 Change Password                           │");
            System.out.println("  │  8. 🚪 Logout                                    │");
            System.out.println("  └──────────────────────────────────────────────────┘");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": viewProfile(); break;
                case "2": uploadHomework(); break;
                case "3": markAttendance(); break;
                case "4": viewNotices(); break;
                case "5": viewTimetable(); break;
                case "6": viewStudentFeedback(); break;
                case "7": changePassword(); break;
                case "8": logout(); break;
                default: ConsoleUI.printError("Invalid choice. Please try again.");
            }
        }
    }

    private void viewProfile() {
        ProfileService service = new ProfileService();
        service.viewProfile(userId);
        ConsoleUI.pressEnterToContinue();
    }

    private void uploadHomework() {
        HomeworkService service = new HomeworkService();
        ConsoleUI.printHeader("📚 Upload Homework");

        String subject = input.readStringRequired("Subject");
        String description = input.readStringRequired("Description");
        String dueDate = input.readStringRequired("Due Date (YYYY-MM-DD)");

        
        String classAssigned = input.readStringRequired("Class (e.g. 10-A)");

        boolean success = service.addHomework(subject, description, dueDate, classAssigned, userId);
        if (success) {
            ConsoleUI.printSuccess("Homework uploaded successfully!");
        } else {
            ConsoleUI.printError("Failed to upload homework.");
        }
        ConsoleUI.pressEnterToContinue();
    }

    private void markAttendance() {
        AttendanceService service = new AttendanceService();
        String classSection = input.readStringRequired("Enter class (e.g. 10-A)");
        service.markAttendanceForClass(classSection, userId);
        ConsoleUI.pressEnterToContinue();
    }

    private void viewNotices() {
        NoticeService service = new NoticeService();
        System.out.println("\n  1. View notices");
        System.out.println("  2. Post new notice");
        String choice = input.readMenuChoice("\n  Enter your choice");

        if (choice.equals("1")) {
            service.viewNotices("teacher");
        } else if (choice.equals("2")) {
            String title = input.readStringRequired("Notice title");
            String content = input.readStringRequired("Notice content");
            System.out.println("  Target: 1. All  2. Students  3. Teachers");
            String target = input.readMenuChoice("  Choice");
            String targetRole;
            if (target.equals("2")) targetRole = "student";
            else if (target.equals("3")) targetRole = "teacher";
            else targetRole = "all";

            service.addNotice(title, content, userId, targetRole);
            ConsoleUI.printSuccess("Notice posted successfully!");
        }
        ConsoleUI.pressEnterToContinue();
    }

    private void viewTimetable() {
        TimetableService service = new TimetableService();
        String classSection = input.readStringRequired("Enter class (e.g. 10-A)");
        String[] parts = classSection.split("-");
        if (parts.length < 2) {
            ConsoleUI.printError("Invalid format. Use 10-A format.");
            return;
        }
        service.viewTimetable(parts[0], parts[1]);
        ConsoleUI.pressEnterToContinue();
    }

    private void viewStudentFeedback() {
        FeelingService service = new FeelingService();
        ConsoleUI.printHeader("💬 Student Feedback");
        java.util.List<models.Feeling> all = service.getAllFeelings();
        if (all.isEmpty()) {
            ConsoleUI.printInfo("No feedback yet.");
        } else {
            for (models.Feeling f : all) {
                System.out.println("  📌 Student " + f.getStudentId() + " on " + f.getDate() +
                                   " - " + f.getMood() + " - " + f.getNote());
            }
        }
        ConsoleUI.pressEnterToContinue();
    }

    private void changePassword() {
        PasswordService service = new PasswordService();
        service.changePassword(userId, scanner);
        ConsoleUI.pressEnterToContinue();
    }

    private void logout() {
        if (input.confirm("Are you sure you want to logout?")) {
            SessionManager.logout();
            ConsoleUI.printSuccess("Logged out successfully.");
        }
    }
}