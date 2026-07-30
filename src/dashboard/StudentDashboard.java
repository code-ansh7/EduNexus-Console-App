package dashboard;

import auth.SessionManager;
import services.*;
import utils.ConsoleUI;
import utils.InputHelper;

import java.util.Scanner;

public class StudentDashboard {

    private Scanner scanner;
    private InputHelper input;
    private String userId;
    private String userName;

    public StudentDashboard(Scanner scanner) {
        this.scanner = scanner;
        this.input = new InputHelper(scanner);
        this.userId = SessionManager.getCurrentUserId();
        this.userName = SessionManager.getCurrentUserName();
    }

    public void show() {
        while (SessionManager.isLoggedIn()) {
            ConsoleUI.showStudentDashboardHeader(userName);

            System.out.println("  ┌──────────────────────────────────────────────────┐");
            System.out.println("  │  1. 👤 My Profile                                │");
            System.out.println("  │  2. 📚 Homework                                  │");
            System.out.println("  │  3. 📢 Notice Board                              │");
            System.out.println("  │  4. 📅 My Attendance                             │");
            System.out.println("  │  5. 🗓️ Timetable                                 │");
            System.out.println("  │  6. 😊 Feelings                                  │");
            System.out.println("  │  7. 🔐 Change Password                           │");
            System.out.println("  │  8. 🚪 Logout                                    │");
            System.out.println("  └──────────────────────────────────────────────────┘");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": viewProfile(); break;
                case "2": viewHomework(); break;
                case "3": viewNotices(); break;
                case "4": viewAttendance(); break;
                case "5": viewTimetable(); break;
                case "6": handleFeelings(); break;
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

    private void viewHomework() {
        HomeworkService service = new HomeworkService();
        ProfileService profileService = new ProfileService();
        models.User user = profileService.getUserById(userId);
        if (user == null) {
            ConsoleUI.printError("Profile not found.");
            return;
        }
        service.viewHomework(user.getClassAssigned() + "-" + user.getSection());
        ConsoleUI.pressEnterToContinue();
    }

    private void viewNotices() {
        NoticeService service = new NoticeService();
        service.viewNotices("student");
        ConsoleUI.pressEnterToContinue();
    }

    private void viewAttendance() {
        AttendanceService service = new AttendanceService();
        service.viewMyAttendance(userId);
        ConsoleUI.pressEnterToContinue();
    }

    private void viewTimetable() {
        TimetableService service = new TimetableService();
        ProfileService profileService = new ProfileService();
        models.User user = profileService.getUserById(userId);
        if (user == null) {
            ConsoleUI.printError("Profile not found.");
            return;
        }
        service.viewTimetable(user.getClassAssigned(), user.getSection());
        ConsoleUI.pressEnterToContinue();
    }

    private void handleFeelings() {
        FeelingService service = new FeelingService();
        System.out.println("\n  1. Log today's feeling");
        System.out.println("  2. View my feelings journal");
        System.out.println("  3. Back");
        String choice = input.readMenuChoice("\n  Enter your choice");

        switch (choice) {
            case "1": service.logFeeling(userId); break;
            case "2": service.viewMyFeelings(userId); break;
            case "3": return;
            default: ConsoleUI.printError("Invalid choice.");
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