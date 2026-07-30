package services;

import datahandler.CSVReader;
import datahandler.CSVWriter;
import models.Notice;
import utils.ConsoleUI;
import utils.DateHelper;
import utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class NoticeService {

    private static final String NOTICES_FILE = "notices.csv";

    public List<Notice> getAllNotices() {
        List<String[]> data = CSVReader.readAll(NOTICES_FILE);
        List<Notice> notices = new ArrayList<>();
        for (String[] row : data) {
            Notice n = Notice.fromCSV(row);
            if (n != null) notices.add(n);
        }
        return notices;
    }

    public List<Notice> getNoticesForRole(String role) {
        List<Notice> all = getAllNotices();
        List<Notice> filtered = new ArrayList<>();
        for (Notice n : all) {
            if (n.getTargetRole().equalsIgnoreCase("all") ||
                n.getTargetRole().equalsIgnoreCase(role)) {
                filtered.add(n);
            }
        }
        return filtered;
    }

    public void viewNotices(String role) {
        List<Notice> notices = getNoticesForRole(role);
        ConsoleUI.printHeader("📢 Notice Board");

        if (notices.isEmpty()) {
            ConsoleUI.printInfo("No notices available.");
            return;
        }

        int i = 1;
        for (Notice n : notices) {
            System.out.println("  ┌──────────────────────────────────────────────────┐");
            System.out.println("  │  [" + i + "] " + pad(n.getTitle(), 45) + "│");
            System.out.println("  │      Date: " + pad(DateHelper.formatDate(n.getDate()), 41) + "│");
            System.out.println("  │      " + pad(n.getContent(), 48) + "│");
            System.out.println("  └──────────────────────────────────────────────────┘");
            i++;
        }
    }

    public boolean addNotice(String title, String content, String authorId, String targetRole) {
        List<String[]> existing = CSVReader.readAll(NOTICES_FILE);
        String newId = String.valueOf(IdGenerator.generateId(existing));
        String date = DateHelper.getCurrentDate();
        Notice n = new Notice(newId, title, content, date, authorId, targetRole);
        CSVWriter.appendRow(NOTICES_FILE, n.toCSV());
        return true;
    }

    public boolean deleteNotice(String id) {
        return CSVWriter.deleteRow(NOTICES_FILE, id);
    }

    private String pad(String s, int n) {
        if (s == null) s = "";
        if (s.length() >= n) return s.substring(0, n);
        return s + String.format("%" + (n - s.length()) + "s", "");
    }
}