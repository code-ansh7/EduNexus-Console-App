package models;

public class Timetable {

    private String id;
    private String day;
    private String time;
    private String subject;
    private String classAssigned;
    private String section;
    private String teacherId;

    public Timetable() {
    }

    public Timetable(String id, String day, String time, String subject,
                     String classAssigned, String section, String teacherId) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.subject = subject;
        this.classAssigned = classAssigned;
        this.section = section;
        this.teacherId = teacherId;
    }

    public static Timetable fromCSV(String[] data) {
        if (data == null || data.length < 7) return null;
        return new Timetable(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            data[4].trim(),
            data[5].trim(),
            data[6].trim()
        );
    }

    public String[] toCSV() {
        return new String[]{id, day, time, subject, classAssigned, section, teacherId};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getClassAssigned() { return classAssigned; }
    public void setClassAssigned(String classAssigned) { this.classAssigned = classAssigned; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    @Override
    public String toString() {
        return day + " | " + time + " | " + subject + " | Class " + classAssigned + "-" + section;
    }
}