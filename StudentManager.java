import java.io.*;
import java.util.ArrayList;

/**
 * StudentManager.java
 * Holds all CRUD logic (Create, Read, Update, Delete) and file storage.
 * Concepts used: ArrayList (collections), file handling, exception handling.
 */
public class StudentManager {
    private static final String FILE_NAME = "students.csv";
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManager() {
        loadFromFile();   // load saved data when the program starts
    }

    // CREATE: returns false if the ID already exists
    public boolean addStudent(Student s) {
        if (findById(s.getId()) != null) {
            return false;
        }
        students.add(s);
        saveToFile();
        return true;
    }

    // READ: show all students
    public void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // READ: find one student by ID (returns null if not found)
    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // UPDATE: change details of an existing student
    public boolean updateStudent(int id, String name, String branch, double cgpa) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setBranch(branch);
        s.setCgpa(cgpa);
        saveToFile();
        return true;
    }

    // DELETE: remove a student by ID
    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        students.remove(s);
        saveToFile();
        return true;
    }

    // Save all records to students.csv
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.println(s.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Load records from students.csv (if the file exists)
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(Student.fromCsv(line));
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
