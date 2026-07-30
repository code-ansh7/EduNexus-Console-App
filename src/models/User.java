package models;

public class User {

    private String id;
    private String name;
    private String role;
    private String password;
    private String status;
    private String classAssigned;
    private String section;
    private String email;
    private String phone;

    public User() {
    }

    public User(String id, String name, String role, String password, String status,
                String classAssigned, String section, String email, String phone) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
        this.status = status;
        this.classAssigned = classAssigned;
        this.section = section;
        this.email = email;
        this.phone = phone;
    }

    public static User fromCSV(String[] data) {
        if (data == null || data.length < 9) return null;
        return new User(
            data[0].trim(),
            data[1].trim(),
            data[2].trim(),
            data[3].trim(),
            data[4].trim(),
            data[5].trim(),
            data[6].trim(),
            data[7].trim(),
            data[8].trim()
        );
    }

    public String[] toCSV() {
        return new String[]{id, name, role, password, status, classAssigned, section, email, phone};
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getClassAssigned() { return classAssigned; }
    public void setClassAssigned(String classAssigned) { this.classAssigned = classAssigned; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Role: " + role +
               " | Class: " + classAssigned + "-" + section +
               " | Email: " + email + " | Phone: " + phone;
    }
}