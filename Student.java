/**
 * Student.java
 * Represents a single student record.
 * Concepts used: class, object, encapsulation (private fields + getters/setters).
 */
public class Student {
    private int id;
    private String name;
    private String branch;
    private double cgpa;

    public Student(int id, String name, String branch, double cgpa) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.cgpa = cgpa;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public double getCgpa() { return cgpa; }

    // Setters (id is not changeable, so no setId)
    public void setName(String name) { this.name = name; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    // Convert this student to one line of text for saving in a file
    public String toCsv() {
        return id + "," + name + "," + branch + "," + cgpa;
    }

    // Create a Student object back from a saved line
    public static Student fromCsv(String line) {
        String[] parts = line.split(",");
        return new Student(
                Integer.parseInt(parts[0].trim()),
                parts[1].trim(),
                parts[2].trim(),
                Double.parseDouble(parts[3].trim()));
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d Name: %-20s Branch: %-8s CGPA: %.2f",
                id, name, branch, cgpa);
    }
}
