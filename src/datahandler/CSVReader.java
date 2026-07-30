package datahandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static final String DATABASE_DIR = "database" + File.separator;

    public static List<String[]> readAll(String fileName) {
        List<String[]> data = new ArrayList<>();
        String path = DATABASE_DIR + fileName;
        File file = new File(path);

        if (!file.exists()) {
            return data;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] row = parseLine(line);
                data.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + fileName + ": " + e.getMessage());
        }
        return data;
    }

    public static String[] findById(String fileName, String id) {
        List<String[]> data = readAll(fileName);
        for (String[] row : data) {
            if (row.length > 0 && row[0].trim().equals(id)) {
                return row;
            }
        }
        return null;
    }

    public static List<String[]> findByColumn(String fileName, int columnIndex, String value) {
        List<String[]> result = new ArrayList<>();
        List<String[]> data = readAll(fileName);
        for (String[] row : data) {
            if (row.length > columnIndex && row[columnIndex].trim().equals(value)) {
                result.add(row);
            }
        }
        return result;
    }

    public static List<String[]> searchByColumn(String fileName, int columnIndex, String keyword) {
        List<String[]> result = new ArrayList<>();
        List<String[]> data = readAll(fileName);
        String lowerKeyword = keyword.toLowerCase();
        for (String[] row : data) {
            if (row.length > columnIndex && row[columnIndex].toLowerCase().contains(lowerKeyword)) {
                result.add(row);
            }
        }
        return result;
    }

    public static boolean exists(String fileName, String id) {
        return findById(fileName, id) != null;
    }

    private static String[] parseLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        fields.add(current.toString().trim());

        return fields.toArray(new String[0]);
    }
}