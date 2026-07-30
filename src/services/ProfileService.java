package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.User;
import utils.ConsoleUI;

import java.util.List;

public class ProfileService {

    private static final String USERS_FILE = "users.csv";

    public User getUserById(String id) {
        String[] row = CSVReader.findById(USERS_FILE, id);
        return User.fromCSV(row);
    }

    public void viewProfile(String userId) {
        User user = getUserById(userId);
        if (user == null) {
            ConsoleUI.printError("Profile not found.");
            return;
        }
        ConsoleUI.printHeader("👤 My Profile");
        System.out.println("  ┌──────────────────────────────────────────────────┐");
        System.out.println("  │  ID        : " + pad(user.getId(), 38) + "│");
        System.out.println("  │  Name      : " + pad(user.getName(), 38) + "│");
        System.out.println("  │  Role      : " + pad(user.getRole(), 38) + "│");
        System.out.println("  │  Class     : " + pad(user.getClassAssigned(), 38) + "│");
        System.out.println("  │  Section   : " + pad(user.getSection(), 38) + "│");
        System.out.println("  │  Email     : " + pad(user.getEmail(), 38) + "│");
        System.out.println("  │  Phone     : " + pad(user.getPhone(), 38) + "│");
        System.out.println("  │  Status    : " + pad(user.getStatus(), 38) + "│");
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    public boolean updateProfile(String userId, String name, String email, String phone) {
        String[] row = CSVReader.findById(USERS_FILE, userId);
        if (row == null) return false;
        User user = User.fromCSV(row);
        if (user == null) return false;

        if (name != null && !name.isEmpty()) user.setName(name);
        if (email != null && !email.isEmpty()) user.setEmail(email);
        if (phone != null && !phone.isEmpty()) user.setPhone(phone);

        return CSVWriter.updateRow(USERS_FILE, userId, user.toCSV());
    }

    public List<User> getAllStudents() {
        return getUsersByRole("student");
    }

    public List<User> getAllTeachers() {
        return getUsersByRole("teacher");
    }

    public List<User> getUsersByClass(String classAssigned) {
        List<String[]> data = CSVReader.findByColumn(USERS_FILE, 5, classAssigned);
        List<User> users = new java.util.ArrayList<>();
        for (String[] row : data) {
            User u = User.fromCSV(row);
            if (u != null) users.add(u);
        }
        return users;
    }

    public List<User> getUsersByRole(String role) {
        List<String[]> data = CSVReader.findByColumn(USERS_FILE, 2, role);
        List<User> users = new java.util.ArrayList<>();
        for (String[] row : data) {
            User u = User.fromCSV(row);
            if (u != null) users.add(u);
        }
        return users;
    }

    public boolean addUser(User user) {
        CSVWriter.appendRow(USERS_FILE, user.toCSV());
        return true;
    }

    public boolean deleteUser(String id) {
        return CSVWriter.deleteRow(USERS_FILE, id);
    }

    public boolean toggleStatus(String id) {
        String[] row = CSVReader.findById(USERS_FILE, id);
        if (row == null) return false;
        User user = User.fromCSV(row);
        if (user == null) return false;
        String newStatus = user.getStatus().equalsIgnoreCase("active") ? "inactive" : "active";
        user.setStatus(newStatus);
        return CSVWriter.updateRow(USERS_FILE, id, user.toCSV());
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}