package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.Homework;
import utils.ConsoleUI;
import utils.DateHelper;
import utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class HomeworkService {

    private static final String HOMEWORK_FILE = "homework.csv";

    public List<Homework> getAllHomework() {
        List<String[]> data = CSVReader.readAll(HOMEWORK_FILE);
        List<Homework> list = new ArrayList<>();
        for (String[] row : data) {
            Homework h = Homework.fromCSV(row);
            if (h != null) list.add(h);
        }
        return list;
    }

    public List<Homework> getHomeworkForClass(String classSection) {
        List<Homework> all = getAllHomework();
        List<Homework> filtered = new ArrayList<>();
        for (Homework h : all) {
            if (h.getClassAssigned().equalsIgnoreCase(classSection)) {
                filtered.add(h);
            }
        }
        return filtered;
    }

    public void viewHomework(String classSection) {
        List<Homework> list = getHomeworkForClass(classSection);
        ConsoleUI.printHeader("📚 Homework");

        if (list.isEmpty()) {
            ConsoleUI.printInfo("No homework assigned.");
            return;
        }

        int i = 1;
        for (Homework h : list) {
            System.out.println("  ┌──────────────────────────────────────────────────┐");
            System.out.println("  │  [" + i + "] " + pad(h.getSubject(), 45) + "│");
            System.out.println("  │      " + pad(h.getDescription(), 48) + "│");
            System.out.println("  │      Due: " + pad(DateHelper.formatDate(h.getDueDate()), 41) + "│");
            System.out.println("  │      Posted: " + pad(DateHelper.formatDate(h.getDatePosted()), 38) + "│");
            System.out.println("  └──────────────────────────────────────────────────┘");
            i++;
        }
    }

    public boolean addHomework(String subject, String description, String dueDate,
                                String classAssigned, String teacherId) {
        List<String[]> existing = CSVReader.readAll(HOMEWORK_FILE);
        String newId = String.valueOf(IdGenerator.generateId(existing));
        String datePosted = DateHelper.getCurrentDate();
        Homework h = new Homework(newId, subject, description, dueDate, classAssigned, teacherId, datePosted);
        CSVWriter.appendRow(HOMEWORK_FILE, h.toCSV());
        return true;
    }

    public boolean deleteHomework(String id) {
        return CSVWriter.deleteRow(HOMEWORK_FILE, id);
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}