import java.util.Scanner;

/**
 * Main.java
 * Console menu for the Student Management System.
 * Concepts used: Scanner input, loops, switch, input validation with try-catch.
 */
public class Main {
    static Scanner sc = new Scanner(System.in);
    static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: manager.viewAll(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try 1-6.");
            }
        } while (choice != 6);
    }

    static void addStudent() {
        int id = readInt("Enter ID: ");
        String name = readText("Enter name: ");
        String branch = readText("Enter branch: ");
        double cgpa = readCgpa();
        Student s = new Student(id, name, branch, cgpa);
        if (manager.addStudent(s)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with this ID already exists.");
        }
    }

    static void searchStudent() {
        int id = readInt("Enter ID to search: ");
        Student s = manager.findById(id);
        System.out.println(s != null ? s : "Student not found.");
    }

    static void updateStudent() {
        int id = readInt("Enter ID to update: ");
        if (manager.findById(id) == null) {
            System.out.println("Student not found.");
            return;
        }
        String name = readText("Enter new name: ");
        String branch = readText("Enter new branch: ");
        double cgpa = readCgpa();
        manager.updateStudent(id, name, branch, cgpa);
        System.out.println("Student updated successfully.");
    }

    static void deleteStudent() {
        int id = readInt("Enter ID to delete: ");
        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // ---------- Helper methods for safe input ----------

    // Keeps asking until the user types a valid whole number
    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Keeps asking until the user types a non-empty text without commas
    static String readText(String message) {
        while (true) {
            System.out.print(message);
            String text = sc.nextLine().trim();
            if (!text.isEmpty() && !text.contains(",")) {
                return text;
            }
            System.out.println("Input cannot be empty or contain commas.");
        }
    }

    // Keeps asking until CGPA is between 0 and 10
    static double readCgpa() {
        while (true) {
            System.out.print("Enter CGPA (0-10): ");
            try {
                double cgpa = Double.parseDouble(sc.nextLine().trim());
                if (cgpa >= 0 && cgpa <= 10) {
                    return cgpa;
                }
            } catch (NumberFormatException e) {
                // fall through to the message below
            }
            System.out.println("CGPA must be a number between 0 and 10.");
        }
    }
}
