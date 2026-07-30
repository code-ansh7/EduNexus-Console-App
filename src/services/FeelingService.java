package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.Feeling;
import utils.ConsoleUI;
import utils.DateHelper;
import utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class FeelingService {

    private static final String FEELINGS_FILE = "feelings.csv";

    public List<Feeling> getAllFeelings() {
        List<String[]> data = CSVReader.readAll(FEELINGS_FILE);
        List<Feeling> list = new ArrayList<>();
        for (String[] row : data) {
            Feeling f = Feeling.fromCSV(row);
            if (f != null) list.add(f);
        }
        return list;
    }

    public List<Feeling> getFeelingsByStudent(String studentId) {
        List<String[]> data = CSVReader.findByColumn(FEELINGS_FILE, 1, studentId);
        List<Feeling> list = new ArrayList<>();
        for (String[] row : data) {
            Feeling f = Feeling.fromCSV(row);
            if (f != null) list.add(f);
        }
        return list;
    }

    public void logFeeling(String studentId) {
        ConsoleUI.printHeader("😊 How are you feeling today?");

        System.out.println("  1. 😊 Happy");
        System.out.println("  2. 😐 Okay");
        System.out.println("  3. 😢 Sad");
        System.out.println("  4. 😰 Stressed");
        System.out.println("  5. 😴 Tired");
        System.out.println("  6. 🤒 Unwell");

        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("\n  Choose your mood (1-6): ");
        String choice = sc.nextLine().trim();

        String mood;
        switch (choice) {
            case "1": mood = "Happy"; break;
            case "2": mood = "Okay"; break;
            case "3": mood = "Sad"; break;
            case "4": mood = "Stressed"; break;
            case "5": mood = "Tired"; break;
            case "6": mood = "Unwell"; break;
            default:
                ConsoleUI.printError("Invalid choice.");
                return;
        }

        System.out.print("  Any note about how you feel? (optional, press Enter to skip): ");
        String note = sc.nextLine().trim();
        if (note.isEmpty()) note = "-";

        List<String[]> existing = CSVReader.readAll(FEELINGS_FILE);
        String newId = String.valueOf(IdGenerator.generateId(existing));
        String date = DateHelper.getCurrentDate();

        Feeling f = new Feeling(newId, studentId, date, mood, note);
        CSVWriter.appendRow(FEELINGS_FILE, f.toCSV());

        ConsoleUI.printSuccess("Your feeling has been recorded. Take care of yourself 💕");
    }

    public void viewMyFeelings(String studentId) {
        List<Feeling> list = getFeelingsByStudent(studentId);
        ConsoleUI.printHeader("📖 My Feelings Journal");

        if (list.isEmpty()) {
            ConsoleUI.printInfo("No feelings logged yet.");
            return;
        }

        System.out.println("  ┌──────────────────────────────────────────────────┐");
        for (Feeling f : list) {
            System.out.println("  │  " + pad(DateHelper.formatDate(f.getDate()), 15) + " | " + pad(f.getMood(), 10) + " | " + pad(f.getNote(), 18) + "│");
        }
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}