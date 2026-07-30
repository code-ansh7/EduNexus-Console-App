package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getCurrentDate() {
        return LocalDate.now().format(FORMATTER);
    }

    public static String getCurrentDateTime() {
        return LocalDate.now().format(FORMATTER);
    }

    public static boolean isValidDate(String date) {
        if (date == null || date.isEmpty()) return false;
        try {
            LocalDate.parse(date, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String formatDate(String date) {
        if (!isValidDate(date)) return date;
        try {
            LocalDate d = LocalDate.parse(date, FORMATTER);
            DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return d.format(displayFormatter);
        } catch (Exception e) {
            return date;
        }
    }

    public static long daysBetween(String date1, String date2) {
        if (!isValidDate(date1) || !isValidDate(date2)) return 0;
        LocalDate d1 = LocalDate.parse(date1, FORMATTER);
        LocalDate d2 = LocalDate.parse(date2, FORMATTER);
        return java.time.temporal.ChronoUnit.DAYS.between(d1, d2);
    }
}