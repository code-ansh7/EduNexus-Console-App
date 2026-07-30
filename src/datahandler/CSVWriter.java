package datahandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {

    private static final String DATABASE_DIR = "database" + File.separator;

    public static void appendRow(String fileName, String[] row) {
        String path = DATABASE_DIR + fileName;
        File file = new File(path);

        boolean fileExists = file.exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                // write header if file is new
                String header = getHeader(fileName);
                if (header != null) {
                    bw.write(header);
                    bw.newLine();
                }
            }
            bw.write(joinRow(row));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file " + fileName + ": " + e.getMessage());
        }
    }

    public static void writeAll(String fileName, List<String[]> data, String header) {
        String path = DATABASE_DIR + fileName;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            if (header != null && !header.isEmpty()) {
                bw.write(header);
                bw.newLine();
            }
            for (String[] row : data) {
                bw.write(joinRow(row));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file " + fileName + ": " + e.getMessage());
        }
    }

    public static boolean updateRow(String fileName, String id, String[] newRow) {
        List<String[]> data = datahandler.CSVReader.readAll(fileName);
        boolean found = false;
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row.length > 0 && row[0].trim().equals(id)) {
                data.set(i, newRow);
                found = true;
                break;
            }
        }
        if (found) {
            String header = getHeader(fileName);
            writeAll(fileName, data, header);
        }
        return found;
    }

    public static boolean deleteRow(String fileName, String id) {
        List<String[]> data = datahandler.CSVReader.readAll(fileName);
        boolean removed = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).length > 0 && data.get(i)[0].trim().equals(id)) {
                data.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            String header = getHeader(fileName);
            writeAll(fileName, data, header);
        }
        return removed;
    }

    private static String joinRow(String[] row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            if (i > 0) sb.append(",");
            String value = row[i] == null ? "" : row[i];
            if (value.contains(",")) {
                sb.append("\"").append(value).append("\"");
            } else {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    private static String getHeader(String fileName) {
        switch (fileName) {
            case "users.csv": return "id,name,role,password,status,class,section,email,phone";
            case "notices.csv": return "id,title,content,date,authorId,targetRole";
            case "homework.csv": return "id,subject,description,dueDate,classAssigned,teacherId,datePosted";
            case "attendance.csv": return "id,studentId,date,status,markedBy";
            case "timetable.csv": return "id,day,time,subject,class,section,teacherId";
            case "feelings.csv": return "id,studentId,date,mood,note";
            case "reports.csv": return "id,type,description,date,generatedBy";
            case "settings.csv": return "key,value";
            default: return null;
        }
    }
}