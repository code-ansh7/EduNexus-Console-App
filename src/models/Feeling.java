package models;

public class Feeling {

    private String id;
    private String studentId;
    private String date;
    private String mood;
    private String note;

    public Feeling() {
    }

    public Feeling(String id, String studentId, String date, String mood, String note) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.mood = mood;
        this.note = note;
    }

    public static Feeling fromCSV(String[] data) {
        if (data == null || data.length < 5) return null;
        return new Feeling(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            data[4].trim()
        );
    }

    public String[] toCSV() {
        return new String[]{id, studentId, date, mood, note};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    @Override
    public String toString() {
        return "Date: " + date + " | Mood: " + mood + " | Note: " + note;
    }
}