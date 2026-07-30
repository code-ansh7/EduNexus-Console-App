package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.Timetable;
import utils.ConsoleUI;
import utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class TimetableService {

    private static final String TIMETABLE_FILE = "timetable.csv";

    public List<Timetable> getAllTimetable() {
        List<String[]> data = CSVReader.readAll(TIMETABLE_FILE);
        List<Timetable> list = new ArrayList<>();
        for (String[] row : data) {
            Timetable t = Timetable.fromCSV(row);
            if (t != null) list.add(t);
        }
        return list;
    }

    public List<Timetable> getTimetableForClass(String classAssigned, String section) {
        List<Timetable> all = getAllTimetable();
        List<Timetable> filtered = new ArrayList<>();
        for (Timetable t : all) {
            if (t.getClassAssigned().equalsIgnoreCase(classAssigned) &&
                t.getSection().equalsIgnoreCase(section)) {
                filtered.add(t);
            }
        }
        return filtered;
    }

    public void viewTimetable(String classAssigned, String section) {
        List<Timetable> list = getTimetableForClass(classAssigned, section);
        ConsoleUI.printHeader("🗓️ Timetable - Class " + classAssigned + "-" + section);

        if (list.isEmpty()) {
            ConsoleUI.printInfo("No timetable entries found.");
            return;
        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (String day : days) {
            System.out.println("\n  📌 " + day);
            System.out.println("  ┌──────────────────────────────────────────────────┐");
            boolean hasEntry = false;
            for (Timetable t : list) {
                if (t.getDay().equalsIgnoreCase(day)) {
                    hasEntry = true;
                    System.out.println("  │  " + pad(t.getTime(), 14) + " | " + pad(t.getSubject(), 31) + "│");
                }
            }
            if (!hasEntry) {
                System.out.println("  │  No classes scheduled                          │");
            }
            System.out.println("  └──────────────────────────────────────────────────┘");
        }
    }

    public boolean addTimetable(String day, String time, String subject, String classAssigned,
                                 String section, String teacherId) {
        List<String[]> existing = CSVReader.readAll(TIMETABLE_FILE);
        String newId = String.valueOf(IdGenerator.generateId(existing));
        Timetable t = new Timetable(newId, day, time, subject, classAssigned, section, teacherId);
        CSVWriter.appendRow(TIMETABLE_FILE, t.toCSV());
        return true;
    }

    public boolean deleteTimetable(String id) {
        return CSVWriter.deleteRow(TIMETABLE_FILE, id);
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}