package models;

public class Attendance {

    private String id;
    private String studentId;
    private String date;
    private String status;
    private String markedBy;

    public Attendance() {
    }

    public Attendance(String id, String studentId, String date, String status, String markedBy) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.markedBy = markedBy;
    }

    public static Attendance fromCSV(String[] data) {
        if (data == null || data.length < 5) return null;
        return new Attendance(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            data[4].trim()
        );
    }

    public String[] toCSV() {
        return new String[]{id, studentId, date, status, markedBy};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMarkedBy() { return markedBy; }
    public void setMarkedBy(String markedBy) { this.markedBy = markedBy; }

    @Override
    public String toString() {
        return "Student ID: " + studentId + " | Date: " + date + " | Status: " + status;
    }
}