package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.User;
import utils.ConsoleUI;
import utils.DateHelper;
import utils.IdGenerator;

import java.util.List;

public class ReportService {

    private static final String REPORTS_FILE = "reports.csv";
    private static final String USERS_FILE = "users.csv";
    private static final String ATTENDANCE_FILE = "attendance.csv";

    public void generateStudentReport() {
        ConsoleUI.printHeader("📊 Student Report");

        List<String[]> users = CSVReader.readAll(USERS_FILE);
        int totalStudents = 0;
        int activeStudents = 0;

        for (String[] row : users) {
            if (row.length > 2 && row[2].trim().equalsIgnoreCase("student")) {
                totalStudents++;
                if (row.length > 4 && row[4].trim().equalsIgnoreCase("active")) {
                    activeStudents++;
                }
            }
        }

        System.out.println("  Total Students   : " + totalStudents);
        System.out.println("  Active Students  : " + activeStudents);
        System.out.println("  Inactive Students: " + (totalStudents - activeStudents));

        saveReport("student_summary", "Total: " + totalStudents + ", Active: " + activeStudents);
    }

    public void generateTeacherReport() {
        ConsoleUI.printHeader("📊 Teacher Report");

        List<String[]> users = CSVReader.readAll(USERS_FILE);
        int totalTeachers = 0;
        int activeTeachers = 0;

        for (String[] row : users) {
            if (row.length > 2 && row[2].trim().equalsIgnoreCase("teacher")) {
                totalTeachers++;
                if (row.length > 4 && row[4].trim().equalsIgnoreCase("active")) {
                    activeTeachers++;
                }
            }
        }

        System.out.println("  Total Teachers   : " + totalTeachers);
        System.out.println("  Active Teachers  : " + activeTeachers);
        System.out.println("  Inactive Teachers: " + (totalTeachers - activeTeachers));

        saveReport("teacher_summary", "Total: " + totalTeachers + ", Active: " + activeTeachers);
    }

    public void generateAttendanceReport() {
        ConsoleUI.printHeader("📊 Attendance Report");

        List<String[]> records = CSVReader.readAll(ATTENDANCE_FILE);
        int total = records.size();
        int present = 0, absent = 0, late = 0;

        for (String[] row : records) {
            if (row.length > 3) {
                String status = row[3].trim().toLowerCase();
                if (status.equals("present")) present++;
                else if (status.equals("absent")) absent++;
                else if (status.equals("late")) late++;
            }
        }

        System.out.println("  Total Records : " + total);
        System.out.println("  Present       : " + present);
        System.out.println("  Absent        : " + absent);
        System.out.println("  Late          : " + late);

        if (total > 0) {
            double percent = (present * 100.0) / total;
            System.out.println("  Attendance %  : " + String.format("%.2f", percent) + "%");
        }

        saveReport("attendance_summary", "Total: " + total + ", Present: " + present);
    }

    private void saveReport(String type, String description) {
        List<String[]> existing = CSVReader.readAll(REPORTS_FILE);
        String newId = String.valueOf(IdGenerator.generateId(existing));
        String date = DateHelper.getCurrentDate();
        String[] row = new String[]{newId, type, description, date, "admin"};
        CSVWriter.appendRow(REPORTS_FILE, row);
    }

    public void viewAllReports() {
        ConsoleUI.printHeader("📑 All Reports");
        List<String[]> reports = CSVReader.readAll(REPORTS_FILE);
        if (reports.isEmpty()) {
            ConsoleUI.printInfo("No reports generated yet.");
            return;
        }
        System.out.println("  ┌──────────────────────────────────────────────────┐");
        for (String[] r : reports) {
            if (r.length >= 4) {
                System.out.println("  │  " + pad(r[0], 4) + " | " + pad(r[1], 18) + " | " + pad(r[3], 12) + "│");
                System.out.println("  │       " + pad(r[2], 43) + "│");
            }
        }
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}