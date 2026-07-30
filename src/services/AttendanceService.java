package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.Attendance;
import models.User;
import utils.ConsoleUI;
import utils.DateHelper;
import utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class AttendanceService {

    private static final String ATTENDANCE_FILE = "attendance.csv";

    public List<Attendance> getAllAttendance() {
        List<String[]> data = CSVReader.readAll(ATTENDANCE_FILE);
        List<Attendance> list = new ArrayList<>();
        for (String[] row : data) {
            Attendance a = Attendance.fromCSV(row);
            if (a != null) list.add(a);
        }
        return list;
    }

    public List<Attendance> getAttendanceByStudent(String studentId) {
        List<String[]> data = CSVReader.findByColumn(ATTENDANCE_FILE, 1, studentId);
        List<Attendance> list = new ArrayList<>();
        for (String[] row : data) {
            Attendance a = Attendance.fromCSV(row);
            if (a != null) list.add(a);
        }
        return list;
    }

    public List<Attendance> getAttendanceByDate(String date) {
        List<String[]> data = CSVReader.findByColumn(ATTENDANCE_FILE, 2, date);
        List<Attendance> list = new ArrayList<>();
        for (String[] row : data) {
            Attendance a = Attendance.fromCSV(row);
            if (a != null) list.add(a);
        }
        return list;
    }

    public void viewMyAttendance(String studentId) {
        List<Attendance> list = getAttendanceByStudent(studentId);
        ConsoleUI.printHeader("📅 My Attendance");

        if (list.isEmpty()) {
            ConsoleUI.printInfo("No attendance records found.");
            return;
        }

        int present = 0, absent = 0, late = 0;
        System.out.println("  ┌──────────────────────────────────────────────────┐");
        System.out.println("  │  Date           | Status                        │");
        System.out.println("  ├──────────────────────────────────────────────────┤");
        for (Attendance a : list) {
            String date = DateHelper.formatDate(a.getDate());
            String status = a.getStatus().toUpperCase();
            if (status.equals("PRESENT")) present++;
            else if (status.equals("ABSENT")) absent++;
            else if (status.equals("LATE")) late++;
            System.out.println("  │  " + pad(date, 15) + " | " + pad(status, 30) + "│");
        }
        System.out.println("  └──────────────────────────────────────────────────┘");

        int total = list.size();
        double percent = total > 0 ? (present * 100.0 / total) : 0;
        System.out.println("\n  📊 Summary:");
        System.out.println("     Present: " + present + " | Absent: " + absent + " | Late: " + late);
        System.out.println("     Attendance: " + String.format("%.2f", percent) + "%");
    }

    public void markAttendanceForClass(String classSection, String teacherId) {
        ProfileService profileService = new ProfileService();
        List<User> students = profileService.getAllStudents();
        List<User> classStudents = new ArrayList<>();
        for (User s : students) {
            if (s.getClassAssigned().equalsIgnoreCase(classSection)) {
                classStudents.add(s);
            }
        }

        if (classStudents.isEmpty()) {
            ConsoleUI.printError("No students found in class " + classSection);
            return;
        }

        String date = DateHelper.getCurrentDate();
        ConsoleUI.printHeader("📋 Mark Attendance - " + DateHelper.formatDate(date));

        java.util.Scanner sc = new java.util.Scanner(System.in);

        for (User s : classStudents) {
            System.out.println("\n  Student: " + s.getName() + " (ID: " + s.getId() + ")");
            System.out.println("  1. Present");
            System.out.println("  2. Absent");
            System.out.println("  3. Late");
            System.out.print("  Choice: ");
            String choice = sc.nextLine().trim();

            String status;
            if (choice.equals("1")) status = "present";
            else if (choice.equals("2")) status = "absent";
            else if (choice.equals("3")) status = "late";
            else {
                ConsoleUI.printWarning("Invalid choice. Marking as absent.");
                status = "absent";
            }

            List<String[]> existing = CSVReader.readAll(ATTENDANCE_FILE);
            String newId = String.valueOf(IdGenerator.generateId(existing));
            Attendance a = new Attendance(newId, s.getId(), date, status, teacherId);
            CSVWriter.appendRow(ATTENDANCE_FILE, a.toCSV());
        }

        ConsoleUI.printSuccess("Attendance marked successfully!");
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}