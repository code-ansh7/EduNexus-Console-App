package utils;

import java.util.List;

public class IdGenerator {

    public static int generateId(List<String[]> existingData) {
        int maxId = 0;
        for (String[] row : existingData) {
            if (row.length > 0) {
                try {
                    int id = Integer.parseInt(row[0].trim());
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch (NumberFormatException e) {
                    // skip invalid ids
                }
            }
        }
        return maxId + 1;
    }

    public static int generateId(int currentMax) {
        return currentMax + 1;
    }
}