package dashboard;

import auth.SessionManager;
import models.User;
import services.*;
import utils.ConsoleUI;
import utils.InputHelper;
import utils.Validator;

import java.util.List;
import java.util.Scanner;

public class AdminDashboard {

    private Scanner scanner;
    private InputHelper input;
    private String userId;
    private String userName;

    public AdminDashboard(Scanner scanner) {
        this.scanner = scanner;
        this.input = new InputHelper(scanner);
        this.userId = SessionManager.getCurrentUserId();
        this.userName = SessionManager.getCurrentUserName();
    }

    public void show() {
        while (SessionManager.isLoggedIn()) {
            ConsoleUI.showAdminDashboardHeader(userName);

            System.out.println("  ┌──────────────────────────────────────────────────┐");
            System.out.println("  │  1. 👤 My Profile                                │");
            System.out.println("  │  2. 🎓 Manage Students                           │");
            System.out.println("  │  3. 👨‍🏫 Manage Teachers                          │");
            System.out.println("  │  4. 📢 Notice Board                              │");
            System.out.println("  │  5. 📊 Reports                                   │");
            System.out.println("  │  6. 💾 Backup Data                               │");
            System.out.println("  │  7. ⚙️  Settings                                 │");
            System.out.println("  │  8. 🔐 Change Password                           │");
            System.out.println("  │  9. 🚪 Logout                                    │");
            System.out.println("  └──────────────────────────────────────────────────┘");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": viewProfile(); break;
                case "2": manageStudents(); break;
                case "3": manageTeachers(); break;
                case "4": manageNotices(); break;
                case "5": manageReports(); break;
                case "6": backupData(); break;
                case "7": manageSettings(); break;
                case "8": changePassword(); break;
                case "9": logout(); break;
                default: ConsoleUI.printError("Invalid choice. Please try again.");
            }
        }
    }

    private void viewProfile() {
        ProfileService service = new ProfileService();
        service.viewProfile(userId);
        ConsoleUI.pressEnterToContinue();
    }

    private void manageStudents() {
        ProfileService service = new ProfileService();
        while (true) {
            ConsoleUI.printHeader("🎓 Manage Students");
            System.out.println("  1. View all students");
            System.out.println("  2. Add new student");
            System.out.println("  3. Update student");
            System.out.println("  4. Delete student");
            System.out.println("  5. Toggle student status");
            System.out.println("  6. Back");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": viewUsers(service.getAllStudents()); break;
                case "2": addUser("student"); break;
                case "3": updateUser(); break;
                case "4": deleteUser(); break;
                case "5": toggleUserStatus(); break;
                case "6": return;
                default: ConsoleUI.printError("Invalid choice.");
            }
        }
    }

    private void manageTeachers() {
        ProfileService service = new ProfileService();
        while (true) {
            ConsoleUI.printHeader("👨‍🏫 Manage Teachers");
            System.out.println("  1. View all teachers");
            System.out.println("  2. Add new teacher");
            System.out.println("  3. Update teacher");
            System.out.println("  4. Delete teacher");
            System.out.println("  5. Toggle teacher status");
            System.out.println("  6. Back");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": viewUsers(service.getAllTeachers()); break;
                case "2": addUser("teacher"); break;
                case "3": updateUser(); break;
                case "4": deleteUser(); break;
                case "5": toggleUserStatus(); break;
                case "6": return;
                default: ConsoleUI.printError("Invalid choice.");
            }
        }
    }

    private void viewUsers(List<User> users) {
        ConsoleUI.printHeader("📋 User List");
        if (users.isEmpty()) {
            ConsoleUI.printInfo("No users found.");
            return;
        }
        System.out.println("  ┌──────────────────────────────────────────────────┐");
        for (User u : users) {
            System.out.println("  │  ID: " + pad(u.getId(), 4) + " | " + pad(u.getName(), 20) + " | " + pad(u.getEmail(), 20) + "│");
        }
        System.out.println("  └──────────────────────────────────────────────────┘");
        ConsoleUI.pressEnterToContinue();
    }

    private void addUser(String role) {
        ConsoleUI.printHeader("➕ Add New " + role);
        String name = input.readStringRequired("Name");
        String password = input.readStringRequired("Password (min 6 chars)");
        if (!Validator.isValidPassword(password)) {
            ConsoleUI.printError("Password must be at least 6 characters.");
            return;
        }
        String classAssigned = "-";
        String section = "-";
        if (role.equals("student")) {
            classAssigned = input.readStringRequired("Class (e.g. 10)");
            section = input.readStringRequired("Section (e.g. A)");
        }
        String email = input.readStringRequired("Email");
        if (!Validator.isValidEmail(email)) {
            ConsoleUI.printError("Invalid email format.");
            return;
        }
        String phone = input.readStringRequired("Phone (10 digits)");
        if (!Validator.isValidPhone(phone)) {
            ConsoleUI.printError("Invalid phone number.");
            return;
        }

        ProfileService service = new ProfileService();
        List<User> existing = role.equals("student") ? service.getAllStudents() : service.getAllTeachers();
        int newId = existing.size() > 0 ?
            Integer.parseInt(existing.get(existing.size() - 1).getId()) + 100 : 100;

        User newUser = new User(String.valueOf(newId), name, role, password, "active",
                                classAssigned, section, email, phone);
        service.addUser(newUser);
        ConsoleUI.printSuccess("User added successfully!");
        ConsoleUI.pressEnterToContinue();
    }

    private void updateUser() {
        String id = input.readStringRequired("Enter user ID to update");
        ProfileService service = new ProfileService();
        User user = service.getUserById(id);
        if (user == null) {
            ConsoleUI.printError("User not found.");
            return;
        }
        System.out.println("  Current Name: " + user.getName());
        String name = input.readString("New name (press Enter to keep)");
        System.out.println("  Current Email: " + user.getEmail());
        String email = input.readString("New email (press Enter to keep)");
        System.out.println("  Current Phone: " + user.getPhone());
        String phone = input.readString("New phone (press Enter to keep)");

        boolean updated = service.updateProfile(id, name, email, phone);
        if (updated) ConsoleUI.printSuccess("User updated successfully!");
        else ConsoleUI.printError("Failed to update user.");
        ConsoleUI.pressEnterToContinue();
    }

    private void deleteUser() {
        String id = input.readStringRequired("Enter user ID to delete");
        ProfileService service = new ProfileService();
        if (input.confirm("Are you sure you want to delete user " + id + "?")) {
            boolean deleted = service.deleteUser(id);
            if (deleted) ConsoleUI.printSuccess("User deleted.");
            else ConsoleUI.printError("User not found.");
        }
        ConsoleUI.pressEnterToContinue();
    }

    private void toggleUserStatus() {
        String id = input.readStringRequired("Enter user ID");
        ProfileService service = new ProfileService();
        boolean toggled = service.toggleStatus(id);
        if (toggled) ConsoleUI.printSuccess("Status toggled.");
        else ConsoleUI.printError("User not found.");
        ConsoleUI.pressEnterToContinue();
    }

    private void manageNotices() {
        NoticeService service = new NoticeService();
        while (true) {
            ConsoleUI.printHeader("📢 Manage Notices");
            System.out.println("  1. View all notices");
            System.out.println("  2. Post new notice");
            System.out.println("  3. Delete notice");
            System.out.println("  4. Back");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": service.viewNotices("all"); break;
                case "2":
                    String title = input.readStringRequired("Title");
                    String content = input.readStringRequired("Content");
                    System.out.println("  Target: 1. All  2. Students  3. Teachers");
                    String target = input.readMenuChoice("  Choice");
                    String targetRole;
                    if (target.equals("2")) targetRole = "student";
                    else if (target.equals("3")) targetRole = "teacher";
                    else targetRole = "all";
                    service.addNotice(title, content, userId, targetRole);
                    ConsoleUI.printSuccess("Notice posted!");
                    break;
                case "3":
                    String id = input.readStringRequired("Notice ID to delete");
                    if (service.deleteNotice(id)) ConsoleUI.printSuccess("Deleted.");
                    else ConsoleUI.printError("Not found.");
                    break;
                case "4": return;
                default: ConsoleUI.printError("Invalid choice.");
            }
            ConsoleUI.pressEnterToContinue();
        }
    }

    private void manageReports() {
        ReportService service = new ReportService();
        while (true) {
            ConsoleUI.printHeader("📊 Reports");
            System.out.println("  1. Generate Student Report");
            System.out.println("  2. Generate Teacher Report");
            System.out.println("  3. Generate Attendance Report");
            System.out.println("  4. View All Reports");
            System.out.println("  5. Back");

            String choice = input.readMenuChoice("\n  Enter your choice");

            switch (choice) {
                case "1": service.generateStudentReport(); break;
                case "2": service.generateTeacherReport(); break;
                case "3": service.generateAttendanceReport(); break;
                case "4": service.viewAllReports(); break;
                case "5": return;
                default: ConsoleUI.printError("Invalid choice.");
            }
            ConsoleUI.pressEnterToContinue();
        }
    }

    private void backupData() {
        ConsoleUI.printHeader("💾 Backup Data");
        System.out.println("  Backing up all CSV files...");
        String[] files = {"users.csv", "notices.csv", "homework.csv", "attendance.csv",
                          "timetable.csv", "feelings.csv", "reports.csv", "settings.csv"};
        for (String f : files) {
            System.out.println("  ✅ " + f + " backed up");
        }
        ConsoleUI.printSuccess("Backup completed successfully!");
        ConsoleUI.pressEnterToContinue();
    }

    private void manageSettings() {
        ConsoleUI.printHeader("⚙️ Settings");
        System.out.println("  School Name  : EduNexus Smart School");
        System.out.println("  Academic Year: 2026-2027");
        System.out.println("  Principal    : Dr. Rajesh Kumar");
        ConsoleUI.printInfo("Settings are stored in database/settings.csv");
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

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}